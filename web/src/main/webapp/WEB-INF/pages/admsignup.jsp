<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<%@ include file="/resources/include/head.htm" %>
<%@ include file="/resources/include/register.htm" %>
<body>
<div class="container">

    <form:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/create" modelAttribute="admin">
        <form:errors path="*" cssClass="errorBlock" element="div"/>
        <fieldset>

            <legend>Admin Registration</legend>

            <div class="col-lg-4">
                <form:label path="login">Login</form:label>
                <form:input path="login" class="form-control" placeholder="Login"/>
            </div>
            <div class="col-lg-4">
                <form:label path="password">Password</form:label>
                <form:input path="password" class="form-control" placeholder="Password"/>
            </div>
            <div class="col-lg-4">
                <form:label path="email">E-mail</form:label>
                <form:input path="email" class="form-control" placeholder="E-mail"/>
            </div>
            <div class="col-lg-4">
                <form:label path="phone">Phone</form:label>
                <form:input path="phone" class="form-control" placeholder="Phone"/>
            </div>

            <div class="col-lg-4">
                <label class="col-lg-4 control-label" for="select">Select Role Type</label>
                <div class="col-lg-4">
                    <select id="select" name="select" class="form-control">
                        <c:forEach items="${roles}" var="item">
                            <option value=${item.role}>${item.role}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <div class="col-lg-4">
                    <button type="submit" class="btn btn-primary">Sign Up</button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>
</body>
</html>




