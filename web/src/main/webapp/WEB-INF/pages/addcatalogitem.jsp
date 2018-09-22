<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>
<div class="container">
    <form class="form-horizontal"  method="post" action="do?command=addcatalogitem">
        <fieldset>

        <!-- Form Name -->
        <legend>Добавить позицию в каталог</legend>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="name">Имя бутылки</label>
          <div class="col-md-4">
          <input id="name" name="name" type="text" placeholder="enter name" class="form-control input-md">
          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="amount">Кол-во на склад</label>
          <div class="col-md-4">
          <input id="amount" name="amount" type="text" placeholder="enter amount" class="form-control input-md">
          <span class="help-block">Только целое число!</span>
          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-4 control-label" for="Price">Цена</label>
          <div class="col-md-4">
          <input id="price" name="price" type="text" placeholder="enter price" class="form-control input-md">
          </div>
        </div>

        <!-- Button -->
        <div class="form-group">
          <label class="col-md-4 control-label" for="singlebutton">Добавить</label>
          <div class="col-md-4">
            <button id="singlebutton" name="singlebutton" class="btn btn-primary">Жми!</button>
          </div>
        </div>
        </fieldset>
    </form>
</div>
</body>
</html>


