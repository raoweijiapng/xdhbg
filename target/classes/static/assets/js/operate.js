// 删除确认弹出框
function p_del() {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        return true;
    } else {
        return false;

    }
}
//手机号正则
var regex = /^((0\d{2,3}-\d{7,8})|(1[3456789]\d{9}))$/;
//姓名正则
var reg_name = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
//班级名称正则
var reg_class = /^([a-z0-9]){2,10}$/i;
//密码正则
var reg_password = /^([a-z0-9\.\@\!\#\$\%\^\&\*\(\)]){6,20}$/i;

//手机号符合正则时手机号条变绿
$("#form").bind("input propertychange", function () {
    if (regex.test($("#mobile").val())){
        $('#mobile').css("border-color","green").css("border-width","2px");
    }else {
        $('#mobile').css("border-color","red");
    }
})

//姓名符合正则时密码框变绿
$("#name").bind("input propertychange", function () {
    if (reg_name.test($("#name").val())) {
        $('#name').css("border-color", "green").css("border-width", "2px");
    } else {
        $('#name').css("border-color", "red");
    }
})

//密码符合正则时密码框变绿
$("#psd").bind("input propertychange", function () {
    if (reg_password.test($("#psd").val())) {
        $('#psd').css("border-color", "green").css("border-width", "2px");
    } else {
        $('#psd').css("border-color", "red");
    }
})
$("#psd2").bind("input propertychange", function () {
    if (reg_password.test($("#psd2").val())) {
        $('#psd2').css("border-color", "green").css("border-width", "2px");
    } else {
        $('#psd2').css("border-color", "red");
    }
})

// 增加班级
function p_insert() {
    var className = $("#class").val().trim();
    var teacherId = $("#teacher_id").val();
    if (className == "") {
        alert("班级名称不能为空");
        return false;
    } else if (teacherId == -1) {
        alert("请选择老师");
    } else {
        $.ajax({
            type: "post",
            url: "/adminaddxdhclassform",
            data: {
                class_name: $('#class').val(),
                teacher_id: parseInt($('#teacher_id').val())
            }, //发送到服务器的参数
            success: function (result) {
                if (result.msg == "success") {
                    window.location.href = "/admin.xdhclass"
                } else {
                    alert("该班级已存在");
                }
            }
        });
    }
}







//修改班级
$("#btn").click(function () {
    var class_name = $("#class").val();
    var teacher_id = $("#teacher_id").val();
    var is_graduate = $("input:radio[name='is_graduate']:checked").val();
    if (class_name == "") {
        alert("班级名称不能为空");
    } else {
        $.ajax({
            type: "post",
            url: $("#form").attr("url"),
            data: {
                teacher_id: teacher_id,
                class_name: class_name,
                is_graduate: is_graduate
            }, //发送到服务器的参数
            success: function (result) {
                if (result.msg == "success") {
                    window.location.href = "/admin.xdhclass";
                } else {
                    alert("该班级名已存在");
                }
            },
        });
    }
});

$(".delBtn").click(function () {
    $.ajax({
        url: $(this).attr("url"),
        type: "delete",
        success: function (result) {
            if (result.msg == "success") {
                var msg = "您真的确定要删除吗？";
                if (confirm(msg) == true) {
                    window.location.href = "/admin.xdhclass";
                } else {
                    return false;
                }
            } else {
                alert("该班级有学生,无法删除");
            }
        }
    });
});

//修改教师
$("#btn3").click(function () {
    var password = $('#psd').val();
    var password2 = $("#psd2").val();
    var mobile = $("#mobile").val();
    var name = $("#name").val();
    console.log(mobile);
    if (password != password2) {
        alert("两次输入的密码不相等,请重新输入");
    }else if (password == "" && password2 == ""){
        alert("请输入密码" );
    }
    else if (mobile == ""){
        alert("手机号不能为空");
    } else if (name == "" ){
        alert("请填写教师姓名" );
    }
    else if (regex.test(mobile) && reg_name.test(name)
        && reg_password.test(password)) {
        $.ajax({
            type: "post",
            url: $("#form").attr("url"),
            data: {
                name: name,
                mobile: mobile,
                password : password
            }, //发送到服务器的参数
            success: function (result) {
                if (result.msg == "success") {
                    window.location.href = "/admin.teacher";
                } else {
                    alert("该手机号已存在");
                }
            },
        });
    }
});









function p_ateacher() {
    var password = $('#psd').val();
    var password2 = $("#psd2").val();
    var mobile = $('#mobile').val();
    var name = $('#name').val();
    var add_time = 1;
    var add_ip = 1;
    if (password != password2) {
        alert("两次输入的密码不相等,请重新输入");
    }
    else if (password == "" && password2 == ""){
        alert("请输入密码" );
    }else if (name == ""){
        alert("请填写教师姓名" );
    }else if (regex.test(mobile) && reg_name.test(name)
        && reg_password.test(password)) {
        $.ajax({
            type: "post",
            url: "/admin.addteacher",
            data: {
                name: name,
                mobile: mobile,
                password: password,
                add_ip: add_ip,
                add_time: add_time
            },success:function(result) {
                if (result.msg == "success"){
                    window.location.href ="/admin.teacher";
                }else {
                    alert("该手机号已存在");
                    
                }
                
            }
        });
    }


}


