<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>
    <div class="container">
        <div class="col-md-6 col-lg-6 col-md-offset-2 col-lg-offset-2 toppad" >
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">${admin.login}</h3>
            </div>
            <div class="panel-body">
              <table class="table table-user-information">
                <tbody>
                  <tr>
                    <td>Admin Login</td>
                    <td>${admin.login}</td>
                  </tr>
                  <tr>
                    <td>E-mail</td>
                    <td>${admin.email}</td>
                  </tr>
                  <tr>
                    <td>Phone number</td>
                    <td>${admin.phone}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
            <form class="form-horizontal" method="post" action="do?command=profile">
            <fieldset>
                <div class="form-group">
                <label class="col-md-2 control-label" for="logout">Log Out</label>
                <button id="logout" name="logout" class="btn btn-primary">Press here</button>
                </div>
            </fieldset>
            </form>
        </div>
    </div>
</body>
</html>
