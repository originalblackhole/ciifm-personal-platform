layui.use(['layer', 'form'],function(){
    var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;

    $(document).ready(function() {
        var url = window.location.href;
        url = url.replace("login","");
        var t = Math.random();
        $("#imgCode")[0].src=url+"login/createImgCode?t=" + t;
    });

    $("#imgCode").on('click',function(){
        var url = window.location.href;
        url = url.replace("login","");
        var t = Math.random();
        $("#imgCode")[0].src=url+"login/createImgCode?t=" + t;
    });


    //登录按钮
    form.on("submit(login)",function(data){
        var loadIndex = layer.load(2, {
            shade: [0.3, '#333']
        });
        if($('form').find('input[type="checkbox"]')[0].checked){
            data.field.rememberMe = true;
        }else{
            data.field.rememberMe = false;
        }
        data.field.username = $("#userName").val();
        data.field.password = $("#password").val();
        data.field.code = $("#code").val();
        $.post(data.form.action, data.field, function(res) {
            layer.close(loadIndex);
            if(res.code == 0){
                var url = window.location.href;
                url = url.replace("login","index");
                location.href = url;
            }else{
                layer.msg(res.message);
                $("#imgCode").click();
                $("#code").val("")
            }
        });
        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
