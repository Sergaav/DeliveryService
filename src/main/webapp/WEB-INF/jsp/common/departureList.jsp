<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Departures</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<%@include file="/WEB-INF/jspf/fragment/destinationsForm.jspf"%>

<table class="table table-condensed" style="margin-left:50px;margin-right:300px;align-content:center"  title="Destinations">
    <thead>
    <tr>
        <th scope="col">City arrive</th>
        <th scope="col">City departure</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="k" value="0"/>
    <c:forEach var="item" items="${sessionScope.get('destinationsBeans')}">
        <c:set var="k" value="${k+1}"/>
        <tr>
            <td>${item.cityArrive}</td>
            <td>${item.cityDeparture}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
