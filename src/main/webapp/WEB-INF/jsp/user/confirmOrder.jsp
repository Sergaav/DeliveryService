<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm Order</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerUser.jspf" %>

<form style="margin-left: 50px" id="settings_form" action="controller" method="get">
    <input type="hidden" name="command" value="confirmOrder">
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
            <th><fmt:message key="order.form.name.address"/></th>
            <td><c:out value="${sessionScope.orderBean.address}"/></td>
        </tr>
        <tr>
            <th><fmt:message key="parcel.weight"/></th>
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
            <th><fmt:message key="order.bill"/></th>
            <td><fmt:formatNumber maxFractionDigits="2" value="${sessionScope.orderBean.bill}"
                                  type="currency" currencyCode="UAH"/></td>
        </tr>
    </table>
    <button style="margin-left: 30px" type="submit" class="btn btn-primary"><fmt:message key="order.button.confirm"/></button><br>
    <button type="button" onclick="history.back()" style="margin-left: 50px" class="btn btn-primary"><fmt:message
            key="order.view.button.back"/></button>
</form>
</body>
</html>
