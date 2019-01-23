<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <title>用户管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        .introduce-table td {
            height: 50px;
        }

        .introduce-table td label {
            padding-left: 5px;
            font-family: 楷体;
            font-size: 16px;
        }

        .introduce-table td label span {
            padding-left: 5px;
            font-family: 楷体;
            font-size: 16px;
        }
    </style>

</head>
<body class="app sidebar-mini rtl">

<!-- 包含页面头部 -->
<jsp:include page="page-header.jsp" flush="true"/>

<!-- 包含页面底部 -->
<jsp:include page="page-sidebar.jsp" flush="true"/>

<main class="app-content" style="display: block;" id="user-manage-main">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> 用户管理</h1>
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
                    <button class="btn btn-info btn-sm" type="button" style="float: right;" id="searchBtn">搜索
                    </button>
                    <table class="table table-hover table-bordered" id="userTable"
                           style="width:100%;text-align: center"></table>
                </div>
            </div>
        </div>
    </div>
</main>

<main class="app-content" style="display: none;" id="user-introduce-main">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> 用户管理</h1>
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
                    <div class="col-md-12">
                        <button class="btn btn-info btn-sm" id="returnBtn">返回</button>
                    </div>
                    <div class="col-md-12" style="padding-top: 10px;">
                        <table class="introduce-table" border="1px;"
                               style="width:50%;border:1px solid #dee2e6;margin:0px auto;">
                            <tbody>
                            <tr>
                                <td style="width: 60%;"><label>账号:<span id="introduce_name"></span></label></td>
                                <td rowspan="4">
                                    <img id="introduce_head_pic" src="" style="width: 100%;height: 260px;"
                                         class="img-thumbnail">
                                </td>
                            </tr>
                            <tr>
                                <td><label>密码:<span id="introduce_password"></span></label></td>
                            </tr>
                            <tr>
                                <td><label>真实姓名:</label><span id="introduce_username"></span></td>
                            </tr>
                            <tr>
                                <td><label>性别:<span id="introduce_sex"></span></label></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div>
                                        <label>身份证正面:</label>
                                    </div>
                                    <div>
                                        <img id="introduce_id_card_front" src=""
                                             style="width: 80%;height: 350px;float: right;" class="img-thumbnail">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div>
                                        <label>身份证反面:</label>
                                    </div>
                                    <div>
                                        <img id="introduce_id_card_reverse" src=""
                                             style="width: 80%;height: 350px;float: right;" class="img-thumbnail">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td><label>用户ID:<span id="introduce_userId"></span></label></td>
                                <td><label>产品密钥:<span id="introduce_cdKey"></span></label></td>
                            </tr>
                            <tr>
                                <td><label>出生年月日:<span id="introduce_birthday"></span></label></td>
                                <td><label>电话号码:<span id="introduce_mobile"></span></label></td>
                            </tr>
                            <tr>
                                <td>
                                    <div>
                                        <label>身份证号码:<span id="introduce_id_card_number"></span></label>
                                    </div>
                                </td>
                                <td><label>车牌号码:<span id="introduce_plate_number"></span></label></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div>
                                        <label>驾驶证:</label>
                                    </div>
                                    <div>
                                        <img id="introduce_driver_license" src=""
                                             style="width: 80%;height: 350px;float: right;" class="img-thumbnail">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div>
                                        <label>行车证:</label>
                                    </div>
                                    <div>
                                        <img id="introduce_driver_permit" src=""
                                             style="width: 80%;height: 350px;float: right;" class="img-thumbnail">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><label>注册时间:<span id="introduce_create_time"></span></label></td>
                            </tr>
                            <tr>
                                <td colspan="2"><label>更新时间:<span id="introduce_update_time"></span></label></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Essential javascripts for application to work-->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="js/plugins/pace.min.js"></script>
