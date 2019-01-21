// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
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

function dateFormatter(value) {
    var result = new Date(value).Format("yyyy-MM-dd hh:mm:ss");
    return result;
}

/*
*
* <%--<script type="text/javascript">--%>
    <%--$(function () {--%>
        <%--var table = $("#sampleTable").dataTable({--%>
            <%--paging: true,//分页--%>
            <%--ordering: true,//是否启用排序--%>
            <%--bLengthChange: true,--%>
            <%--bPaginate: true,  //翻页功能--%>
            <%--searching: true,//搜索--%>
            <%--language: {--%>
                <%--paginate: {//分页的样式内容。--%>
                    <%--previous: "上一页",--%>
                    <%--next: "下一页",--%>
                    <%--first: "第一页",--%>
                    <%--last: "最后"--%>
                <%--},--%>
                <%--zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。--%>
                <%--//下面三者构成了总体的左下角的内容。--%>
                <%--info: "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。--%>
                <%--infoEmpty: "0条记录",//筛选为空时左下角的显示。--%>
                <%--infoFiltered: ""//筛选之后的左下角筛选提示，--%>
            <%--},--%>
            <%--processing: true,//设置为true,就会有表格加载时的提示--%>
            <%--serverSide: true,--%>
            <%--sAjaxSource: "administrator/device/query",//这个是请求的地址--%>
            <%--fnServerData: retrieveData, // 获取数据的处理函数--%>
            <%--columns: [--%>
                <%--{data: null},--%>
                <%--{data: "deviceId"},--%>
                <%--{data: "deviceVersion"},--%>
                <%--{data: "deviceName"},--%>
                <%--{data: "cdKey"},--%>
                <%--{data: "createTime"},--%>
                <%--{data: "updateTime"},--%>
                <%--{data: null}--%>
            <%--],--%>
            <%--columnDefs: [--%>
                <%--{//列渲染，可以添加一些操作等--%>
                    <%--targets: 0,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。--%>
                    <%--title: "选择栏",--%>
                    <%--render: function (obj) {--%>
<%--//                        return "<input type='checkbox' class='checkbox' name=" + id + "></input>";--%>
                        <%--return "<div class=\"animated-checkbox\">\n" +--%>
                            <%--"<label>\n" +--%>
                            <%--"<input type=\"checkbox\"><span class=\"label-text\"></span>\n" +--%>
                            <%--"</label>\n" +--%>
                            <%--"</div>";--%>
                    <%--}--%>
                <%--},--%>
                <%--{//列渲染，可以添加一些操作等--%>
                    <%--targets: 1,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。--%>
                    <%--title: "设备ID"--%>
                <%--},--%>
                <%--{//列渲染，可以添加一些操作等--%>
                    <%--targets: 2,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。--%>
                    <%--title: "产品版本"--%>
                <%--},--%>
                <%--{//列渲染，可以添加一些操作等--%>
                    <%--targets: 3,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。--%>
                    <%--title: "设备名称"--%>
                <%--},--%>
                <%--{//列渲染，可以添加一些操作等--%>
                    <%--targets: 4,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。--%>
                    <%--title: "产品密钥"--%>
                <%--},--%>
                <%--{//列渲染，可以添加一些操作等--%>
                    <%--targets: 5,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。--%>
                    <%--title: "生产日期",--%>
                    <%--render: function (obj) {--%>
                        <%--return dateFormatter(obj);--%>
                    <%--}--%>
                <%--},--%>
                <%--{//列渲染，可以添加一些操作等--%>
                    <%--targets: 6,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。--%>
                    <%--title: "更新日期",--%>
                    <%--render: function (obj) {--%>
                        <%--return dateFormatter(obj);--%>
                    <%--}--%>
                <%--},--%>
                <%--{//列渲染，可以添加一些操作等--%>
                    <%--targets: 7,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。--%>
                    <%--title: "操作",--%>
                    <%--render: function (obj) {--%>
                        <%--var id = obj.cid;--%>
                        <%--return "<button class=\"btn btn-primary btn-sm\" type=\"button\">编辑</button>" + "&nbsp;&nbsp;" +--%>
                            <%--"<button class=\"btn btn-danger btn-sm\" type=\"button\">删除</button>";--%>
                    <%--}--%>
                <%--}],--%>
            <%--pagingType: "full_numbers"//分页样式的类型--%>
        <%--});--%>

        <%--function retrieveData(sSource111, aoData111, fnCallback111) {--%>
            <%--$.ajax({--%>
                <%--url: sSource111,//这个就是请求地址对应sAjaxSource--%>
                <%--data: {"aoData": JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数--%>
                <%--type: 'get',--%>
                <%--dataType: 'json',--%>
                <%--async: false,--%>
                <%--success: function (result) {--%>
                    <%--fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的--%>
<%--//                    $(".check").on('click', check);--%>
<%--//                    $(".edit").on('click', edit);--%>
                <%--},--%>
                <%--error: function (msg) {--%>
                <%--}--%>
            <%--});--%>
        <%--}--%>

    <%--});--%>
<%--</script>--%>
* */