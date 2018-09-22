<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<div class="container">
    <%@ include file="include/menu.htm" %>
    <form class="form-horizontal" method="post" action="do?command=admsignup">
        <fieldset>

            <!-- Form Name -->
            <legend>Admin Registration</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="login">Login</label>
                <div class="col-md-4">
                    <input id="login" name="login" type="text" placeholder="enter login here"
                           class="form-control input-md">
                    <span class="help-block">${help_login}</span>
                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Password</label>
                <div class="col-md-4">
                    <input id="password" name="password" type="password" placeholder="enter password here"
                           class="form-control input-md">
                    <span class="help-block">${help_password}</span>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="login">E-mail</label>
                <div class="col-md-4">
                    <input id="email" name="email" type="text" placeholder="enter email here"
                           class="form-control input-md">
                    <span class="help-block">${help_email}</span>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="login">Phone</label>
                <div class="col-md-4">
                    <input id="phone" name="phone" type="text" placeholder="enter phone here"
                           class="form-control input-md">
                    <span class="help-block">${help_phone}</span>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="select">Select Role Type</label>
                <div class="col-md-4">
                    <select id="select" name="select" class="form-control">
                        <c:forEach items="${roles}" var="item">
                            <option value=${item.role}>${item.role}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="Submit"></label>
                <div class="col-md-4">
                    <button id="Submit" name="Submit" class="btn btn-primary">Sign Up</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>




