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
</head>
<body>
<div id="container" style="width: 100%;height: 100%;">
    <input type="button" id="send" value="提交">
</div>
</body>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script src="layer/layer.js"></script>
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.11&key=601e81fa506625f0c4661a0f56243eab&plugin=Map3D"></script>
<script>
    $(function () {
        var map;
        //开始界面loading特效
        var loadingEffects = layer.load(2);
        $.ajax({
            url:"mobile/user/check-route-plan",
            type:"get",
            success:function (d) {
                var data = eval(d);
                var passPoints = eval(data.speedRoutePlan.passPoints);
                for(var i=0;i<passPoints.length;i++){

                    var longitude = passPoints[i].longitude;
                    var latitude = passPoints[i].latitude;

                    if(i == 0){
                        map = new AMap.Map('container',{
                            zoom:12,
                            center:[longitude,latitude]
                        });
                    }else{
                        // 创建一个 Marker 实例：
                        var marker = new AMap.Marker({
                            position: new AMap.LngLat(longitude, latitude),   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                        });
                        // 将创建的点标记添加到已有的地图实例：
                        map.add(marker);
                    }

                }
                //关闭loading特效
                layer.close(loadingEffects);
            }
        });
    });
</script>
</html>

