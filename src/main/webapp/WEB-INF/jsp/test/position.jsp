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
<div id="container">
    <input type="button" id="send" value="提交">
</div>
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

        $("#send").click(function () {
            $.ajax({
                url: "device/user/position",
                contentType: "application/json",
                method: "post",
                data: "deviceId:2,Longitude:110.412368774414060,latitude:25.316579818725586,speed:0,currentTime:"+"\"" + dateFormatter(new Date().getTime())+"\"",
                success: function (d) {
                }
            });
        });

        Date.prototype.Format = function (fmt) { //author: meizz
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }

        function dateFormatter(value, row, index) {
            var result = new Date(value).Format("yyyy-MM-dd hh:mm:ss");
            return result;
        }

    });
</script>
</html>

