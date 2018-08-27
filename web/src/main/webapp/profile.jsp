<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/menu.htm" %>
     <div class="container">
        <div class="col-md-6 col-lg6 col-md-offset-2 col-lg-offset-2 toppad" >
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">${user.login}</h3>
            </div>
            <div class="panel-body">
              <table class="table table-user-information">
                <tbody>
                  <tr>
                    <td>User Login</td>
                    <td>${user.login}</td>
                  </tr>
                  <tr>
                    <td>E-mail</td>
                    <td>${user.email}</td>
                  </tr>
                  <tr>
                    <td>Phone number</td>
                    <td>${user.phone}</td>
                  </tr>
                   <tr>
                    <td>User status</td>
                    <td>${user.carma}</td>
                  </tr>
                  <tr>
                    <td><b>User</b></td>
                    <td><b>Address</b></td>
                  </tr>
                  <tr>
                    <td>Country</td>
                    <td>${address.country}</td>
                  </tr>
                  <tr>
                    <td>City</td>
                    <td>${address.city}</td>
                  </tr>
                  <tr>
                    <td>Street</td>
                    <td>${address.street}</td>
                  </tr>
                   <tr>
                    <td>Building</td>
                    <td>${address.building}</td>
                  </tr>
                  <tr>
                    <td>Apartment</td>
                    <td>${address.apt}</td>
                  </tr>
                  <tr>
                    <td>ZIP Code</td>
                    <td>${address.zip}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
            <form class="form-horizontal" method="post" action="do?command=profile">
                <fieldset>
                <div class="form-group">
                  <label class="col-md-8 control-label" for="logout">Log Out</label>
                  <div class="col-md-8">
                    <button id="logout" name="logout" class="btn btn-primary">Press here</button>
                  </div>
                </div>
                </fieldset>
            </form>
        </div>
     </div>
</body>
</html>
