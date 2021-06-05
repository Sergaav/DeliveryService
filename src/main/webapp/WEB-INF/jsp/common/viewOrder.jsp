<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>View order</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerAdmin.jspf" %>

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
        <td><c:out value="${sessionScope.orderBean.parcel.length}"/>x<c:out
                value="${sessionScope.orderBean.parcel.height}"/>x
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
</body>
</html>
