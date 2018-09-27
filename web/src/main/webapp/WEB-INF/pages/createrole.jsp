<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<%@ include file="/resources/include/head.htm" %>
<%@ include file="/resources/include/register.htm" %>
<body>
<div class="container">
    <form:form class="form-horizontal" method="post" action="/role/create" modelAttribute="role">
        <fieldset>
            <div class="form-group" align="center">
                <form:label path="role">Rolename</form:label>
                <form:input path="role" class="form-control" placeholder="Role"/>
            </div>
            <div class="col-lg-4">
                <p></p>
                <button type="submit" class="btn btn-primary">Create</button>
            </div>
        </fieldset>
    </form:form>
</div>
</body>
</html>
