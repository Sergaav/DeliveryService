<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm order</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerUser.jspf" %>
<div style="margin-left: 30px; color: darkgreen">
    <fmt:message key="user_menu.hello"/>, &nbsp<c:out value="${sessionScope.firstName}"/><br>
    <fmt:message key="user_menu.balance"/>&nbsp<c:out value="${sessionScope.balance}"/>&nbsp<fmt:message key="user_menu.UAH"/>
</div>

<form style="margin-left: 50px" id="settings_form" action="controller" method="get">

    <fieldset style="background: whitesmoke;outline: 2px solid #000;border: 3px solid #fff;
    border-radius: 10px;width: 1000px;height: 500px;">
        <legend></legend>
        <input type="hidden" name="command" value="confirmOrder">
        <c:out value="${sessionScope.orderBean.user.firstName}"/> <c:out value="${sessionScope.orderBean.user.lastName}"/><br>
<%--        <c:out value="${sessionScope.cities.get(sessionScope.orderBean.cityArriveId)}"/>--%>
<%--        <c:out value="${sessionScope.cities.get(sessionScope.orderBean.cityDepartureId)}"/>--%>
        <br>
        <c:out value="${sessionScope.orderBean.recipientName}"/><br>
        <c:out value="${sessionScope.orderBean.address}"/>
        <c:out value="${sessionScope.orderBean.dateCreation}"/><br>
        <c:out value="${sessionScope.orderBean.dateDeparture}"/><br>
        <c:out value="${sessionScope.orderBean.bill}"/><br>

    </fieldset>
</form>
</body>
</html>
