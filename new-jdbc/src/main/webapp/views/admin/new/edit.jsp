<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglibs.jsp"%>
<c:url var="APIurl" value="/api-admin-new" />
<c:url var="Newurl" value="/admin-new" />
<html>

<head>
    <title>Chỉnh sửa bài viết</title>
</head>

<body>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {}
                </script>
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang chủ</a>
                    </li>
                    <li class="active">Chỉnh sửa bài viết</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${not empty message}">
                            <div class="alert alert-${alert}">
                                ${message}
                            </div>
                        </c:if>
                        <form id="formSubmit">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="categoryCode" name="categoryCode">
                                        <c:if test="${empty model.categoryCode}">
                                            <option value="">Chọn loại bài viết</option>
                                            <c:forEach var="item" items="${categories}">
                                                <option value="${item.code}">${item.name}</option>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty model.categoryCode}">
                                            <c:forEach var="item" items="${categories}">
                                                <option value="">Chọn loại bài viết</option>
                                                <option value="${item.code}" <c:if
                                                    test="${item.code == model.categoryCode}">selected="selected"
                                        </c:if>>
                                        ${item.name}
                                        </option>
                                        </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="title" name="title"
                                        value="${model.title}" />
                                </div>
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="thumbnail" name="thumbnail" value="" />
                                </div>
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="shortDescription"
                                        name="shortDescription" value="${model.shortDescription}" />
                                </div>
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                <div class="col-sm-9">
                                    <textarea rows="" cols="" id="content" name="content" style="width: 820px;height: 175px">${model.content}</textarea>
                                </div>
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${not empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold"
                                            value="Cập nhật bài viết" id="btnAddOrUpdateNew" />
                                    </c:if>
                                    <c:if test="${empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold"
                                            value="Thêm bài viết" id="btnAddOrUpdateNew" />
                                    </c:if>
                                </div>
                            </div>
                            <input type="hidden" value="${model.id}" id="id" name="id" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    
        var editor = "";
        //start web lên chạy đầu tiên
    	$(document).ready(function () {
           editor = CKEDITOR.replace("content");
        });
    
        $('#btnAddOrUpdateNew').click(function (e) {
            e.preventDefault();
            // data là javascript object
            var data = {};
            var formData = $("#formSubmit").serializeArray();
            $.each(formData, function (i, v) {
                data["" + v.name + ""] = v.value;
            });
            // Sử dụng ckeditor thì ko lấy được dữ liệu
            
            data["content"] = editor.getData(); // -> get data riêng cho content mới lấy được
            var id = $("#id").val();
            if (id == "") {
                addNew(data);
            } else {
                updateNew(data);
            }
        });

        function addNew(data) {
            $.ajax({
                url: '${APIurl}',
                type: 'POST',
                //client gửi cho server type json
                contentType: 'application/json',
                // JSON.stringify dùng convert data(javascript object) thành json
                data: JSON.stringify(data),
                // server gửi lại cho client type json
                dataType: 'json',
                success: function (result) {
                	window.location.href = "${Newurl}?type=list&page=1&maxPageItem=2&sortName=title&sortBy=desc";
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }

        function updateNew(data) {
            $.ajax({
                url: '${APIurl}',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                	window.location.href = "${Newurl}?type=list&page=1&maxPageItem=2&sortName=title&sortBy=desc";
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
    </script>
</body>

</html>