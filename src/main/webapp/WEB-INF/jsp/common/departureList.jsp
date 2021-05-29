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

<%@include file="/WEB-INF/jspf/fragment/destinationsForm.jspf" %>

<table class="table table-condensed" style="margin-left:50px;margin-right:300px;align-content:center"
       title="Destinations">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="city.arrive"/></th>
        <th scope="col"><fmt:message key="city.departure"/></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="k" value="0"/>
    <c:forEach var="item" items="${sessionScope.get('destinationsBeans')}">
        <c:set var="k" value="${k+1}"/>
        <tr>
            <td><fmt:message key="city.${item.cityArrive}"/></td>
            <td><fmt:message key="city.${item.cityDeparture}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<nav aria-label="...">
    <ul class="pagination pagination-sm" style="margin-left: 600px">

        <c:set var="page_number" value="${sessionScope.pageNumber}"/>

        <c:if test="${page_number == 1}">
            <c:set var="disable_pr" value="disabled"/>
        </c:if>

        <c:if test="${page_number == 7}">
            <c:set var="disable_next" value="disabled"/>
        </c:if>

        <li class="page-item ${disable_pr}"><a class="page-link ${disable_pr}"
                                 href="controller?command=destination&pageNumber=${page_number-1}">Previous</a></li>
        <li class="page-item ${disable_next}"><a class="page-link ${disable_next}" href="controller?command=destination&pageNumber=${page_number+1}">Next</a>
        </li>
    </ul>
</nav>


</body>
</html>
