<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="resources"/>
<html>
<head>
    <title>User menu</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerUser.jspf" %>
<div style="margin-left: 30px; color: darkgreen">
    <fmt:message key="user_menu.hello"/>, &nbsp<c:out value="${sessionScope.firstName}"/><br>
    <fmt:message key="user_menu.balance"/>&nbsp<c:out value="${sessionScope.balance}"/>&nbsp<fmt:message key="user_menu.UAH"/>
</div>


</body>
</html>
