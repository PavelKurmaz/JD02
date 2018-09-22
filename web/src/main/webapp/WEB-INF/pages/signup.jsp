<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<div class="container">
    <%@ include file="include/menu.htm" %>
    <form class="form-horizontal" method="post" action="do?command=signup">
    <fieldset>

    <!-- Form Name -->
    <legend>Registration</legend>

    <!-- Text input-->
    <div class="form-group">
      <label class="col-md-4 control-label" for="login">Login</label>
      <div class="col-md-4">
      <input id="login" name="login" type="text" placeholder="enter login here" class="form-control input-md">
      <span class="help-block">${help_login}</span>
      </div>
    </div>

    <!-- Password input-->
    <div class="form-group">
      <label class="col-md-4 control-label" for="password">Password</label>
      <div class="col-md-4">
        <input id="password" name="password" type="password" placeholder="enter password here" class="form-control input-md">
        <span class="help-block">${help_password}</span>
      </div>
    </div>

    <!-- Text input-->
    <div class="form-group">
      <label class="col-md-4 control-label" for="E-mail">E-mail</label>
      <div class="col-md-4">
      <input id="E-mail" name="E-mail" type="text" placeholder="enter e-mail here" class="form-control input-md">
      </div>
    </div>

    <!-- Text input-->
    <div class="form-group">
      <label class="col-md-4 control-label" for="phone">Phone Number</label>
      <div class="col-md-4">
      <input id="phone" name="phone" type="text" placeholder="enter phone number here" class="form-control input-md">
      <span class="help-block">+375.....</span>
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




