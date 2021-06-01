<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf"%>

<fmt:message key="error_page.message"/>

<c:out value="${requestScope.errorMessage}"/>
</body>
</html>
