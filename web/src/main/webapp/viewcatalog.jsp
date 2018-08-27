<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/menu.htm" %>
<!------ Include the above in your HEAD tag ---------->
    <div class="container">
       <table class="table">
         <thead>
           <tr>
             <th scope="col">#</th>
             <th scope="col">Name</th>
             <th scope="col">Stock Amount</th>
             <th scope="col">Price</th>
           </tr>
         </thead>
         <tbody>
         <c:forEach items="${catalogItems}" var="item">
           <tr>
             <th scope="row">${item.ID}</th>
             <td>${item.name}</td>
             <td>${item.amount}</td>
             <td>${item.price}</td>
           </tr>
          </c:forEach>
         </tbody>
       </table>
       <form class="form-horizontal" method="post" action="do?command=profile">
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
</body>