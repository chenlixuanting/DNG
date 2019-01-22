<%@ page import="com.guet.navigator.web.pojo.Administrator" %>
<%@ page import="com.guet.navigator.web.constant.administrator.AdministratorConstant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Administrator administrator = (Administrator) request.getSession().getAttribute(AdministratorConstant.ADMINISTRATOR);
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="<%=administrator.getHeadPic()%>"
                                        width="50px" height="50px" alt="User Image">
        <div>
            <p class="app-sidebar__user-name"><%=administrator.getUsername()%>
            </p>
            <p class="app-sidebar__user-designation"><%=administrator.getPosition()%>
            </p>
        </div>
    </div>
    <ul class="app-menu">
        <li><a class="app-menu__item" href="administrator/index"><i class="app-menu__icon fa fa-dashboard"></i><span
                class="app-menu__label">首页</span></a></li>
        <li class="treeview"><a class="app-menu__item" href="/index" data-toggle="treeview"><i
                class="app-menu__icon fa fa-user"></i><span class="app-menu__label">用户管理</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="bootstrap-components.html"><i
                        class="icon fa fa-circle-o"></i>正式用户</a></li>
                <li><a class="treeview-item" href="https://fontawesome.com/v4.7.0/icons/" target="_blank"
                       rel="noopener"><i class="icon fa fa-circle-o"></i>待审核用户</a></li>
            </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="/index" data-toggle="treeview"><i
                class="app-menu__icon fa fa-car"></i><span class="app-menu__label">设备管理</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="administrator/device-manage"><i
                        class="icon fa fa-circle-o"></i>服役产品</a></li>
                <li><a class="treeview-item" href="https://fontawesome.com/v4.7.0/icons/" target="_blank"
                       rel="noopener"><i class="icon fa fa-circle-o"></i>待审核用户</a></li>
            </ul>
        </li>
        <li><a class="app-menu__item" href="charts.html"><i class="app-menu__icon fa fa-pie-chart"></i><span
                class="app-menu__label">用户数据统计</span></a></li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-edit"></i><span class="app-menu__label">搜集测试数据</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="form-components.html"><i class="icon fa fa-circle-o"></i> Form
                    Components</a></li>
                <li><a class="treeview-item" href="form-custom.html"><i class="icon fa fa-circle-o"></i> Custom
                    Components</a></li>
                <li><a class="treeview-item" href="form-samples.html"><i class="icon fa fa-circle-o"></i> Form
                    Samples</a></li>
                <li><a class="treeview-item" href="form-notifications.jsp"><i class="icon fa fa-circle-o"></i> Form
                    Notifications</a></li>
            </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-th-list"></i><span class="app-menu__label">测试模型</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="table-basic.html"><i class="icon fa fa-circle-o"></i> Basic
                    Tables</a></li>
                <li><a class="treeview-item" href="user-manage.jsp"><i class="icon fa fa-circle-o"></i> Data Tables</a>
                </li>
            </ul>
        </li>
        <li class="treeview is-expanded"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fa fa-file-text"></i><span class="app-menu__label">系统设置</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="blank-page.html"><i class="icon fa fa-circle-o"></i> Blank Page</a>
                </li>
                <li><a class="treeview-item" href="backup/page-login.html"><i class="icon fa fa-circle-o"></i> Login
                    Page</a></li>
                <li><a class="treeview-item" href="page-lockscreen.html"><i class="icon fa fa-circle-o"></i> Lockscreen
                    Page</a></li>
                <li><a class="treeview-item active" href="administrator-details.jsp"><i class="icon fa fa-circle-o"></i>
                    User Page</a></li>
                <li><a class="treeview-item" href="page-invoice.html"><i class="icon fa fa-circle-o"></i> Invoice
                    Page</a></li>
                <li><a class="treeview-item" href="page-calendar.html"><i class="icon fa fa-circle-o"></i> Calendar Page</a>
                </li>
                <li><a class="treeview-item" href="page-mailbox.html"><i class="icon fa fa-circle-o"></i> Mailbox</a>
                </li>
                <li><a class="treeview-item" href="page-error.html"><i class="icon fa fa-circle-o"></i> Error Page</a>
                </li>
            </ul>
        </li>
    </ul>
</aside>
