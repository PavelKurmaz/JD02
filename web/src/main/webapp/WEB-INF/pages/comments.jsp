<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/menu.htm" %>
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
            <div class=col-lg-6>
                <p>Comment</p>
            </div>
            <div class=col-lg-2>
                <p>UserId</p>
            </div>
            <div class=col-lg-2>
                <p>Date</p>
            </div>
        </div>
        </thead>
        <tbody>
        <div class="comments">
            <c:forEach items="${comments}" var="item">
                <form class="list_news" action="do?command=readnews" method=POST>
                    <div class="row">
                        <input name="newsId" type="hidden" value="${item.id}"/>
                        <div class=col-lg-1>
                            <p>${item.id}</p>
                        </div>
                        <div class=col-lg-6>
                            <p>${item.content}</p>
                        </div>
                        <div class=col-lg-2>
                            <p>${item.userDTO.login}</p>
                        </div>
                        <div class=col-lg-2>
                            <p>${item.created}</p>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </div>
        </tbody>
    </table>
    <form class="form-horizontal" method="post" action="do?command=readnews">
        <fieldset>
            <input name="newsId" type="hidden" value="${singleNews.id}"/>
            <div class="form-group">
                <p></p>
                <div class="input-group input-group-lg">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-lg">Comment</span>
                    </div>
                    <input id="content" type="text" name="content" class="form-control" aria-label="content" aria-describedby="inputGroup-sizing-sm">
                </div>
                <p></p>
                <button id="addcomment" value="addcomment" name="addcomment" class="btn btn-success col-lg-2">
                    Add Comment
                </button>
                <p></p>
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