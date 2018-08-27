<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>
<div class="container">
    <div class="row">
        <div class=col-md-1>Номер</div>
        <div class=col-md-1>User ID</div>
        <div class=col-md-2>Статус</div>
    </div>
    <c:forEach items="${orders}" var="order">
        <form class="update-user-${user.id}" action="do?command=editOrders" method=POST>
            <div class="row">
                <input name="id" type="hidden" value="${order.id}"/>
                <div class=col-md-1>
                    <p>${order.id}</p>
                </div>
                <input name="users_id" type="hidden" value="${order.users_ID}"/>
                <div class=col-md-1>
                    <p>${order.users_ID}</p>
                </div>
                <div class=col-md-2>
                    <select id="completed" name="completed" class="form-control">
                      <option value=0>"Incompleted"</option>
                      <option value=1>"Completed"</option>
                      <option selected="${order.completed}">
                          <c:choose>
                          <c:when test="${order.completed < 1}">Not completed</c:when>
                          <c:otherwise>Completed</c:otherwise>
                          </c:choose></option>
                    </select>
                </div>
                <div class=col-md-4>
                <button id="Update" value="Update" name="Update" class="btn btn-success col-md-4">
                    Обновить
                </button>

                <button id="Delete" value="Delete" name="Delete" class="btn btn-danger col-md-4">
                    Удалить
                </button>
                </div>
            </div>
        </form>
        <p></p>
    </c:forEach>
</div>
</body>
</html>