// 删除确认弹出框
function p_del() {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg)==true){
        alert("点击确定完成删除!");
        return true;
    }else{
        return false;
        
    }
}

// 增加确认弹出框
function p_insert() {
    var insert = "您确定要增加班级吗？";
    if (confirm(insert)==true){
        
        $.ajax({
            type: "post",
            async: false,
            url: "/xdhclassform",
            data: { 
                class_name: $('#class').val(),
                teacher_id: parseInt($('#teacher_id').val())
            }, //发送到服务器的参数
            
            success: function (result) {
                if (result.class_name && result.teacher_id){
                    window.location.href='/xdhclass';
                }
                
            },
            error: function (errorMsg) {
                alert("手机号已存在!");
            }
        });
        
    }else{
        alert("取消增加");
        return false;

    }
}// 增加确认弹出框
function p_update() {
    var update = "您确定要修改吗？";
    if (confirm(update)==true){
        alert("修改成功");
        return true;
    }else{
        alert("取消修改");
        return false;

    }
}

//姓名正则
var  reg_name= /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;


//姓名符合正则时密码框变绿
$("#name").bind("input propertychange", function () {
    if (reg_name.test($("#name").val())){
        $('#name').css("border-color","green").css("border-width","2px");
    }else {
        $('#name').css("border-color","red");
    }
})
//姓名符合正则时候姓名框变绿
// $("#form").bind("input propertychange", function () {
//     if (reg_name.test($("#name").val())){
//         $('#name').css("border-color","green").css("border-width","2px");
//     }else {
//         $('#name').css("border-color","red");
//     }
// })
//班级名称正则
var  reg_class= /^([a-z0-9]){2,10}$/i;
//班级符合格式并且数据库中不存在该班级才是绿色
$("#class").bind("input propertychange", function () {
    if (reg_class.test($("#class").val())){
        
        $.ajax({
            type: "post",
            url: "/checkclassname",
            data: {
                class_name: $('#class').val()
            }, //发送到服务器的参数
            success: function (result) {
                if (result.class_name){
                    $('#class').css("border-color","red");
                    alert("班级已存在!");
                    $("#btn").attr("disabled",true);
                }else {
                    $('#class').css("border-color", "green").css("border-width", "2px");
                    $("#btn").attr("disabled",false);
                }
            },
            error: function (errorMsg) {
                alert("请选择老师!");
            }
        });
    }else {
        $('#class').css("border-color","red");
        $("#btn").attr("disabled",true);
    }
})


//手机号正则
var regex=/^((0\d{2,3}-\d{7,8})|(1[3456789]\d{9}))$/;

//手机号符合正则时手机号条变绿
// $("#form").bind("input propertychange", function () {
//     if (regex.test($("#mobile").val())){
//         $('#mobile').css("border-color","green").css("border-width","2px");
//     }else {
//         $('#mobile').css("border-color","red");
//     }
// })

// $("#mobile").bind("input propertychange", function () {
//     if (regex.test($("#mobile").val())){
//         $('#mobile').css("border-color","green").css("border-width","2px");
//     }else {
//         $('#mobile').css("border-color","red");
//     }
// })
//判断手机号是否存在,若数据库不存在则绿色
$("#mobile").bind("input propertychange", function () {
    if (regex.test($("#mobile").val())){

        $.ajax({
            type: "post",
            url: "/checkteachermobile",
            data: {
                mobile: $('#mobile').val()
            }, //发送到服务器的参数
            success: function (result) {
                if (result.mobile){
                    $('#mobile').css("border-color","red");
                    
                    alert("手机号已存在!");
                    $("#btn").attr("disabled",true);
                }else {
                    $('#mobile').css("border-color", "green").css("border-width", "2px");
                    $("#btn").attr("disabled",false);
                }

            },
            error: function (errorMsg) {
                alert("手机号已存在!");
            }
        });
    }else {
        $('#mobile').css("border-color","red");
        $("#btn").attr("disabled",true);
    }
})



//密码正则
var  reg_password= /^([a-z0-9\.\@\!\#\$\%\^\&\*\(\)]){6,20}$/i;

//密码符合正则时密码框变绿
$("#psd").bind("input propertychange", function () {
    if (reg_password.test($("#psd").val())){
        $('#psd').css("border-color","green").css("border-width","2px");
    }else {
        $('#psd').css("border-color","red");
    }
})


//数据都符合正则时候才可以点击按钮
$("#form").bind("input propertychange", function () {
    if (regex.test($("#mobile").val())&&reg_name.test($("#name").val())
        &&reg_password.test($("#psd").val())){
        $("#btn").attr("disabled",false);
    }else {
        $("#btn").attr("disabled",true);
    }

})



