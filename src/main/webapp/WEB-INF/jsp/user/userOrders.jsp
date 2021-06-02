<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="resources"/>
<html>
<head>
    <title>User orders</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerUser.jspf" %>
<div style="margin-left: 30px; color: darkgreen">
    <fmt:message key="user_menu.hello"/>, &nbsp<c:out value="${sessionScope.firstName}"/><br>
    <fmt:message key="user_menu.balance"/>&nbsp<c:out value="${sessionScope.balance}"/>&nbsp<fmt:message key="user_menu.UAH"/>
</div>

<table class="table table-condensed" style="align-content:center"
       title="Destinations" aria-describedby="">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="order.id"/></th>
        <th scope="col"><fmt:message key="city.arrive"/></th>
        <th scope="col"><fmt:message key="city.departure"/></th>
        <th scope="col"><fmt:message key="parcel.weight"/></th>
        <th scope="col"><fmt:message key="order.bill"/></th>
        <th scope="col"><fmt:message key="date.create"/></th>
        <th scope="col"><fmt:message key="date.delivery"/></th>
        <th scope="col"><fmt:message key="order.status"/></th>
        <th scope="col"><fmt:message key="order.navigation"/></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="k" value="0"/>
    <c:forEach var="item" items="${sessionScope.get('userOrders')}">
        <c:set var="k" value="${k+1}"/>
        <tr>
            <td><fmt:message key="item.${item.cityArrive}"/></td>
            <td><fmt:message key="item.${item.cityDeparture}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
