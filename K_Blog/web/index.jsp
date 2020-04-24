<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>
<script src="${pageContext.request.contextPath}/js/template.js"></script>
<style>
    .content_item {
        height: 160px;
        position: relative;
    }

    .content_item img {
        position: absolute;
        right: 10px;
        top: 10px;
        width: 250px;
        height: 140px;
    }

</style>

<section class="layout main-wrap  content">
    <section class='col-main'>

        <article class="mainarea" style="display:block;">
            <div class="blog-tab">
                <div class="tab-content" id="tab_content">

                </div>
            </div>
        </article>
        <!--Blog community-->
        <article class="mainarea" style="display:block;">
            <div class="blog-tab">

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="home">

                        <div id="lk_blog_list" class="container">
                            <div class="row">
                                <ul class="blog-list" id="content">
                                    <li class="content_item">
                                        <div class="blog-list-left" style="float: left;">
                                            <div class="main-title">
                                                <a href="detail.jsp">标题标题标题标题标题标题</a>
                                            </div>
                                            <p class="sub-title">描述描述描述描述描述描述描述描述</p>
                                            <div class="meta">
                                                2020-08-31
                                            </div>
                                        </div>
                                        <img src="${pageContext.request.contextPath}/images/blog_img.png" alt="" class="img-rounded">
                                    </li>
                                </ul>
                                <div id="page" class="page_div"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </section>

</section>
<footer id="lk_footer">
    <div class="container">
        <div class="footer-top">
          <!--Sub pages-->
        </div>
        <div class="footer-bottom col-sm-offset-2 hidden-sm hidden-xs">
            <ul>
                <li><a href="" onclick="reminder()">学科报名</a></li>
                <li><a href="" onclick="reminder()">师资团队</a></li>
                <li><a href="" onclick="reminder()">线上公开课</a></li>
                <li><a href="" onclick="reminder()">联络我们</a></li>
                <li><a href="" onclick="reminder()">支持我们</a></li>
                <li><a href="" onclick="reminder()">沪ICP备 18012345号-1</a></li>
            </ul>
        </div>
    </div>
</footer>

<%--Js template for html rendering--%>
<script id="js_article_Template" type="text/html">
    {{each list as value}}
    <li class="content_item">
        <div class="blog-list-left" style="float: left;">
            <div class="main-title">
                <a href="detail.jsp">{{value.article_title}}</a>
            </div>
            <p class="sub-title">{{value.article_desc}}</p>
            <div class="meta">
                2020-08-31
            </div>
        </div>
        <img src="${pageContext.request.contextPath}/uploadDir/{{value.article_pic}}" alt="" class="img-rounded">
    </li>
    {{/each}}
</script>

<script id="js_subCategory_Template" type="text/html">
    <div role="tabpanel" class="tab-pane fade in active" id="tab">
        <%--Classification--%>
        <div id="lk_blog_two" class="container">
            <div class="row">
                {{each list as value}}
                <button class="btn-tag">{{value.cname}}</button>
                {{/each}}
            </div>
        </div>
    </div>
</script>


<script>
    /* Load sub categories once parent category is selected*/
    var parentid = getParams("parentid");
    if (null != parentid) {
        $.post(
            "${pageContext.request.contextPath}/articleAction_getCategory.action",
            {"parentId": parentid},
            function (data) {
                console.log(data);
                var html = template('js_subCategory_Template', {list: data});
                $("#tab_content").html(html);
            },
            "json");
    } else {
        getPageList(1);
    }

    function reminder() {
        alert("功能还未上线，敬请期待");
    };

    function getPageList(currentPage) {
        $.post(
            "${pageContext.request.contextPath}/webAction_getPageList.action",
            {"currentPage": currentPage},
            function (data) {
                // Use template to render html
                var html = template('js_article_Template', {list: data.list});
                $("#content").html(html);

                // Set paging
                $("#page").paging({
                    pageNo: data.currentPage,
                    totalPage: data.totalPage,
                    totalSize: data.totalCount,
                    callBack: function (num) {
                        getPageList(num);
                    }
                });
            },
            "json");
    };

    // Get parameters from Url through regular expression
    function getParams(param) {
        var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    };

</script>


</body>
</html>

