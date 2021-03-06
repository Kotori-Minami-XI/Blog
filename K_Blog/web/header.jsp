<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>Kotori(Kotori.com)</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lkblog.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog_con.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pageStyle.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/paging.js"></script>
</head>

<body>
<div id="top_bar" class="container hidden-xs hidden-sm">
    <div class="row">
        <div class="col-md-offset-1 col-md-7">
            <ul class="top-bar-left">
                <li style="margin-right: 20px;">
                    <a href="#" target="_blank">
                        <span></span>
                        喜欢IT,就来Kotori吧!
                    </a>
                </li>
                <li>
                    <a href="http://www.Kotori.com" target="_blank">
                        <span></span>
                        www.Kotori.com
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-md-offset-3">
            <span class="top-bar-right pull-right">喜欢IT, 就来<span style="color: red">Kotori</span> (www.<span style="color: red">Kotori</span>.com)</span>
        </div>
    </div>
</div>
<!-- Navigator -->
<nav class="navbar navbar-default navbar-lk">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <a class="navbar-brand" href="index.html" style="margin-left: 180px">
                <img src="${pageContext.request.contextPath}/images/lk_logo_sm.png" alt="Logo" width="220">
            </a>

            <ul class="nav navbar-nav" id="nav" style="font-size: 20px">
                <li class="active"><a href="index.jsp">博客首页</a></li>
            </ul>
            <a href="${pageContext.request.contextPath}/mgr_login.jsp" style="line-height:95px; height: 95px;font-size: 18px; color: #0a628f">
                发布文章
            </a>
        </div>
    </div>
</nav>
<!--banner-->
<section id="lk_blog_one">
    <img src="#" alt="" class="one-img" width="280">
    <div class="one-right">
        <h1>Kotori学院技术专栏</h1>
        <span>喜欢IT,就来Kotori吧!</span>
        <div style="position: relative;" class="one-bottom">
            <button>关注我们</button>
            <img src="${pageContext.request.contextPath}/images/ewm2.jpg" alt="" width="100" class="one-ewm">
        </div>
    </div>
</section>

<script>
    $(function () {
        $.post(
            "${pageContext.request.contextPath}/articleAction_getCategory.action",
            {"parentId":0},
            function (data) {
                $(data).each(function (i, obj) {
                    //alert(obj.cname);
                    $("#nav").append("<li class='active'><a href='index.jsp?parentid=" + obj.cid + "'>" + obj.cname +" </a></li>");
                });
            },
            "json");
    });
</script>
