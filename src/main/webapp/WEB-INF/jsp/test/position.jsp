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
        src="https://webapi.amap.com/maps?v=1.4.11&key=601e81fa506625f0c4661a0f56243eab&plugin=Map3D"></script>
<script>
    $(function () {

//        var geocoder = new AMap.Geocoder({
//            radius: 1000,
//            extensions: "all"
//        });
//
//        function regeocoder(position) {  //逆地理编码
//            geocoder.getAddress(position, function (status, result) {
//                if (status === 'complete' && result.info === 'OK') {
//                    geocoder_CallBack(result);
//                }
//            });
//        }
//
//        function geocoder_CallBack(data) {
//            var address = data.regeocode.formattedAddress; //返回地址描述
//            console.log(address);
//        }

        $.ajax({
            url: "device/user/pdata",
            contentType: "application/json",
            method: "get",
            success: function (d) {
                var data = eval(d);
                var position = eval(data.positions);
                var x = 0;
                var marker;
                var map = new AMap.Map('container', {
                    zoom: 11,//级别
                    center: [position[x].startLongitude, position[x].startLatitude],//中心点坐标
                    viewMode: '3D'//使用3D视图
                });

                var object3Dlayer = new AMap.Object3DLayer({zIndex: 10});
                map.add(object3Dlayer);
                var Line3D = new AMap.Object3D.Line();
                Line3D.geometry.vertices.push(position[0].startLongitude,position[0].startLatitude, 0);
                Line3D.geometry.vertices.push(position[0].endLongitude,position[0].endLatitude, 0);
                object3Dlayer.add(Line3D);

               var marker1 = new AMap.Marker({
                    position: new AMap.LngLat(position[0].startLongitude,position[0].startLatitude)   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                });

                var marker2 = new AMap.Marker({
                    position: new AMap.LngLat(position[0].endLongitude,position[0].endLatitude)   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                });


                var marker3 = new AMap.Marker({
                    position: new AMap.LngLat(position[1].startLongitude,position[1].startLatitude)   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                });

                var marker4 = new AMap.Marker({
                    position: new AMap.LngLat(position[1].endLongitude,position[1].endLatitude)   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                });

                var marker5 = new AMap.Marker({
                    position: new AMap.LngLat(position[2].startLongitude,position[2].startLatitude)   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                });

                var marker6 = new AMap.Marker({
                    position: new AMap.LngLat(position[2].endLongitude,position[2].endLatitude)   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                });

                map.add(marker1);
//                map.add(marker2);
                map.add(marker3);
                map.add(marker4);
                map.add(marker5);
                map.add(marker6);

            }
        });

    });
</script>
</html>

