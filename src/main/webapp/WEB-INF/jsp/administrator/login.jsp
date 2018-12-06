<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>领航员-后台登录界面</title>
</head>

<body>
    <section class="material-half-bg">
        <div class="cover"></div>
    </section>
    <section class="login-content">

        <div class="logo">
            <h1>Driver-Navigator</h1>
        </div>

        <!-- 访客id -->
        <input id="guestCode" style="display: none;" value="">

        <div class="login-box">
            <div class="login-form">
                <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>SIGN IN</h3>
                <div class="form-group">
                    <label class="control-label">USERNAME</label>
                    <input class="form-control" type="text" placeholder="Username" id="username" autofocus>
                </div>
                <div class="form-group">
                    <label class="control-label">PASSWORD</label>
                    <input class="form-control" type="password" placeholder="Password" id="password">
                </div>
                <div class="form-group">
                    <div class="utility">
                        <div class="animated-checkbox">
                            <label>
                                <input type="checkbox"><span class="label-text">Stay Signed in</span>
                            </label>
                        </div>
                        <p class="semibold-text mb-2"><a href="#" data-toggle="flip">Forgot Password ?</a></p>
                    </div>
                </div>
                <div class="form-group btn-container">
                    <button class="btn btn-primary btn-block" id="signBtn"><i class="fa fa-sign-in fa-lg fa-fw"></i>SIGN IN</button>
                </div>
            </div>

            <div class="forget-form">
                <h3 class="login-head"><i class="fa fa-lg fa-fw fa-lock"></i>Forgot Password ?</h3>
                <div class="form-group">
                    <label class="control-label">EMAIL</label>
                    <input class="form-control" type="text" placeholder="Email">
                </div>
                <div class="form-group btn-container">
                    <button class="btn btn-primary btn-block"><i class="fa fa-unlock fa-lg fa-fw"></i>RESET</button>
                </div>
                <div class="form-group mt-3">
                    <p class="semibold-text mb-0"><a href="#" data-toggle="flip"><i class="fa fa-angle-left fa-fw"></i> Back to Login</a></p>
                </div>
            </div>
        </div>

    </section>

    <!-- Essential javascripts for application to work-->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
    <script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="js/plugins/pace.min.js"></script>

    <script src="layer/layer.js"></script>

    <script type="text/javascript">
        // Login Page Flipbox control
        $('.login-content [data-toggle="flip"]').click(function() {
            $('.login-box').toggleClass('flipped');
            return false;
        });
    </script>

    <script type="text/javascript">
        $(function () {

            var websocket;

            // 首先判断是否 支持 WebSocket
            if('WebSocket' in window) {
                websocket = new WebSocket("ws://localhost:8080/navigator/common/websocket/connect");
            } else if('MozWebSocket' in window) {
                websocket = new MozWebSocket("ws://n227u79429.imwork.net/navigator/device/user/websocket");
            } else {
                websocket = new SockJS("http://n227u79429.imwork.net/navigator/sockjs/websocket");
            }

            // 打开时
            websocket.onopen = function(evnt) {
                console.log("  websocket.onopen  ");
            };

            // 处理消息时
            websocket.onmessage = function(evnt) {
                $("#msg").append("<p>(<font color='red'>" + evnt.data + "</font>)</p>");
                console.log("  websocket.onmessage   ");
            };

            websocket.onerror = function(evnt) {
                console.log("  websocket.onerror  ");
            };

            websocket.onclose = function(evnt) {
                console.log("  websocket.onclose  ");
            };

            // 点击了发送消息按钮的响应事件
            $("#signBtn").click(function(){

                // 获取消息内容
                var text = $("#username").val();

                // 判断
                if(text == null || text == ""){
                    alert(" content  can not empty!!");
                    return false;
                }

                var msg = {
                    msgContent: text,
                    postsId: 1
                };

                // 发送消息
                websocket.send(JSON.stringify(msg));

            });

            $("#signBtn").click(function(){

                //将登录数据封装称js对象
                var loginData={
                    username:$("#username").val(),
                    password:$("#password").val()
                };

                //开始界面loading特效
                var loadingEffects = layer.load(2);

                //使用ajax异步请求发生登录信息的json格式数据
                $.ajax({
                    type:"post",
                    url:"administrator/loginValidate",
                    dataType:"json",
                    contentType:"application/json;charset=utf-8",
                    data: JSON.stringify(loginData),
                    success:function (d) {

                        //关闭loading特效
                        layer.close(loadingEffects);
                        var data = eval(d);

                        if(data.head){
                            layer.msg(data.body,{
                                icon: 1,
                                time: 1500
                            });
                            window.location.href="administrator/index";
                        }else{
                            layer.msg(data.body,{
                                icon: 2,
                                time: 1500
                            });
                        }

                    }, error:function (d) {
                        layer.close(loadingEffects);
                        layer.msg("登录失败!");
                    }
                });

////            $.notify({
////                title: "Update Complete : ",
////                message: "Something cool is just updated!",
////                icon: 'fa fa-check'
////            },{
////                type: "danger"
////            });
//                action="" method="post"
//                var ii = layer.load();
//
//                //此处用setTimeout演示ajax的回调
//                setTimeout(function(){
//                    layer.close(ii);
//                }, 1000);

//                layer.msg('hello');

            });
        });
    </script>

</body>
</html>
