<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/resources/include/head.htm" %>
<body>
<h1 align = "center" color = "blue">Welcome to AlcoMarket!<span class="badge badge-secondary">Under Construction</span></h1>
<div class="container">
    <img src="/resources/img/1.jpg" class="img-fluid" alt="Responsive image">
    <p></p>
    <form class="form-horizontal"  method="get" action=${pageContext.request.contextPath}/user/login>
    <fieldset>
    <!-- Button -->
    <div class="form-group">
      <div class="col-lg-12">
        <button type="submit" class="btn btn-primary btn-lg btn-block">Proceed</button>
      </div>
    </div>
    </fieldset>
    </form>
</div>
</body>
</html>

