<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>
<!------ Include the above in your HEAD tag ---------->
<div class="container">
    <table class="table">
        <form class="list_news" action="do?command=editnews" method=POST>
            <p></p>
            <input id="newsId" type="hidden" name="newsId" value="${singleNews.id}">
            <p></p>
            <div class="input-group input-group-sm mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-sm">Title</span>
                </div>
                <input id="title" type="text" name="title" value="${singleNews.title}" class="form-control" aria-label="title" aria-describedby="inputGroup-sizing-sm">
            </div>
            <p></p>
            <div class="input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-lg">Content</span>
                </div>
                <input id="content" type="text" name="content" value="${singleNews.content}" class="form-control" aria-label="content" aria-describedby="inputGroup-sizing-sm">
            </div>
            <p></p>
            <button id="update" value="update" name="update" class="btn btn-success col-lg-2">
                Update
            </button>
            <p></p>
            <button id="comments" value="comments" name="comments" class="btn btn-success col-lg-2">
                View Comments
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