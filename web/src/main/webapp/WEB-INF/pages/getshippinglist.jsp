<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<c:choose>
    <c:when test="${user==null}">
    <%@ include file="include/adminka.htm" %><div class="container">
    </c:when>
    <c:when test="${admin==null}">
        <%@ include file="include/menu.htm" %><div class="container">
    </c:when>
</c:choose>
     <div class="container">
        <div class="col-md-9 col-lg-9 col-md-offset-2 col-lg-offset-2 toppad" >
           <div class="panel panel-info">
             <div class="panel-heading">
               <h3 class="panel-title">Ordered Goods</h3>
             </div>
              <div class="panel-body">
                <table class="table table-order-information">
                    <tbody>
                        <tr>
                           <td>Name</td>
                           <td>Amount</td>
                           <td>Price</td>
                           <td>Order #</td>
                        </tr>
                        <c:forEach items="${itemlist}" var="item">
                        <tr>
                           <td>${item.name}</td>
                           <td>${item.amount}</td>
                           <td>${item.price}</td>
                           <td>${item.order_id}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
              </div>
           </div>
           <form class="update-list" action="do?command=profile" method=POST>
            <button id="return" name="return" class="btn btn-primary">Return</button>
           </form>
        </div>
     </div>
</body>
</html>

