<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/adminka.htm" %>
<!------ Include the above in your HEAD tag ---------->
<div class="container">
    <table class="table">
        <thead>
            <div class="row">
                <div class=col-lg-1>
                    <p>#</p>
                </div>
                <div class=col-lg-2>
                    <p>Title</p>
                </div>
                <div class=col-lg-4>
                    <p>Text</p>
                </div>
                <div class=col-lg-2>
                    <p>Date</p>
                </div>
            </div>
        </thead>
        <tbody>
        <div class="news">
            <c:forEach items="${news}" var="item">
                <form class="list_news" action="do?command=editnews" method=POST>
                    <div class="row">
                        <input name="newsId" type="hidden" value="${item.id}"/>
                        <div class=col-lg-1>
                            <p>${item.id}</p>
                        </div>
                        <div class=col-lg-2>
                            <p>${item.title}</p>
                        </div>
                        <div class=col-lg-4>
                            <p>${item.content}</p>
                        </div>
                        <div class=col-lg-2>
                            <p>${item.created}</p>
                        </div>
                        <button id="edit" value="edit" name="edit" class="btn btn-success col-lg-1">
                            Edit
                        </button>
                        <button id="delete" value="delete" name="delete" class="btn btn-danger col-lg-1">
                            Delete
                        </button>
                    </div>
                </form>
            </c:forEach>
        </div>
        </tbody>
    </table>
    <form class="form-horizontal" method="post" action="do?command=editnews">
        <fieldset>
            <div class="form-group">
                <label class="col-md-4 control-label" for="add">Make some news</label>
                <div class="col-md-4">
                    <button id="add" name="add" class="btn btn-primary">Add News</button>
                </div>
            </div>
        </fieldset>
    </form>
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