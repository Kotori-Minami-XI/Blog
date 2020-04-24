<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/pageStyle.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">管理员列表</strong><small></small></div>
<%--        <span><s:property value="USER_ACTION_ERROR"></s:property></span>--%>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="addUser_btn" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加管理员
                    </button>
                    <span style="color: red; margin-left: 10px">${USER_ACTION_ERROR}</span>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>用户</li>
        <li>修改密码</li>
        <li>移除管理员</li>
    </ul>

    <s:iterator value="userList" var="user">
        <ul class="list_goods_ul">
            <li><s:property value="#user.id"></s:property></li>
            <li><s:property value="#user.username"></s:property></li>
            <li><a href="#" class="editUser_btn"
                   data-username="<s:property value="#user.username"></s:property>"
                   data-id="<s:property value="#user.id"></s:property>">
                <img class="img_icon" src="${pageContext.request.contextPath}/images/edit_icon.png" alt=""></a>
            </li>
            <li><a href="${pageContext.request.contextPath}/userAction_deleteUser.action?id=<s:property value="#user.id"></s:property>">
                <img class="img_icon" src="${pageContext.request.contextPath}/images/delete_icon.png" alt=""></a>
            </li>
        </ul>
    </s:iterator>

</div>

<%-- Grey background for windows --%>
<div id="modal_view"></div>

<div id="modal_content_addUserWindow">
    <div id="addUser_close"><img src="${pageContext.request.contextPath}/images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加管理员：</span>
                <span id="addUser_error_info" style="color:#ee5f5b;"></span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>用户名称：</span>
                <input type="text" class="am-form-field" id="addUserWindow_username">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>设置密码：</span>
                <input type="password" class="am-form-field" id="addUserWindow_firstPwd">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>再次输入：</span>
                <input type="password" class="am-form-field" id="addUserWindow_secondPwd">&nbsp;&nbsp;
            </div>
        </div>

        <div class="item1">
            <button id="addUser_submit_btn" class="am-btn am-btn-default" type="button" >添加</button>
        </div>

    </div>
</div>

<div id="modal_content_editUserWindow">
    <div id="editUser_close"><img src="${pageContext.request.contextPath}/images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>修改管理员：</span>
                <span id="editUser_error_info" style="color:#ee5f5b;"></span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>用户名称：</span>
                <input type="text" class="am-form-field" value="" id="editUserWindow_username">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>旧的密码：</span>
                <input type="password" class="am-form-field" id="editUserWindow_oldPwd">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>新的密码：</span>
                <input type="password" class="am-form-field" id="editUserWindow_firstPwd">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>再次输入：</span>
                <input type="password" class="am-form-field" id="editUserWindow_secondPwd">&nbsp;&nbsp;
            </div>
        </div>

        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="editUser_submit_btn">修改</button>
        </div>

    </div>
</div>

<%--JSP: Clean Error info--%>
<%
    if (null != session.getAttribute("USER_ACTION_ERROR")) {
        session.setAttribute("USER_ACTION_ERROR", null);
    }
%>

<%--JQuery--%>
<script>
    $(function () {

        $('#addUser_btn').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content_addUserWindow").fadeIn();
        });

        $("#addUser_close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_addUserWindow").fadeOut();
        });

        $('.editUser_btn').click(function () {
            $("#editUser_submit_btn").data("target_id", $(this).data("id"));
            $("#editUserWindow_username").val($(this).data("username"));

            $("#modal_view").fadeIn();
            $("#modal_content_editUserWindow").fadeIn();
        });

        $("#editUser_close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_editUserWindow").fadeOut();
        });

        $("#addUser_submit_btn").click(function () {
            var username = $("#addUserWindow_username").val().toString();
            var firstPwd = $("#addUserWindow_firstPwd").val().toString();
            var secondPwd = $("#addUserWindow_secondPwd").val().toString();
            // Front page verification
            if (username.length === 0) {
                $("#addUser_error_info").text("用户名不能为空！");
                return;
            }
            if (firstPwd.length === 0) {
                $("#addUser_error_info").text("新的密码不能为空！");
                return;
            }
            if (secondPwd.length === 0) {
                $("#addUser_error_info").text("再次输入密码不能为空！");
                return;
            }
            if (!(firstPwd === secondPwd)) {
                $("#addUser_error_info").text("两次输入密码不一致！");
                return;
            }

            $(window).attr(
                "location",
                "${pageContext.request.contextPath}/userAction_addUser.action?" +
                "username=" +
                username +
                "&firstPwd=" +
                firstPwd);
        });

        $("#editUser_submit_btn").click(function () {
            var username = $("#editUserWindow_username").val().toString();
            var oldPwd = $("#editUserWindow_oldPwd").val().toString();
            var firstPwd = $("#editUserWindow_firstPwd").val().toString();
            var secondPwd = $("#editUserWindow_secondPwd").val().toString();
            var id = $("#editUser_submit_btn").data("target_id");

            // Front page verification
            if (username.length === 0) {
                $("#editUser_error_info").text("用户名不能为空！");
                return;
            }
            if (oldPwd.length === 0) {
                $("#editUser_error_info").text("旧的密码不能为空！");
                return;
            }
            if (firstPwd.length === 0) {
                $("#editUser_error_info").text("新的密码不能为空！");
                return;
            }
            if (secondPwd.length === 0) {
                $("#editUser_error_info").text("再次输入密码不能为空！");
                return;
            }
            if (!(firstPwd === secondPwd)) {
                $("#editUser_error_info").text("两次输入密码不一致！");
                return;
            }

            $(window).attr(
                "location",
                "${pageContext.request.contextPath}/userAction_updateUser.action?" +
                "id=" +
                id +
                "&username=" +
                username +
                "&oldPwd=" +
                oldPwd +
                "&firstPwd=" +
                firstPwd);
        });

    });
</script>
</body>
</html>

<%-- Debug Switch --%>
<%-- <s:debug></s:debug> --%>