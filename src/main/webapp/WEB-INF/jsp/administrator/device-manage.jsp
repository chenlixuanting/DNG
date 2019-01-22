<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <title>设备管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/layui.css">
</head>
<body class="app sidebar-mini rtl">
<!-- 包含页面头部 -->
<jsp:include page="page-header.jsp" flush="true"/>
<!-- 包含页面底部 -->
<jsp:include page="page-sidebar.jsp" flush="true"/>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> 设备管理</h1>
            <p>Table to display analytical data effectively</p>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active"><a href="#">Data Table</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <button class="btn btn-success btn-sm" type="button" style="float: left;" id="addBtn">新增</button>
                    <table class="table table-hover table-bordered" style="text-align: center; width: 100%;"
                           id="sampleTable">
                    </table>
                </div>
            </div>
        </div>
    </div>

    <%--编辑模态框--%>
    <div class="modal fade" id="editModel" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">编辑</h3>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="edit_device_id">设备ID</label>
                            <input class="form-control" id="edit_device_id" type="text" placeholder="请输入设备ID" readonly>
                        </div>
                        <div class="form-group">
                            <label for="edit_product_version">产品版本</label>
                            <input class="form-control" id="edit_product_version" type="text" placeholder="请输入产品版本号">
                        </div>
                        <div class="form-group">
                            <label for="edit_device_name">设备名称</label>
                            <input class="form-control" id="edit_device_name" type="text" placeholder="请输入产品名称">
                        </div>
                        <div class="form-group">
                            <label for="edit_cdKey">产品密钥</label>
                            <input class="form-control" id="edit_cdKey" type="text" placeholder="请输入产品密钥">
                        </div>
                        <div class="form-group">
                            <label for="edit_create_date">生产日期</label>
                            <input class="form-control" id="edit_create_date" type="text" readonly>
                        </div>
                        <div class="form-group">
                            <label for="edit_update_date">更新日期</label>
                            <input class="form-control" id="edit_update_date" type="text" readonly>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button" id="updateSubmit">保存</button>
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

    <%--新增模态框--%>
    <div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">新增</h3>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="add_device_id">设备ID</label>
                            <input class="form-control" id="add_device_id" type="text" placeholder="请输入设备ID">
                        </div>
                        <div class="form-group">
                            <label for="add_product_version">产品版本</label>
                            <input class="form-control" id="add_product_version" type="text" placeholder="请输入产品版本号">
                        </div>
                        <div class="form-group">
                            <label for="add_device_name">设备名称</label>
                            <input class="form-control" id="add_device_name" type="text" placeholder="请输入产品名称">
                        </div>
                        <div class="form-group">
                            <label for="add_cdKey">产品密钥</label>
                            <input class="form-control" id="add_cdKey" type="text" placeholder="请输入产品密钥">
                        </div>
                        <div class="form-group">
                            <label for="add_create_date">生产日期</label>
                            <span>
                                <input class="form-control" id="add_create_date" type="text"
                                       placeholder="请输入生产日期 格式:yyyy-MM-dd HH:mm:ss">
                            </span>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button" id="addSubmit">保存</button>
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

