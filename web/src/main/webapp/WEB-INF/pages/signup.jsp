<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<%@ include file="/resources/include/head.htm" %>
<body>
<%@ include file="/resources/include/registerUser.htm" %>
<div class="container">
    <form:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/create" modelAttribute="user">
        <form:errors path="*" cssClass="errorBlock" element="div"/>
        <fieldset>
            <!-- Form Name -->
            <legend>Registration</legend>
            <!-- Text input-->
            <div class="col-lg-4">
                <form:label path="login">Login</form:label>
                <form:input path="login" class="form-control" placeholder="Login"/>
            </div>
            <!-- Password input-->
            <div class="col-lg-4">
                <form:label path="password">Password</form:label>
                <form:input path="password" class="form-control" placeholder="Password"/>
            </div>
            <div class="col-lg-4">
                <form:label path="email">E-mail</form:label>
                <form:input path="email" class="form-control" placeholder="somename@mail.ru"/>
            </div>
            <div class="col-lg-4">
                <form:label path="phone">Phone number</form:label>
                <form:input path="phone" class="form-control" placeholder="+375..."/>
            </div>
            <!-- Button -->
            <div class="form-group">
                <div class="col-lg-4">
                    <p></p>
                    <button type="submit" class="btn btn-primary">Sign Up</button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>
</body>
</html>




