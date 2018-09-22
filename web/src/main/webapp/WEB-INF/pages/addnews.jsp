<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>
<!------ Include the above in your HEAD tag ---------->
<div class="container">
    <table class="table">
        <form class="list_news" action="do?command=addnews" method=POST>
            <p></p>
            <p></p>
            <div class="input-group input-group-sm mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-sm">Title</span>
                </div>
                <input id="title" type="text" name="title" class="form-control" aria-label="title" aria-describedby="inputGroup-sizing-sm">
            </div>
            <p></p>
            <div class="input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-lg">Content</span>
                </div>
                <input id="content" type="text" name="content" class="form-control" aria-label="content" aria-describedby="inputGroup-sizing-sm">
            </div>
            <p></p>
            <button id="add" value="add" name="add" class="btn btn-success col-lg-2">
                ADD
            </button>
            <p></p>
        </form>
    </table>
    <form class="form-horizontal" method="post" action="do?command=profile">
        <fieldset>
            <div class="form-group">
                <label class="col-md-4 control-label" for="return"></label>
                <div class="col-md-4">
                    <button id="return" name="return" class="btn btn-primary">Return</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>