<!-- Page specific javascripts-->
<!-- Data table plugin-->
<script src="layer/layer.js"></script>
<script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="js/plugins/sweetalert.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/common/common.js"></script>
<script type="text/javascript">
    var table;
    $(function () {

        $("#returnBtn").click(function () {
            $("#user-manage-main").css("display", "block");
            $("#user-introduce-main").css("display", "none");
        });

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

        //更新设备记录
        $("#updateSubmit").click(function () {
            var device = {
                deviceId: $("#edit_device_id").val(),
                deviceVersion: $("#edit_product_version").val(),
                deviceName: $("#edit_device_name").val(),
                cdKey: $("#edit_cdKey").val(),
                createTime: $("#edit_create_date").val()
            };
            //开始界面loading特效
            var loadingEffects = layer.load(2);
            $.ajax({
                url: "administrator/device/update",
                type: "post",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(device),
                success: function (d) {
                    layer.close(loadingEffects);
                    var data = eval(d);
                    if (data.statusCode == 200) {
                        layer.msg("更新设备成功!", {
                            icon: 1,
                            time: 1500
                        });
                        table.api().draw(false);
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
                    $("#editModel").modal("hide");
                }, error: function () {
                    layer.close(loadingEffects);
                    $("#editModel").modal("hide");
                    layer.msg("请求操作失败!", {
                        icon: 2,
                        time: 1500
                    });
                }
            });
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
                        table.api().draw(false);
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

        table = $("#userTable").dataTable({
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
            sAjaxSource: "administrator/user/page",//这个是请求的地址
            fnServerData: retrieveData, // 获取数据的处理函数
            columns: [
                {data: "account"},
                {data: "username"},
                {data: "sex"},
                {data: "plateNumber"},
                {data: "createTime"},
                {data: "updateTime"},
                {data: null}
            ],
            columnDefs: [
                {//列渲染，可以添加一些操作等
                    targets: 0,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "用户账号"
                },
                {//列渲染，可以添加一些操作等
                    targets: 1,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "用户姓名"
                },
                {//列渲染，可以添加一些操作等
                    targets: 2,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "用户性别"
                },
                {//列渲染，可以添加一些操作等
                    targets: 3,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "车牌号"
                },
                {//列渲染，可以添加一些操作等
                    targets: 4,//表示是第8列，所以上面第8列没有对应数据列，就是在这里渲染的。
                    title: "注册日期",
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
                        return "<button class='btn btn-success btn-sm check' type='button' name='" + row.userId + "'>查看</button>" + "&nbsp;&nbsp;" +
                            "<button class='btn btn-primary btn-sm edit' type='button' name='" + row.userId + "'>编辑</button>" + "&nbsp;&nbsp;" +
                            "<button class='btn btn-danger btn-sm del' type='button' name='" + row.userId + "'>删除</button>";
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
                            url: "administrator/device/get/" + this.name,
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
                    $(".del").on("click", function () {
                        var userId = this.name;
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
                                $.ajax({
                                    url: "administrator/user/" + userId,
                                    data: {_method: "delete"},
                                    type:"post",
                                    dataType:"json",
                                    success: function (res) {
                                        var data = eval(res);
                                        if (data.statusCode == 200) {
                                            swal("删除成功!", "你选择的记录已被删除.", "success");
                                            table.api().draw(false);
                                        } else {
                                            swal("删除失败!", "删除操作未执行成功.", "error");
                                        }
                                    }
                                });
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

        /**
         * 查看用户的详细信息
         */
        $(".check").click(function () {
            var userId = this.name;
            //开始界面loading特效
            var loadingEffects = layer.load(2);
            $.ajax({
                url: "administrator/user/" + userId,
                type: "get",
                dataType: "json",
                contentType: "application/json",
                success: function (d) {
                    layer.close(loadingEffects);
                    var data = eval(d);
                    if (data.statusCode == 200) {
                        var user = eval(data.user);
                        $("#introduce_name").text(user.account);
                        $("#introduce_password").text(user.password);
                        $("#introduce_username").text(user.username);
                        $("#introduce_sex").text(user.sex);
                        $("#introduce_userId").text(user.userId);
                        $("#introduce_cdKey").text(user.cdKey);
                        $("#introduce_birthday").text(dateFormatter(user.birthday));
                        $("#introduce_mobile").text(user.mobile)
                        $("#introduce_id_card_number").text(user.idCardNumber);
                        $("#introduce_plate_number").text(user.plateNumber);
                        $("#introduce_create_time").text(dateFormatter(user.createTime));
                        $("#introduce_update_time").text(dateFormatter(user.updateTime));
                        $("#introduce_head_pic").attr("src", user.headPic);
                        $("#introduce_id_card_front").attr("src", user.idCardFrontPic);
                        $("#introduce_id_card_reverse").attr("src", user.idCardReversePic);
                        $("#introduce_driver_license").attr("src", user.driverLicensePic);
                        $("#introduce_driver_permit").attr("src", user.driverPermitPic);
                        $("#user-manage-main").css("display", "none");
                        $("#user-introduce-main").css("display", "block");
                    } else {
                        alert("获取信息失败")
                    }
                }, error: function (d) {
                    layer.close(loadingEffects);
                }
            });

        });

    });
</script>
</body>
</html>