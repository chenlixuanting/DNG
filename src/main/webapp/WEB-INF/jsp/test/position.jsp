<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>滴滴定位示意图</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <style>
        #container {
            width: 1910px;
            height: 1000px;
        }
    </style>
</head>
<body>
<div id="container"></div>
</body>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.11&key=601e81fa506625f0c4661a0f56243eab&plugin=AMap.Geocoder"></script>
<script>
    $(function () {

        var geocoder = new AMap.Geocoder({
            radius: 1000,
            extensions: "all"
        });

        function regeocoder(position) {  //逆地理编码
            geocoder.getAddress(position, function (status, result) {
                if (status === 'complete' && result.info === 'OK') {
                    geocoder_CallBack(result);
                }
            });
        }

        function geocoder_CallBack(data) {
            var address = data.regeocode.formattedAddress; //返回地址描述
            console.log(address);
        }

        $.ajax({
            url: "device/user/pdata/" + "a01b8439e1e42ffcd286241b04d9b1b5",
            contentType: "application/json",
            method: "get",
            success: function (d) {
                var data = eval(d);
                var position = eval(data.positions);
                var x = 0;
                var marker;
                var map;
                if (x == 0) {
                    map = new AMap.Map('container', {
                        zoom: 11,//级别
                        center: [position[x].longitude, position[x].latitude],//中心点坐标
                        viewMode: '3D'//使用3D视图
                    });
                }
                var interval = setInterval(function () {
                    marker = new AMap.Marker({
                        position: [position[x].longitude, position[x].latitude]//位置
                    });
                    map.add(marker);//添加到地图
                    x++;
                    regeocoder([position[x].longitude, position[x].latitude]);
                }, 1000);
            }
        });

    });
</script>
</html>
