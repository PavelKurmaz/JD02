<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/resources/include/head.htm" %>
<body>
<%@ include file="/resources/include/register.htm" %>
<div class="container">
    <form class="form-horizontal" method="post" action="/admin/login">
        <fieldset>

            <!-- Form Name -->
            <legend>Admin Log In</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-lg-4 control-label" for="login">Login</label>
                <div class="col-lg-4">
                    <input id="login" name="login" type="text" placeholder="enter login here"
                           class="form-control input-md" value="Admin1">
                    <span class="help-block">${help_login}</span>
                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-lg-4 control-label" for="password">Password</label>
                <div class="col-lg-4">
                    <input id="password" name="password" type="password" placeholder="enter password here"
                           class="form-control input-md" value="1">
                    <span class="help-block">${help_password}</span>
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <div class="col-lg-4">
                    <button type="submit" class="btn btn-primary">Press here</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
</body>
</html>

