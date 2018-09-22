<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<div class="container">
    <%@ include file="include/menu.htm" %>
    <form class="form-horizontal" method="post" action="do?command=address">
        <fieldset>

            <!-- Form Name -->
            <legend>Enter Address</legend>

            ${user_id}

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="country">Country</label>
                <div class="col-md-4">
                    <input id="country" name="country" type="text" placeholder="" class="form-control input-md">
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="city">City</label>
                <div class="col-md-4">
                    <input id="city" name="city" type="text" placeholder="" class="form-control input-md">
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="street">Street</label>
                <div class="col-md-4">
                    <input id="street" name="street" type="text" placeholder="" class="form-control input-md">
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="building">Building</label>
                <div class="col-md-4">
                    <input id="building" name="building" type="text" placeholder="" class="form-control input-md">
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="apt">Apartment</label>
                <div class="col-md-4">
                    <input id="apt" name="apt" type="text" placeholder="" class="form-control input-md">

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="zip">ZIP</label>
                <div class="col-md-4">
                    <input id="zip" name="zip" type="text" placeholder="" class="form-control input-md">
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="singlebutton">Submit</label>
                <div class="col-md-4">
                    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Press</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>




