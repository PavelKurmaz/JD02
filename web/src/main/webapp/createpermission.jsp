<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>

<form class="form-horizontal" method="post" action="do?command=createpermission">
    <fieldset>
        <div class="form-group">
            <div class="heading">
                <h3 class="title" align="center">Create Permission</h3>
            </div>
            <div class=col-lg-4>
                <input id="name" class="form-control input-md" name="name"
                       value="Enter permission name"/>
            </div>
        </div>
        <div class="col-lg-4">
            <button id="create" name="create" class="btn btn-primary">Create</button>
        </div>
    </fieldset>
</form>
</body>
</html>
