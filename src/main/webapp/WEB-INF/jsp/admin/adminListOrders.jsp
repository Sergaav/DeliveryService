<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="resources"/>
<html>
<head>
    <title>Admin page</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerAdmin.jspf" %>

<table class="table table-condensed" style="align-content:center"
       title="Destinations" aria-describedby="">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="order.id"/></th>
        <th scope="col"><fmt:message key="city.arrive"/></th>
        <th scope="col"><fmt:message key="city.departure"/></th>
        <th scope="col"><fmt:message key="order.sender"/></th>
        <th scope="col"><fmt:message key="order.reciever"/></th>
        <th scope="col"><fmt:message key="parcel.weight"/></th>
        <th scope="col"><fmt:message key="date.create"/></th>
        <th scope="col"><fmt:message key="date.delivery"/></th>
        <th scope="col"><fmt:message key="order.bill"/></th>
        <th scope="col"><fmt:message key="order.status"/></th>
        <th scope="col"><fmt:message key="order.navigation"/></th>

    </tr>
    </thead>
    <tbody>
    <c:set var="k" value="0"/>
    <c:forEach var="item" items="${sessionScope.orderBeanList}">
        <c:set var="k" value="${k+1}"/>
        <tr>
            <td>${item.id}</td>
            <td><fmt:message key="city.${item.cityArriveId}"/></td>
            <td><fmt:message key="city.${item.cityDepartureId}"/></td>
            <td>${item.user.firstName} ${item.user.lastName}</td>
            <td>${item.recipientName}</td>
            <td>${item.parcel.weight}</td>
            <td>${item.dateCreation}</td>
            <td>${item.dateDeparture}</td>
            <td>${item.bill}</td>
            <td>${item.status}</td>
            <td>
                <c:if test="${item.status=='OPENED'}">
                    <a href="controller?command=orderToPay&id=${item.id}"><c:out value="Checkout"/></a>
                </c:if>
                <c:if test="${item.status=='PAID'}">
                    <a href="controller?command=closeOrder&id=${item.id}"><c:out value="Close order"/></a>
                </c:if>
                <a href="#" target="_blank"><c:out value="View"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:if test="${sessionScope.pageNumber eq 1 or sessionScope.pageNumber eq sessionScope.avPages}">
            <c:set var="disable" value="disabled"/>
        </c:if>
        <li class="page-item ${disable}">
            <a class="page-link" href="controller?command=listOrders&page=${sessionScope.pageNumber-1}">Previous</a>
        </li>
        <c:forEach var="k" begin="1" step='1' end='${sessionScope.avPages}'>
            <li class="page-item"><a class="page-link" href="controller?command=listOrders&page=${k}">${k}</a></li>
        </c:forEach>
        <li class="page-item ${disable}">
            <a class="page-link" href="controller?command=listOrders&page=${sessionScope.pageNumber+1}">Next</a>
        </li>
    </ul>
</nav>


</body>
</html>
