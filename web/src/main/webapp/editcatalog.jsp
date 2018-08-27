<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>
<div class="container">
    <div class="row">
        <div class=col-md-2>Название</div>
        <div class=col-md-2>Остаток</div>
        <div class=col-md-2>Цена</div>
    </div>
        <c:forEach items="${items}" var="item">
            <form class="update-item-${item.ID}" action="do?command=EditCatalog" method=POST>
                <div class="row">
                    <input name="id" type="hidden" value="${item.ID}"/>
                    <div class=col-md-2>
                        <input id="name" class="form-control input-md" name="name"
                        value="${item.name}"/>
                    </div>
                    <div class=col-md-2>
                        <input id="amount" class="form-control input-md" name="amount"
                        value="${item.amount}"/>
                    </div>
                    <div class=col-md-2>
                        <input id="price" class="form-control input-md" name="price"
                        value="${item.price}"/>
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