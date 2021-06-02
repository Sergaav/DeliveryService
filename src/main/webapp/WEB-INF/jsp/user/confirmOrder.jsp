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
    <fmt:message key="user_menu.balance"/>&nbsp<c:out value="${sessionScope.balance}"/>&nbsp<fmt:message
        key="user_menu.UAH"/>
</div>
<p>Confirm order</p>
<form style="margin-left: 50px" id="settings_form" action="controller" method="get">
    <input type="hidden" name="command" value="confirmOrder">
    <table class="table" aria-describedby="">
        <tr>
            <th>City arrive</th>
            <td><c:out value="${sessionScope.city[sessionScope.orderBean.cityArriveId]}"/></td>
        </tr>
        <tr>
            <th>City departure</th>
            <td><c:out value="${sessionScope.city[sessionScope.orderBean.cityDepartureId]}"/></td>
        </tr>
        <tr>
            <th>Sender</th>
            <td><c:out value="${sessionScope.orderBean.user.firstName}"/>&nbsp;
                <c:out value="${sessionScope.orderBean.user.lastName}"/></td>
        </tr>
        <tr>
            <th>Receiver</th>
            <td><c:out value="${sessionScope.orderBean.recipientName}"/></td>
        </tr>
        <tr>
            <th>Delivery address</th>
            <td><c:out value="${sessionScope.orderBean.address}"/></td>
        </tr>
        <tr>
            <th>Parcel weight</th>
            <td><c:out value="${sessionScope.orderBean.parcel.weight}"/></td>
        </tr>
        <tr>
            <th>Parcel parameters : L x H x W</th>
            <td><c:out value="${sessionScope.orderBean.parcel.length}"/>x<c:out value="${sessionScope.orderBean.parcel.height}"/>x
                <c:out value="${sessionScope.orderBean.parcel.width}"/></td>
        </tr>
        <tr>
            <th>Date creation</th>
            <td><c:out value="${sessionScope.orderBean.dateCreation}"/></td>
        </tr>
        <tr>
            <th>Date departure</th>
            <td><c:out value="${sessionScope.orderBean.dateDeparture}"/></td>
        </tr>
        <tr>
            <th>Total cost</th>
            <td><c:out value="${sessionScope.orderBean.bill}"/></td>
        </tr>
    </table>
    <button style="margin-left: 30px" type="submit" class="btn btn-primary">Confirm order</button>
</form>
</body>
</html>
