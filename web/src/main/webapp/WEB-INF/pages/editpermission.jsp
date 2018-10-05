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

<div class="heading">
    <h3 class="title" align="center">Edit Permission</h3>
</div>
<div class="col-lg-12 col-lg-offset-4 toppad">
    <div class="row">
        <div class=col-lg-2>Permission #: ${permission.id}</div>
        <div class=col-lg-3>Permission name: ${permission.name}</div>
    </div>
    <c:forEach items="${roles}" var="item">
        <form class="list_permissions"
              action="${pageContext.request.contextPath}/permission/edit/deleteRole/${item.id}" method=POST>
            <input name="permissionId" type="hidden" value="${permission.id}"/>
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


<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/permission/edit/addRole">
    <input name="permissionId" type="hidden" value="${permission.id}"/>
    <fieldset>
        <label class="col-lg-4 control-label" for="select">Select Role Type</label>
        <div class="col-lg-4">
            <select id="select" name="select" class="form-control">
                <c:forEach items="${allroles}" var="item">
                    <option value=${item.role}>${item.role}</option>
                </c:forEach>
            </select>
        </div>
        <br>
        <div class="col-lg-4">
            <button type="submit" class="btn btn-primary">Add Role</button>
        </div>
    </fieldset>
</form>
<form class="form-horizontal" method="get" action="${pageContext.request.contextPath}/permission">
    <fieldset>
        <div class="col-lg-4">
            <button id="return" name="add" class="btn btn-primary">Return</button>
        </div>

    </fieldset>
</form>
</body>
</html>
