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
    <fmt:message key="user_menu.balance"/>&nbsp<c:out value="${sessionScope.balance}"/>&nbsp<fmt:message
        key="user_menu.UAH"/>
</div>

<table class="table table-condensed" style="align-content:center"
       title="Destinations" aria-describedby="">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="order.id"/></th>
        <th scope="col"><fmt:message key="city.arrive"/></th>
        <th scope="col"><fmt:message key="city.departure"/></th>
        <th scope="col"><fmt:message key="order.reciver"/></th>
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
    <c:forEach var="item" items="${sessionScope.userOrders}">
        <c:set var="k" value="${k+1}"/>
        <tr>
            <td>${item.id}</td>
            <td><fmt:message key="city.${item.cityArriveId}"/></td>
            <td><fmt:message key="city.${item.cityDepartureId}"/></td>
            <td>${item.recipientName}</td>
            <td>${item.parcel.weight}</td>
            <td>${item.bill}</td>
            <td>${item.dateCreation}</td>
            <td>${item.dateDeparture}</td>
            <td>${item.status}</td>
            <td>
                <c:if test="${item.status=='OPENED'}">
                    <a href="controller?command=deleteOrder&id=${item.id}"><c:out value="Delete"/></a>
                </c:if>
                <c:if test="${item.status=='CONFIRMED'}">
                    <a href="controller?command=payForOrder&id=${item.id}"><c:out value="Pay order"/></a>
                </c:if>
                <a href="#" target="_blank"><c:out value="View"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
            <a class="page-link" href="#">Next</a>
        </li>
    </ul>
</nav>

</body>
</html>
