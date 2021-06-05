<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>View order</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:if test="${sessionScope.role == 0}">
    <%@include file="/WEB-INF/jspf/headerAdmin.jspf" %>
</c:if>
<c:if test="${sessionScope.role == 1}">
    <%@include file="/WEB-INF/jspf/headerUser.jspf" %>
</c:if>
<h3><fmt:message key="order.view.preview_name"/></h3>

<table class="table" aria-describedby="">
    <tr>
        <th><fmt:message key="city.arrive"/></th>
        <td><fmt:message key="city.${sessionScope.orderBean.cityArriveId}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="city.departure"/></th>
        <td><fmt:message key="city.${sessionScope.orderBean.cityDepartureId}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="order.sender"/></th>
        <td><c:out value="${sessionScope.orderBean.user.firstName}"/>&nbsp;
            <c:out value="${sessionScope.orderBean.user.lastName}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="order.reciever"/></th>
        <td><c:out value="${sessionScope.orderBean.recipientName}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="order.view.address"/></th>
        <td><c:out value="${sessionScope.orderBean.address}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="parcel.weight.name"/></th>
        <td><c:out value="${sessionScope.orderBean.parcel.weight}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="order.view.parcel_param"/></th>
        <td><c:out value="${sessionScope.orderBean.parcel.length}"/>x<c:out
                value="${sessionScope.orderBean.parcel.height}"/>x
            <c:out value="${sessionScope.orderBean.parcel.width}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="date.create"/></th>
        <td><c:out value="${sessionScope.orderBean.dateCreation}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="date.delivery"/></th>
        <td><c:out value="${sessionScope.orderBean.dateDeparture}"/></td>
    </tr>
    <tr>
        <th><fmt:message key="order.view.total_cost"/></th>
        <td><fmt:formatNumber maxFractionDigits="2" value="${sessionScope.orderBean.bill}" type="currency"
                              currencyCode="UAH"/></td>
    </tr>
</table>
<button type="button" onclick="history.back()" style="margin-left: 50px" class="btn btn-primary"><fmt:message
        key="order.view.button.back"/></button>
<c:if test="${sessionScope.role == 0}">
    <c:if test="${sessionScope.orderBean.status.name() == 'OPENED'}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="checkout">
            <input type="hidden" name="id" value="${sessionScope.orderBean.id}">
            <button type="submit" style="margin-left: 50px" class="btn btn-primary"><fmt:message
                    key="order.button.checkout"/></button>
        </form>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="changeOrder">
            <input type="hidden" name="id" value="${sessionScope.orderBean.id}">
            <button type="submit" style="margin-left: 50px" class="btn btn-primary"><fmt:message
                    key="order.button.change"/></button>
        </form>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="deleteOrder">
            <input type="hidden" name="id" value="${sessionScope.orderBean.id}">
            <button type="submit" style="margin-left: 50px" class="btn btn-primary"><fmt:message
                    key="order.button.delete"/></button>
        </form>
    </c:if>
    <c:if test="${sessionScope.orderBean.status.name() == 'CONFIRMED'}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="deleteOrder">
            <input type="hidden" name="id" value="${sessionScope.orderBean.id}">
            <button type="submit" style="margin-left: 50px" class="btn btn-primary"><fmt:message
                    key="order.button.delete"/></button>
        </form>
    </c:if>
    <c:if test="${sessionScope.orderBean.status.name() == 'PAID'}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="closeOrder">
            <input type="hidden" name="id" value="${sessionScope.orderBean.id}">
            <button type="submit" style="margin-left: 50px" class="btn btn-primary"><fmt:message
                    key="order.button.close"/></button>
        </form>
    </c:if>
</c:if>

<c:if test="${sessionScope.role == 1}">
    <c:if test="${sessionScope.orderBean.status.name() == 'OPENED'}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="changeOrder">
            <input type="hidden" name="id" value="${sessionScope.orderBean.id}">
            <button type="submit" style="margin-left: 50px" class="btn btn-primary"><fmt:message
                    key="order.button.change"/></button>
        </form>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="deleteOrder">
            <input type="hidden" name="id" value="${sessionScope.orderBean.id}">
            <button type="submit" style="margin-left: 50px" class="btn btn-primary"><fmt:message
                    key="order.button.delete"/></button>
        </form>
    </c:if>
    <c:if test="${sessionScope.orderBean.status.name() == 'CONFIRMED'}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="payForOrder">
            <input type="hidden" name="id" value="${sessionScope.orderBean.id}">
            <button type="submit" style="margin-left: 50px" class="btn btn-primary"><fmt:message
                    key="order.button.pay"/></button>
        </form>
    </c:if>
</c:if>
</body>
</html>
