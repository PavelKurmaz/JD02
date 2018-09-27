<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/resources/include/head.htm" %>
<body>
<div class="container">
    <form class="form-horizontal" method="post" action=/index>
        <fieldset>

            <!-- Form Name -->
            <legend>Select Profile Type</legend>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="select">Select Profile Type</label>
                <div class="col-md-4">
                    <select id="select" name="select" class="form-control">
                        <option value="User">User</option>
                        <option value="Admin">Admin</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Press here</button>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
