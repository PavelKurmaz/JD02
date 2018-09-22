<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
    p {
        border: 1px solid #000;
        padding: 5px;
    }
</style>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>

<div class="catalog">
    <div class="heading">
        <h3 class="title" align="center">Permissions</h3>
    </div>
    <div class="col-md-12 col-lg-12 col-md-offset-2 col-lg-offset-4 toppad">
        <div class="row">
            <div class=col-lg-2>#</div>
            <div class=col-lg-3>Name</div>
            <div class=col-lg-2>Actions</div>
        </div>
        <c:forEach items="${permissions}" var="item">
            <form class="list_permissions" action="do?command=editpermission" method=POST>
                <input name="id" type="hidden" value="${item.id}"/>
                <div class="row">
                    <div class=col-lg-2>
                        <p>${item.id}</p>
                    </div>
                    <div class=col-lg-3>
                        <p>${item.name}</p>
                    </div>
                    <button id="edit" value="edit" name="edit" class="btn btn-success col-lg-1">
                        Edit
                    </button>
                    <button id="erase" value="erase" name="erase" class="btn btn-danger col-md-1">
                        Delete
                    </button>
                </div>
            </form>
        </c:forEach>
    </div>
</div>

<form class="form-horizontal" method="post" action="do?command=permissions">
    <fieldset>
        <div class="form-group">
            <label class="col-md-4 control-label" for="create">Create Permission</label>
            <div class="col-md-4">
                <button id="create" name="create" class="btn btn-primary">Create</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
</body>
</html>
