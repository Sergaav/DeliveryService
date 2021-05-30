<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title><fmt:message key="calculate.page_name"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<body>

<div class="container">
    <select class="form-select" aria-label="City Arrive">
        <option selected>Choose city arrive</option>
        <c:set var="k" value="0"/>
        <c:forEach var="item" items="${sessionScope.cities}">
            <c:set var="k" value="${k+1}"/>
            <option value="${item.name()}"><fmt:message key="city.${item.name()}"/></option>
        </c:forEach>
    </select>
</div>
</body>
</html>
