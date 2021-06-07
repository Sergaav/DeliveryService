<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="resources"/>
<html>
<head>
    <title><fmt:message key="user.menu.title"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerUser.jspf"%>

<form style="margin-left: 50px" id="settings_form" action="controller" method="get">
    <input type="hidden" name="command" value="topup">
    <input type="number" pattern="^\\d*" name="amount" class="col-sm-2">
    <button type="submit" class="btn btn-primary"><fmt:message key="user.button.topup"/> </button>
</form>
<div style="margin-left: 30px;color: #942a25;font-weight: bold">
<c:out value="${requestScope.errorMessage}"/>
</div>
</body>
</html>