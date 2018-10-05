<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<%@ include file="/resources/include/head.htm" %>
<body>
<%@ include file="/resources/include/adminka.htm" %>

<form:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/permission/create"
           modelAttribute="permission">
    <form:errors path="*" cssClass="errorBlock" element="div"/>
    <fieldset>
        <div class="col-lg-4">
            <form:label path="name">Permission Name</form:label>
            <form:input path="name" class="form-control" placeholder="Permission Name"/>
        </div>
        <p></p>
        <div class="col-lg-4">
            <button type="submit" class="btn btn-primary">Create</button>
        </div>
    </fieldset>
</form:form>
</body>
</html>
