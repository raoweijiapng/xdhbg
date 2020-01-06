var regex=/^[\u4E00-\u9FA5A-Za-z]{1,20}$/;
function inputTest() {
    regex.test($("#searchInput").val())?$("#form").submit():alert("输入有误");
    return false;
}
$(document).keypress(function(event){
    var keycode = (event.keyCode ? event.keyCode : event.which);
    if(keycode == '13'){
        regex.test($("#searchInput").val())?$("#form").submit():alert("输入有误");
        return false;
    }
});



//作品与心得的单选删除
function delone(value) {
    if (confirm("确定要删除吗?")==true){
        $.ajax({
            url : value,
            type :"DELETE",
            success : function() {
                location.reload();
            }
        })
    }
}


