<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<%@ include file="/resources/include/head.htm" %>
<body>
<div class="container">
    <%@ include file="/resources/include/registerUser.htm" %>
    <form:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/address/${address.id}" modelAttribute="address">
        <fieldset>

            <!-- Form Name -->
            <legend>Enter Address</legend>

            <div class="col-lg-4">
                <form:label path="country">Country</form:label>
                <form:input path="country" class="form-control" placeholder="Country"/>
            </div>

            <div class="col-lg-4">
                <form:label path="city">City</form:label>
                <form:input path="city" class="form-control" placeholder="City"/>
            </div>

            <div class="col-lg-4">
                <form:label path="street">Street</form:label>
                <form:input path="street" class="form-control" placeholder="Street"/>
            </div>

            <div class="col-lg-4">
                <form:label path="building">Building</form:label>
                <form:input path="building" class="form-control" placeholder="Building"/>
            </div>

            <div class="col-lg-4">
                <form:label path="apt">Apartment</form:label>
                <form:input path="apt" class="form-control" placeholder="Apartment"/>
            </div>

            <div class="col-lg-4">
                <form:label path="zip">ZIP code</form:label>
                <form:input path="zip" class="form-control" placeholder="ZIP"/>
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




