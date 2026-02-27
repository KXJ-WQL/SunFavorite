<#import "/inclusion/out.ftl" as Layout>

<@Layout.out "个人管理">

    <div id="app" class="row justify-content-md-center">


        <form class="row g-3" id="collect-form" style="width: 500px;">

            <input type="hidden" name="id" value=${persopnal.id}>

            <div class="col-12">
                <label for="title" class="form-label">用户名：</label>
                <input type="text" name="title" class="form-control" id="title" value="${persopnal.username}" required>
            </div>


            <div class="col-12">
                <label for="title" class="form-label">头像修改：</label>
                <br/>
                <div class="layui-upload-drag" id="test10">
                    <i class="layui-icon"></i>
                    <p>点击上传，或将文件拖拽到此处</p>
                    <div class="layui-hide" id="uploadDemoView">
                        <hr>
                        <img src="${persopnal.avater}" alt="上传成功后渲染" style="max-width: 196px">
                    </div>
                </div>
            </div>

            <div class="col-12">
                <button class="btn btn-primary" type="submit">提交修改内容</button>
            </div>
        </form>
    </div>

    <script>
        var userId = '${Session["userentity"].openid}';

        $(function () {
            $("#collect-form").submit(function (event) {
                // 阻止form提交
                event.preventDefault();
                $.ajax({
                    type: 'POST',
                    url: '/updateUserName',
                    data: $("#collect-form").serialize(),
                    success: function (res) {
                        layer.msg("操作成功！", {
                            time: 2000
                        }, function () {
                            if (res.code == 200) {
                                location.href = "/menu/"+userId
                            }
                        })
                    }
                });
            });
        });

        layui.use(['upload', 'element', 'layer'], function(){
            var $ = layui.jquery
                ,upload = layui.upload
                ,element = layui.element
                ,layer = layui.layer;

            upload.render({
                elem: '#test10'
                ,url: '/updateInfo' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
                ,done: function(res){
                    layer.msg('上传成功');
                    layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', res.data);
                    console.log(res)
                }
            });

        });

    </script>

</@Layout.out>