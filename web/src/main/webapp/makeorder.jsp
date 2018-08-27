<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
   p {
    border: 1px solid #000;
    padding: 5px;
   }
  </style>
<%@ include file="include/head.htm" %>
    <body>
    <%@ include file="include/menu.htm" %>
    <div class="container">
        <div class="catalog">
            <div class="row">
                <div class=col-md-2>Name</div>
                <div class=col-md-2>Stock Amount</div>
                <div class=col-md-2>Price</div>
                <div class=col-md-2>Order!</div>
            </div>
        </div>
       <div class="catalog">
           <c:forEach items="${catalogitems}" var="item">
               <form class="list-${user.id}" action="do?command=makeorder" method=POST>
                   <div class="row">
                       <input name="id" type="hidden" value="${item.ID}"/>
                       <div class=col-md-2>
                          <p>${item.name}</p>
                       </div>
                       <div class=col-md-2>
                           <p>${item.amount}</p>
                       </div>
                       <div class=col-md-2>
                           <p>${item.price}</p>
                       </div>
                       <div class=col-md-2>
                           <input id="amount" class="form-control input-md" name="amount"
                           value="0"/>
                       </div>
                       <button id="add" value="add" name="add" class="btn btn-success col-md-1">
                           Order!
                       </button>
                   </div>
               </form>
           </c:forEach>
       </div>

       <form class="form-horizontal" method="post" action="do?command=makeorder">
          <fieldset>
          <div class="form-group">
            <label class="col-md-4 control-label" for="create">Create Order</label>
            <div class="col-md-4">
              <button id="create" name="create" class="btn btn-primary">Create</button>
            </div>
          </div>
          </fieldset>
      </form>
    </body>
</html>


