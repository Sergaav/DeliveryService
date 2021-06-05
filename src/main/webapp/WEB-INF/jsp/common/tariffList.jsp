<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title><fmt:message key="price.page_name"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>


<table class="table table-condensed" style="margin-left:50px;margin-right:300px;align-content:center"
       title="Price">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="price.rate_name.head"/></th>
        <th scope="col"><fmt:message key="price.max_rate.head"/></th>
        <th scope="col"><fmt:message key="price.tariff.head"/></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="k" value="0"/>
    <c:forEach var="item" items="${sessionScope.get('priceBeans')}">
        <c:set var="k" value="${k+1}"/>
        <tr>
            <td><fmt:message key="price.name.id${item.id}"/></td>
            <td>${item.maxWeight}</td>
            <td><fmt:formatNumber maxFractionDigits="2" value="${item.rate}" type ="currency" currencyCode="UAH"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
