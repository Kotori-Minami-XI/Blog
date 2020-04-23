<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/amazeui.min.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <%-- Third-party editor: umedit--%>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/umedit/ueditor.config.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/umedit/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/umedit/lang/zh-cn/zh-cn.js"></script>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">修改文章
        </strong><small></small></div>
    </div>
    <hr>
    <form id="blog_form" action="${pageContext.request.contextPath}/articleAction_updateArticle.action" method="post" enctype="multipart/form-data">
        <div class="edit_content">
            <div class="item1">
                <div>
                    <span>文章标题：</span>
                    <input type="text" value=<s:property value="article_title"></s:property>
                            class="am-form-field" name="article_title" style="width: 300px">&nbsp;&nbsp;
                </div>
            </div>

            <div class="item1">
                <span>所属分类：</span>
                <select id="category_select" name="category.parentid" style="width: 150px">&nbsp;&nbsp;</select>
                <select id="skill_select" name="category.cid" style="width: 150px">&nbsp;&nbsp;</select>
            </div>

            <div class="item1">
                <div>
                    <span>文章摘要：</span>
                    <input type="text" value=<s:property value="article_desc"></s:property>
                           class="am-form-field" name="article_desc" style="width: 300px">&nbsp;&nbsp;
                </div>
            </div>

            <div class="item1 update_pic" style="width: 200px; height: 200px;">
                <span>摘要图片：</span>
                <label for="fileupload" id="label_file">上传文件</label>
                <img src="${pageContext.request.contextPath}/uploadDir/<s:property value="article_pic"></s:property>"
                     id="imageview" class="item1_img" style="" alt="">
                <input type="file" name="upload" id="fileupload"/>
            </div>

            <%-- Hidden text to store obtained article id--%>
            <input type="hidden" name="article_id" value="<s:property value="article_id"></s:property>"/>

            <%-- Hidden text to store obtained article pic--%>
            <input type="hidden" name="article_pic" value="<s:property value="article_pic"></s:property>"/>

            <%-- Hidden text to store obtained article content--%>
            <input type="hidden" id="resContent" value="<s:property value="article_content"></s:property>"/>
            <div id="editor" name="article_content" style="width:900px;height:400px;"></div>
            <button class="am-btn am-btn-default" type="button" id="send" style="margin-top: 10px;">
                修改</button>
        </div>

    </form>

</div>

<script>
    $(function () {
        // Initialize umeditor & parse content form the hidden input into html
        var ue = UE.getEditor('editor');
        ue.ready(function () {
            ue.execCommand("inserthtml", $("#resContent").val());
        });

        // Step 1: Obtain root categories
        $.post(
            "${pageContext.request.contextPath}/articleAction_getCategory.action",
            {"parentId":0},
            function (data) {
                $(data).each(function (i, obj) {
                    // console.log(obj.cname);
                    $("#category_select").append("<option value="+ obj.cid +">" + obj.cname + "</option>");
                });
            },
            "json");

        // Step 2: Obtain sub categories and set current category
        var parentid = <s:property value="category.parentid"></s:property>;
        $.post(
            "${pageContext.request.contextPath}/articleAction_getCategory.action",
            {"parentId":parentid},
            function (data) {
                $(data).each(function (i, obj) {
                    $("#skill_select").append("<option value="+ obj.cid +">" + obj.cname + "</option>");
                })
                $("#category_select option[value=<s:property value="category.parentid"></s:property>]").prop("selected", true);
                $("#skill_select option[value=<s:property value="category.cid"></s:property>]").prop("selected", true);
            },
            "json");

        // Register and listen to change event for select box
        $("#category_select").change(function () {
            var cid = $("#category_select").val();
            $("#skill_select").empty();
            $.post(
                "${pageContext.request.contextPath}/articleAction_getCategory.action",
                {"parentId":cid.toString()},
                function (data) {
                    $(data).each(function (i, obj) {
                        $("#skill_select").append("<option value="+ obj.cid +">" + obj.cname + "</option>");
                    })
                },
                "json");
        });

        // Display updated graph in jsp
        $("#fileupload").change(function() {
            var $file = $(this);
            var objUrl = $file[0].files[0];
            var windowURL = window.URL || window.webkitURL;
            var dataURL;
            dataURL = windowURL.createObjectURL(objUrl);
            $("#imageview").attr("src",dataURL);
            console.log($('#imageview').attr('style'));
            if($('#imageview').attr('style') === 'display: none;'){
                $('#imageview').attr('style','inline');
                $('#imageview').width("200px");
                $('#imageview').height("200px");
                $('.update_pic').attr('style', 'margin-bottom: 80px;');
            }
        });

        $("#send").click(function () {
            $("#blog_form").submit();
        });

    });
</script>

</body>
</html>