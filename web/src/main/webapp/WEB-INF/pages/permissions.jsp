<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
    p {
        border: 1px solid #000;
        padding: 5px;
    }
</style>
<%@ include file="/resources/include/head.htm" %>
<body>
<%@ include file="/resources/include/adminka.htm" %>

<div class="catalog">
    <div class="heading">
        <h3 class="title" align="center">Permissions</h3>
    </div>
    <div class="col-md-12 col-lg-12 col-md-offset-2 col-lg-offset-4 toppad">
        <div class="row">
            <div class=col-lg-1>#</div>
            <div class=col-lg-3>Name</div>
            <div class=col-lg-4>Actions</div>
        </div>
        <c:forEach items="${permissions}" var="item">

            <input name="id" type="hidden" value="${item.id}"/>
            <div class="row">
                <div class=col-lg-1>
                    <p>${item.id}</p>
                </div>
                <div class=col-lg-3>
                    <p>${item.name}</p>
                </div>
                <div class=col-lg-5>
                    <form class="list_permissions"
                          action="${pageContext.request.contextPath}/permission/edit/${item.id}"
                          method=POST>
                        <div class=col-lg-2>
                        <button id="edit" value="edit" name="edit" class="btn btn-success">
                            Edit
                        </button>
                        </div>
                    </form>
                    <form class="list_permissions"
                          action="${pageContext.request.contextPath}/permission/delete/${item.id}"
                          method=POST>
                        <div class=col-lg-2>
                        <button id="erase" value="erase" name="erase" class="btn btn-danger">
                            Delete
                        </button>
                        </div>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<form class="form-horizontal" method="get" action="${pageContext.request.contextPath}/permission/create">
    <fieldset>
        <div class="form-group">
            <label class="col-lg-4 control-label" for="create">Create Permission</label>
            <div class="col-lg-4">
                <button id="create" name="create" class="btn btn-primary">Create</button>
            </div>
        </div>
    </fieldset>
</form>
</body>
</html>
