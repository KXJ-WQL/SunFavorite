<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录 - 空想家</title>
    <link rel="icon" type="image/x-icon" href="/images/favicon.icon">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">
    <script src="/js/jquery-3.6.0.min.js"></script>
</head>
<body class="text-center">

<main class="form-signin">
    <form>
        <img class="mb-4"
             src="/images/logo.png"
             alt="" width="72" height="72"  style="border-radius: 15%;">
        <h1 class="h3 mb-3 fw-normal">阅读收藏 - 空想家smile</h1>

        <img src="/images/kxjweix.jpg" alt="公众号：空想家Smile">
        <div class="mt-2 mb-2 text-muted">
            <form class="form-inline">
                <div class="form-group">
                    <label for="exampleInputEmail2"> 输入登录验证码：</label>
                    <input type="text" class="form-control" id="exampleInputName2" placeholder="*****">
                </div>
                <button type="button" id="sub" class="btn btn-primary">登录</button>
            </form>
        </div>
        <p class="text-muted">扫码关注公众号，输入验证码即可获取</p>
    </form>
</main>

<script>
    $("#sub").click(function () {
          var yzcode = $("#exampleInputName2").val();
          var obj={
              yzcode:yzcode
          }
        $.ajax({
            type:"post",
            url:"/login",
            data:obj,
            dataType:"json",
            success:function (result) {
                if (result.code==200){
                    var userid=result.data
                    window.location.href ="/menu/"+userid;
                }else {
                    alert("登录失败,验证码失效或错误");
                }
            },
            error:function (result) {
                alert("未知错误");
            }
        });
        })

</script>
</body>
</html>