</main>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<script src="js/plugins/pace.min.js"></script>
<script src="layer/layer.js"></script>
<script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="js/plugins/sweetalert.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/common/common.js"></script>
<script type="text/javascript">

    //添加按钮
    $("#addBtn").click(function () {
        //清空新增模态框中的内容
        $("#add_device_id").val("");
        $("#add_product_version").val("");
        $("#add_device_name").val("");
        $("#add_cdKey").val("");
        $("#add_create_date").val("");
        $("#addModel").modal("show");
    });

    //添加新设备
    $("#addSubmit").click(function () {
        var device = {
            deviceId: $("#add_device_id").val(),
            deviceVersion: $("#add_product_version").val(),
            deviceName: $("#add_device_name").val(),
            cdKey: $("#add_cdKey").val(),
            createTime: $("#add_create_date").val()
        };
        //开始界面loading特效
        var loadingEffects = layer.load(2);
        $.ajax({
            url: "administrator/device/add",
            type: "post",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(device),
            success: function (d) {
                layer.close(loadingEffects);
                var data = eval(d);
                if (data.statusCode == 200) {
                    layer.msg("新增设备成功!", {
                        icon: 1,
                        time: 1500
                    });
                } else if (data.statusCode == 300) {
                    layer.msg("数据存在空项!", {
                        icon: 2,
                        time: 1500
                    });
                } else {
                    layer.msg("服务器内部错误!", {
                        icon: 2,
                        time: 1500
                    });
                }
                $("#addModel").modal("hide");
            }, error: function () {
                layer.close(loadingEffects);
                $("#addModel").modal("hide");
                layer.msg("请求操作失败!", {
                    icon: 2,
                    time: 1500
                });
            }
        });

    });

    /**
     * 通过deviceId删除设备记录
     */
    var openDelModel = function (deviceId) {
        $.ajax({
            url: "administrator/device/query/" + deviceId,
            success: function (res) {
            }
        });
    };

    $(function () {
        var table = $("#sampleTable").dataTable({
            paging: true,//分页
            ordering: true,//是否启用排序
            bLengthChange: true,
            bPaginate: true,  //翻页功能
            searching: true,//搜索
            language: {
                paginate: {//分页的样式内容。
                    previous: "上一页",
                    next: "下一页",
                    first: "第一页",
                    last: "最后"
                },
                zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
//下面三者构成了总体的左下角的内容。
                info: "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
                infoEmpty: "0条记录",//筛选为空时左下角的显示。
                infoFiltered: ""//筛选之后的左下角筛选提示，
            },
            processing: true,//设置为true,就会有表格加载时的提示
            serverSide: true,
            sAjaxSource: "administrator/device/query",//这个是请求的地址
            fnServerData: retrieveData, // 获取数据的处理函数
            columns: [
                {data: "deviceId"},
                {data: "deviceVersion"},
                {data: "deviceName"},
                {data: "cdKey"},
                {data: "createTime"},
                {data: "updateTime"},
                {data: null}
            ],
            columnDefs: [
                {//列渲染，可以添加一些操作等
                    targets: 0,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "设备ID"
                },
                {//列渲染，可以添加一些操作等
                    targets: 1,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "产品版本"
                },
                {//列渲染，可以添加一些操作等
                    targets: 2,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "设备名称"
                },
                {//列渲染，可以添加一些操作等
                    targets: 3,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "产品密钥"
                },
                {//列渲染，可以添加一些操作等
                    targets: 4,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "生产日期",
                    render: function (obj) {
                        return dateFormatter(obj);
                    }
                },
                {//列渲染，可以添加一些操作等
                    targets: 5,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "更新日期",
                    render: function (obj) {
                        return dateFormatter(obj);
                    }
                },
                {//列渲染，可以添加一些操作等
                    targets: 6,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "操作",
                    render: function (obj, a, row, set) {
                        return "<button class='btn btn-primary btn-sm edit' type='button' name='" + row.deviceId + "'>编辑</button>" + "&nbsp;&nbsp;" +
                            "<button class='btn btn-danger btn-sm del' type='button' name='" + row.deviceId + "'>删除</button>";
                    }
                }],
            pagingType: "full_numbers"//分页样式的类型
        });

        function retrieveData(sSource111, aoData111, fnCallback111) {
            $.ajax({
                url: sSource111,//这个就是请求地址对应sAjaxSource
                data: {"aoData": JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                type: 'get',
                dataType: 'json',
                async: false,
                success: function (result) {
                    fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                    //绑定编辑按钮
                    $(".edit").on("click", function () {
                        $.ajax({
                            url: "administrator/device/query/" + this.name,
                            success: function (d) {
                                var device = eval(d);
                                $("#edit_device_id").val(device.deviceId);
                                $("#edit_product_version").val(device.deviceVersion);
                                $("#edit_device_name").val(device.deviceName);
                                $("#edit_cdKey").val(device.cdKey);
                                $("#edit_create_date").val(dateFormatter(device.createTime));
                                $("#edit_update_date").val(dateFormatter(device.updateTime));
                                $("#editModel").modal('show');
                            }
                        });
                    });

                    //绑定删除按钮
                    $(".del").on("click", function (deviceId) {
                        swal({
                            title: "您确定要删除吗?",
                            text: "删除的记录将无法立即找回!",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonText: "确定删除!",
                            cancelButtonText: "取消删除!",
                            closeOnConfirm: false,
                            closeOnCancel: false
                        }, function (isConfirm) {
                            if (isConfirm) {
                                swal("删除成功!", "你选择的记录已被删除.", "success");
                            } else {
                                swal("删除取消", "你的删除操作被取消 :)", "error");
                            }
                        });
                    });

                },
                error: function (msg) {
                }
            });
        }

    });
</script>
</body>
</html>