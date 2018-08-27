<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>
<div class="container">
    <div class="container">
        <div class="row">
            <div class=col-md-2>Имя</div>
            <div class=col-md-2>Пароль</div>
            <div class=col-md-2>Email</div>
            <div class=col-md-2>Телефон</div>
            <div class=col-md-2>Карма</div>
        </div>
    </div>
    <div class="container">
        <c:forEach items="${users}" var="user">
            <form class="update-user-${user.id}" action="do?command=editUsers" method=POST>
                <div class="row">
                    <input name="id" type="hidden" value="${user.id}"/>
                    <div class=col-md-2>
                        <input id="login" class="form-control input-md" name="login"
                               value="${user.login}"/>
                    </div>
                    <div class=col-md-2>
                        <input id="password" class="form-control input-md" name="password"
                               value="${user.password}"/>
                    </div>
                    <div class=col-md-2>
                        <input id="email" class="form-control input-md" name="email"
                               value="${user.email}"/>
                    </div>
                    <div class=col-md-2>
                        <input id="phone" class="form-control input-md" name="phone"
                                value="${user.phone}"/>
                    </div>
                    <div class=col-md-2>
                        <select id="carma" name="carma" class="form-control">
                          <option value="weak">weak</option>
                          <option value="regular">regular</option>
                          <option value="silver">silver</option>
                          <option value="gold">gold</option>
                          <option selected="${user.carma}">${user.carma}</option>
                        </select>
                    </div>
                    <button id="Update" value="Update" name="Update" class="btn btn-success col-md-1">
                        Обновить
                    </button>
                    <button id="Delete" value="Delete" name="Delete" class="btn btn-danger col-md-1">
                        Удалить
                    </button>
                </div>
            </form>
            <p></p>
        </c:forEach>
    </div>
</div>
</body>
</html>