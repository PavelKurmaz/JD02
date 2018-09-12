<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/useredit.htm" %>
<div class="container">
    <div class="container">
        <div class="row">
            <div class=col-lg-2>Имя</div>
            <div class=col-lg-2>Пароль</div>
            <div class=col-lg-2>Карма</div>
            <div class=col-lg-2>Отключить</div>
            <div class=col-lg-2>Роль</div>
        </div>
    </div>
    <div class="container">
        <c:forEach items="${users}" var="user">
            <form class="update-user-${user.id}" action="do?command=editUsers" method=POST>
                <div class="row">
                    <input name="id" type="hidden" value="${user.id}"/>
                    <div class=col-lg-2>
                        <input id="login" class="form-control input-md" name="login"
                               value="${user.login}"/>
                    </div>
                    <div class=col-lg-2>
                        <input id="password" class="form-control input-md" name="password"
                               value="${user.password}"/>
                    </div>
                    <div class=col-lg-2>
                        <select id="carma" name="carma" class="form-control">
                          <option value="weak">weak</option>
                          <option value="regular">regular</option>
                          <option value="silver">silver</option>
                          <option value="gold">gold</option>
                          <option selected="${user.carma}">${user.carma}</option>
                        </select>
                    </div>
                    <div class=col-lg-2>
                        <select id="disabled" name="disabled" class="form-control">
                            <option value="disabled">Disabled</option>
                            <option value="enabled">Enabled</option>
                            <option selected="${user.disabled}">${user.disabled}</option>
                        </select>
                    </div>
                    <div class=col-lg-2>
                        <select id="role" name="role" class="form-control">
                            <c:forEach items="${roles}" var="item">
                                <option value=${item.role}>${item.role}</option>
                            </c:forEach>
                            <option selected= "">User</option>
                        </select>
                    </div>
                    <button id="Update" value="Update" name="Update" class="btn btn-success col-lg-1">
                        Обновить
                    </button>
                    <button id="Delete" value="Delete" name="Delete" class="btn btn-danger col-lg-1">
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