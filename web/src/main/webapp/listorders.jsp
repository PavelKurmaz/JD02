<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/menu.htm" %>
<!------ Include the above in your HEAD tag ---------->
    <div class="container">
        <div class="col-md-9 col-lg-9 col-md-offset-2 col-lg-offset-2 toppad" >
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">User Orders</h3>
            </div>
            <div class="panel-body">
              <table class="table table-order-information" layout:fixed>
                <tbody>
                <c:forEach items="${orders}" var="order">
                  <tr>
                      <td>Order number</td>
                      <td align="left">${order.id}</td>
                  </tr>
                  <tr>
                      <td>Order Status</td>
                      <c:choose>
                      <c:when test="${order.completed < 1}">
                      <td align="left">Not completed</td>
                      </c:when>
                      <c:otherwise>
                      <td align="left">Completed</td>
                      </c:otherwise>
                      </c:choose>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
             </div>
          </div>
        <form class="form-horizontal" method="post" action="do?command=listorders">
        <fieldset>
        <div class="form-group">
          <label class="col-md-4 control-label" for="return"></label>
          <div class="col-md-4">
            <button id="return" name="return" class="btn btn-primary">Return</button>
          </div>
        </div>
        </fieldset>
        </form>
        </div>
    </div>
</body>
</html>
