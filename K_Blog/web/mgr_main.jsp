<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"
          type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pageStyle.css">

</head>
<body style="background:#f3f3f3;">

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">文章管理
        </strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 新增</button>
                </div>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">

        </div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search" placeholder="请输入文章标题关键字"
                       value="<s:property value="#parameters.keyword"></s:property>">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-default" type="button" id="input_search_btn">搜索</button>
                </span>
            </div>
        </div>
    </div>
</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>序号</li>
        <li>标题</li>
        <li>学科</li>
        <li>编辑</li>
        <li>删除</li>
    </ul>

    <s:iterator value="list" var="article">
        <ul class="list_goods_ul">
            <li><s:property value="#article.article_id"></s:property></li>
            <li><s:property value="#article.article_title"></s:property></li>
            <li><s:property value="#article.category.cname"></s:property></li>
            <li>
                <a href="#">
                <img class="img_icon" src="${pageContext.request.contextPath}/images/edit_icon.png" alt=""></a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/articleAction_deleteArticle.action?article_id=<s:property value="#article.article_id"></s:property>">
                    <img class="img_icon" src="${pageContext.request.contextPath}/images/delete_icon.png" alt="">
                </a>
            </li>
        </ul>
    </s:iterator>
    <!-- Sub pages -->
    <div id="page" class="page_div"></div>
</div>

<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/paging.js"></script>
<script>
    
    // Sub pages implementation
    $("#page").paging({
        pageNo: <s:property value="currentPage"></s:property>,
        totalPage: <s:property value="totalPage"></s:property>,
        totalSize: <s:property value="totalCount"></s:property>,
        callback: function(num) {
            //alert(num);
            $(window).attr(
                'location','${pageContext.request.contextPath}/articleAction_pageList.action?currentPage=' + num);
        }
    });

    $("#add").click(function () {
        $(window).attr('location','${pageContext.request.contextPath}/mgr_add_article.jsp');
    });

    $("#input_search_btn").click(function () {
        var keyword = $("#input_search").val();
        $(window).attr('location',"${pageContext.request.contextPath}/articleAction_pageList.action?keyword=" + keyword);

    })
</script>

</body>
</html>