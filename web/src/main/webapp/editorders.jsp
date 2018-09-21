<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>
<div class="container">
    <div class="row">
        <div class=col-lg-1>#</div>
        <div class=col-lg-1>User ID</div>
        <div class=col-lg-2>Items</div>
        <div class=col-lg-2>Статус</div>
        <div class=col-lg-2>Дата</div>
    </div>
    <c:forEach items="${buckets}" var="bucket">
        <form class="update-orders" action="do?command=editOrders" method=POST>
            <div class="row">
                <input name="id" type="hidden" value="${bucket.id}"/>
                <div class=col-lg-1>
                    <p>${bucket.id}</p>
                </div>
                <div class=col-lg-1>
                    <p>${bucket.userId}</p>
                </div>
                <div class=col-lg-2>
                    <c:forEach items="${bucket.orders}" var="order">
                        <div class="row">
                            <div class=col-lg-1>#</div>
                            <div class=col-lg-1>Quantity</div>
                        </div>
                        <div class="row">
                            <div class=col-lg-1>
                                <p>${order.itemId}</p>
                            </div>
                            <div class=col-lg-1>
                                <p>${order.quantity}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class=col-lg-2>
                    <select id="status" name="status" class="form-control">
                        <option value="New">"New"</option>
                        <option value="In progress">"In progress"</option>
                        <option value="Completed">"Completed"</option>
                        <option selected="${bucket.status}">${bucket.status}</option>
                    </select>
                </div>
                <div class=col-lg-2>
                    <p>${bucket.created}</p>
                </div>
                <div class=col-lg-3>
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