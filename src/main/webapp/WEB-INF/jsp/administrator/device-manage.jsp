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
                    <table class="table table-hover table-bordered" style="text-align: center;" id="sampleTable">
                        <thead>
                            <th>设备ID</th>
                            <th>产品版本</th>
                            <th>设备名称</th>
                            <th>产品密钥</th>
                            <th>生产日期</th>
                            <th>更新日期</th>
                            <th>操作</th>
                        </thead>
                        <tbody>
                            <c:forEach var="device" items="${devices}" varStatus="status">
                                <tr>
                                    <td>${device.deviceId}</td>
                                    <td>${device.deviceVersion}</td>
                                    <td>${device.deviceName}</td>
                                    <td>${device.cdKey}</td>
                                    <td><fmt:formatDate value="${device.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td><fmt:formatDate value="${device.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>
                                        <button class="btn btn-primary btn-sm" type="button" name="${device.deviceId}" index="${status.index}" onclick="openEditModel(this.name)">编辑</button>
                                        <button class="btn btn-danger btn-sm" type="button" name="${device.deviceId}" index="${status.index}" onclick="delFun(this.name)">删除</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
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
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
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
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="add_device_id">设备ID</label>
                            <input class="form-control" id="add_device_id" type="text" placeholder="请输入设备ID" >
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
                            <input class="form-control" id="add_cdKey" type="text" placeholder="请输入产品密钥" >
                        </div>
                        <div class="form-group">
                            <label for="add_create_date">生产日期</label>
                            <span>
                                <input class="form-control" id="add_create_date" type="text" placeholder="请输入生产日期 格式:yyyy-MM-dd HH:mm:ss">
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
<script type="text/javascript">$("#sampleTable").dataTable();</script>
<script type="text/javascript">

    /**
     * 通过ajax获取知道设备信息，并打开编辑框
     */
    var openEditModel = function (deviceId) {
        $.ajax({
            url:"administrator/device/query/"+deviceId,
            success:function (d) {
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
    };

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
            deviceId:$("#add_device_id").val(),
            deviceVersion:$("#add_product_version").val(),
            deviceName:$("#add_device_name").val(),
            cdKey:$("#add_cdKey").val(),
            createTime:$("#add_create_date").val()
        };
        //开始界面loading特效
        var loadingEffects = layer.load(2);
        $.ajax({
            url:"administrator/device/add",
            type:"post",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(device),
            success:function (d) {
                layer.close(loadingEffects);
                var data = eval(d);
                if(data.statusCode == 200){
                    layer.msg("新增设备成功!",{
                        icon: 1,
                        time: 1500
                    });
                    var mark = $("tr").length;
                    $("tbody").append(
                        "<tr>" +
                            "<td>"+data.device.deviceId+"</td>"+
                            "<td>"+data.device.deviceVersion+"</td>"+
                            "<td>"+data.device.deviceName+"</td>"+
                            "<td>"+data.device.cdKey+"</td>"+
                            "<td>"+dateFormatter(data.device.createTime)+"</td>"+
                            "<td>"+dateFormatter(data.device.updateTime)+"</td>"+
                            "<td>"+
                                "<button class='btn btn-primary btn-sm' type='button' name="+data.device.deviceId+" index="+mark+" onclick='openEditModel(this.name)'>编辑</button>"+
                                "<button class='btn btn-danger btn-sm' type='button' name="+data.device.deviceId+" index="+mark+" onclick='delFun(this.name)'>删除</button>"+
                            "</td>"+
                        "</tr>");
                }else if(data.statusCode == 300){
                    layer.msg("数据存在空项!",{
                        icon: 2,
                        time: 1500
                    });
                }else{
                    layer.msg("服务器内部错误!",{
                        icon: 2,
                        time: 1500
                    });
                }
                $("#addModel").modal("hide");
            },error:function () {
                layer.close(loadingEffects);
                $("#addModel").modal("hide");
                layer.msg("请求操作失败!",{
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
            url:"administrator/device/query/"+deviceId,
            success:function (res) {
            }
        });
    };

    //删除按钮功能
    var delFun = function(deviceId){
        swal({
            title: "您确定要删除吗?",
            text: "删除的记录将无法立即找回!",
            type: "warning",
            showCancelButton: true,
            confirmButtonText: "确定删除!",
            cancelButtonText: "取消删除!",
            closeOnConfirm: false,
            closeOnCancel: false
        }, function(isConfirm) {
            if (isConfirm) {
                swal("删除成功!", "你选择的记录已被删除.", "success");
            } else {
                swal("删除取消", "你的删除操作被取消 :)", "error");
            }
        });
    };
</script>

</body>
</html>