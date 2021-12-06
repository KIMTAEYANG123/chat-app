<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="ko">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${path}/resources/css/chatList.css" rel="stylesheet"/>
    </head>
    <body>
        <h3>채팅 리스트</h3>
        <div>
            <ul>
                <c:forEach items="${lists}" var="list">
                    <a href="/room/enter/${list.roomId}"><li>방  :  ${list.name}</li></a>
                </c:forEach>
            </ul>
        </div>
        <div>
            <form action="<c:url value="/create"/>" method="post">
               <input type="text" name="name" class="form-control name">
               <button class="enter" type="submit">개설하기</button>
            </form>
        </div>
        <script src="${path}/resources/js/chatList.js"></script>
    </body>
</html>