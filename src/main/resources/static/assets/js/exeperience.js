var regex=/^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$/;
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

function del() {
    var msg = "确定要删除吗?";
    if (confirm(msg)==true){
        return true;
    }else{
        return false;
    }
}
