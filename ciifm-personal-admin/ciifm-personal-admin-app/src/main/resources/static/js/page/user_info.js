var form, $;
layui.use(['form','layer','upload'],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload;
    //上传头像
    upload.render({
        elem: '.userFaceBtn',
        url: '/file/uploadIcon',
        field: 'file',
        before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#userFace').attr('src', result); //图片链接（base64）
            });
            layer.load(2, {
                shade: [0.3, '#333']
            });
        },
        done: function (res) {
            layer.closeAll('loading');
            //如果上传失败
            if (res.code == 0) {
                layer.msg("上传成功",{time:1000},function () {
                    //$("input[name='icon']").val(res.data.url);
                    $('#userFace').attr('src', res.data); //图片链接（base64）
                    $('#userInfo').attr('src', res.data); //图片链接（base64）
                })
            }else{
                return layer.msg('上传失败');
            }
        }
    });

    //提交个人资料
    form.on("submit(changeUser)",function(data){
        var loadIndex = layer.load(2, {
            shade: [0.3, '#333']
        });
        $.post("/user/updateUserInfo",data.field,function(res){
            layer.close(loadIndex);
            if(res.code == 0 ){
                parent.layer.msg("保存成功！",{time:1500},function(){
                    parent.location.reload();
                });
            }else{
                layer.msg(res.message);
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

})