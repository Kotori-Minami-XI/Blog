<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <link rel="stylesheet" href="css/amazeui.min.css" />
    <link rel="stylesheet" href="js/pageStyle.css">
    <script src="js/jquery.min.js"></script>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="addCategory_btn" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加分类</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>分类名称</li>
        <li>修改分类</li>
        <li>删除分类</li>
    </ul>

    <s:iterator value="categoryList" var="category" >
        <ul class="list_goods_ul">
            <li><s:property value="#category.cid"></s:property></li>
            <li><s:property value="#category.cname"></s:property></li>
            <li><a href="#" class="editCategory_btn" data-cid="<s:property value="#category.cid"></s:property>">
                <img class="img_icon" src="images/edit_icon.png" alt=""></a>
            </li>
            <li><a href="#" class="deleteCategory_btn"><img class="img_icon" src="images/delete_icon.png" alt=""></a></li>
        </ul>
    </s:iterator>

</div>

<%-- Grey background for windows --%>
<div id="modal_view"></div>

<div id="modal_content_addCategoryWindow">
    <div id="addCategory_close"><img src="images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加分类：</span>
                <input type="text" name="parentid" class="am-form-field" id="parentid">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>分类名称：</span>
                <input type="text" name="cname" class="am-form-field" id="cname">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="addCategory">添加</button>
        </div>
        
    </div>
</div>

<div id="modal_content_editCategoryWindow">
    <div id="editCategory_close"><img src="images/delete_icon.png" alt=""></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加分类：</span>
                <input type="text" name="parentid" class="am-form-field" id="parentid_edit">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <div>
                <span>分类名称：</span>
                <input type="text" name="cname" class="am-form-field" id="cname_edit">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="editCategory">添加</button>
        </div>

    </div>
</div>

<script>
    $(function () {
        $('#addCategory_btn').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content_addCategoryWindow").fadeIn();
        });

        $("#addCategory_close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_addCategoryWindow").fadeOut();
        });

        $("#addCategory").click(function () {
            var parentId = $("#parentid").val();
            var cname = $("#cname").val();
            //alert(parentId+"---"+cname);
            $(window).attr(
                'location',
                '${pageContext.request.contextPath}/categoryAction_addCategory.action?parentid=' + parentId + "&cname=" + cname);
        })

        $(".editCategory_btn").click(function () {
            var cid = $(this).data("cid");
            // alert(cid);

            // Ajax request to obtain a specific result
            $.post(
                "${pageContext.request.contextPath}/categoryAction_updateUI.action",
                {"cid": cid},
                function(data){
                    $("#")
                },
                "json")

            $("#modal_view").fadeIn();
            $("#modal_content_editCategoryWindow").fadeIn();
        })

        $("#editCategory_close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_editCategoryWindow").fadeOut();
        });

    });
</script>
</body>
</html>