<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/menu.htm" %>
<!------ Include the above in your HEAD tag ---------->
<div class="container">
    <table class="table">
        <thead>
            <div class="row">
                <div class=col-md-1>
                    <p>#</p>
                </div>
                <div class=col-md-2>
                    <p>Title</p>
                </div>
                <div class=col-md-2>
                    <p>Text</p>
                </div>
                <div class=col-md-2>
                    <p>Date</p>
                </div>
                <div class=col-md-2>
                    <p>Comment</p>
                </div>
            </div>
        </thead>
        <tbody>
        <div class="news">
            <c:forEach items="${news}" var="item">
                <form class="list_news" action="do?command=editnews" method=POST>
                    <div class="row">
                        <input name="newsId" type="hidden" value="${item.id}"/>
                        <div class=col-md-1>
                            <p>${item.id}</p>
                        </div>
                        <div class=col-md-2>
                            <p>${item.title}</p>
                        </div>
                        <div class=col-md-2>
                            <p>${item.content}</p>
                        </div>
                        <div class=col-md-2>
                            <p>${item.created}</p>
                        </div>
                        <div class=col-md-3>
                            <input id="comment" class="form-control input-md" name="comment"
                                   value=""/>
                        </div>
                        <button id="addComment" value="addComment" name="addComment" class="btn btn-success col-md-2">
                            Make comment
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