<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>

<hr>
ERROR:<br> ${errmessage}
<hr>
STACK:<br> ${stack}
<hr>
<form class="form-horizontal" method="post" action="do?command=index">
    <label class="col-lg-2 control-label" for="return">Oops!</label>
    <div class="col-lg-2">
        <button id="return" name="return" class="btn btn-secondary">Return</button>
    </div>
</form>
</body>
</html>


