<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>
<!------ Include the above in your HEAD tag ---------->
<div class="container">
    <div class="heading">
        <h3 class="title" align="center">${singleNews.title}</h3>
    </div>
    <table class="table">
        <thead>
        <div class="row">
            <div class=col-lg-1>
                <p>#</p>
            </div>
            <div class=col-lg-1>
                <p>User ID</p>
            </div>
            <div class=col-lg-6>
                <p>Comment</p>
            </div>
            <div class=col-lg-2>
                <p>Date</p>
            </div>
        </div>
        </thead>
        <tbody>
        <div class="comments">
            <c:forEach items="${comments}" var="item">
                <form class="list_news" action="do?command=editnews" method=POST>
                    <div class="row">
                        <input name="newsId" type="hidden" value="${newsId}"/>
                        <input name="id" type="hidden" value="${item.id}"/>
                        <div class=col-lg-1>
                            <p>${item.id}</p>
                        </div>
                        <div class=col-lg-1>
                            <p>${item.userId}</p>
                        </div>
                        <div class=col-lg-6>
                            <p>${item.content}</p>
                        </div>
                        <div class=col-lg-2>
                            <p>${item.created}</p>
                        </div>
                        <button id="deletecomment" value="deletecomment" name="deletecomment" class="btn btn-success col-lg-2">
                            Delete Comment
                        </button>
                    </div>
                </form>
            </c:forEach>
        </div>
        </tbody>
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