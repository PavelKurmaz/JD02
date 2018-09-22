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
        <h3 class="title" align="center">Edit Permission</h3>
    </div>
    <div class="col-md-12 col-lg-12 col-md-offset-2 col-lg-offset-4 toppad">
        <div class="row">
            <div class=col-lg-2>Permission #: ${permissionId}</div>
            <div class=col-lg-3>Permission name: ${name}</div>
        </div>
        <c:forEach items="${roles}" var="item">
            <form class="list_permissions" action="do?command=editpermission" method=POST>
                <input name="id" type="hidden" value="${item.id}"/>
                <div class="row">
                    <div class=col-lg-2>
                        <p>Role #: ${item.id}</p>
                    </div>
                    <div class=col-lg-3>
                        <p>Role name: ${item.role}</p>
                    </div>
                    <button id="delete" value="delete" name="delete" class="btn btn-success col-lg-1">
                        Delete
                    </button>
                </div>
            </form>
        </c:forEach>
    </div>
</div>

<form class="form-horizontal" method="post" action="do?command=editpermission">
    <fieldset>
        <label class="col-md-4 control-label" for="select">Select Role Type</label>
        <div class="col-md-4">
            <select id="select" name="select" class="form-control">
                <c:forEach items="${addroles}" var="item">
                <option value=${item.role}>${item.role}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="add">Add Role</label>
            <div class="col-md-4">
                <button id="add" name="add" class="btn btn-primary">Add</button>
            </div>
        </div>
    </fieldset>
</form>
<form class="form-horizontal" method="post" action="do?command=permissions">
    <fieldset>
        <div class="form-group">
            <label class="col-lg-4 control-label" for="add">Finish edit</label>
            <div class="col-lg-4">
                <button id="return" name="add" class="btn btn-primary">Return</button>
            </div>
        </div>
    </fieldset>
</form>
</body>
</html>
