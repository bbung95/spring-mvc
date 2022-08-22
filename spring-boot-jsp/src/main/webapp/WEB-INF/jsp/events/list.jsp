<%--
  Created by IntelliJ IDEA.
  User: bbung
  Date: 2022/08/22
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>이벤트 목록</h1>
    <h2>${message}</h2>
    <table>
        <thead>
            <tr>
                <th>이름</th>
                <th>시작</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.name}</td>
                    <td>${event.starts}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
