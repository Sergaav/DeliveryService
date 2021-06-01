<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create order</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerUser.jspf" %>
<div style="margin-left: 30px; color: darkgreen">
    <fmt:message key="user_menu.hello"/>, &nbsp<c:out value="${sessionScope.firstName}"/><br>
    <fmt:message key="user_menu.balance"/>&nbsp<c:out value="${sessionScope.balance}"/>&nbsp<fmt:message key="user_menu.UAH"/>
</div>

<%@include file="/WEB-INF/jspf/fragment/orderForm.jspf"%>


</body>
</html>
