<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/menu.htm" %>
<div class="container">
    <div class="userdata">
        <div class="row">
            <div class=col-md-2>Name</div>
            <div class=col-md-2>Password</div>
            <div class=col-md-2>Email</div>
            <div class=col-md-2>Phone</div>
        </div>
    </div>

    <div class="userdata">
            <form class="update-user-${user.id}" action="do?command=userEdit" method=POST>
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
                    <button id="Update" value="Update" name="Update" class="btn btn-success col-md-2">
                        Update
                    </button>
                    <button id="Delete" value="Delete" name="Delete" class="btn btn-danger col-md-2">
                        Delete
                    </button>
                </div>
            </form>
            <p></p>
    </div>

    <p></p>

    <div class="address">
                <div class="row">
                    <div class=col-md-2>Country</div>
                    <div class=col-md-2>City</div>
                    <div class=col-md-2>Street</div>
                    <div class=col-md-2>Building</div>
                    <div class=col-md-2>Apt</div>
                    <div class=col-md-2>ZIP</div>
                </div>
            </div>

    <div class="address">
                <form class="update-user-${user.id}" action="do?command=userEdit" method=POST>
                    <div class="row">
                        <input name="id" type="hidden" value="${user.id}"/>
                        <div class=col-md-2>
                            <input id="country" class="form-control input-md" name="country"
                                   value="${address.country}"/>
                        </div>
                        <div class=col-md-2>
                            <input id="city" class="form-control input-md" name="city"
                                   value="${address.city}"/>
                        </div>
                        <div class=col-md-2>
                            <input id="street" class="form-control input-md" name="street"
                                   value="${address.street}"/>
                        </div>
                        <div class=col-md-2>
                            <input id="building" class="form-control input-md" name="building"
                                    value="${address.building}"/>
                        </div>
                        <div class=col-md-2>
                            <input id="apt" class="form-control input-md" name="apt"
                                     value="${address.apt}"/>
                        </div>
                        <div class=col-md-2>
                            <input id="zip" class="form-control input-md" name="zip"
                                     value="${address.zip}"/>
                        </div>
                    </div>
                    <p></p>
                    <button id="AddUpdate" value="Update" name="AddUpdate" class="btn btn-success col-md-2">
                         Update
                    </button>
                </form>
                <p></p>
        </div>
</div>
</body>
</html>