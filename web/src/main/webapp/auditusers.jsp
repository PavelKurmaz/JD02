<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/useredit.htm" %>
<!------ Include the above in your HEAD tag ---------->
    <div class="container">
       <table class="table">
         <thead>
           <tr>
             <th scope="col">#</th>
             <th scope="col">Event</th>
             <th scope="col">Date</th>
             <th scope="col">User ID</th>
           </tr>
         </thead>
         <tbody>
         <c:forEach items="${audit}" var="item">
           <tr>
             <th scope="row">${item.ID}</th>
             <td>${item.event_type}</td>
             <td>${item.localDateTime}</td>
             <td>${item.user_id}</td>
           </tr>
          </c:forEach>
         </tbody>
       </table>
       <form class="form-horizontal" method="post" action="do?command=auditusers">
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