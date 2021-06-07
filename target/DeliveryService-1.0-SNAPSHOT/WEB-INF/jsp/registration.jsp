<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

<head>
    <title>Registration</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>
    <tr >
        <td class="content center">
            <form action="controller" method="get">
                <input type="hidden" name="command" value="registration">
                <input type="hidden" name="role" value="1">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputEmail1"><fmt:message key="login.login.name"/></label>
                        <input pattern="[A-Za-z0-9]{5,10}" type="text" name="login" class="form-control" id="exampleInputEmail1"
                               aria-describedby="emailHelp" placeholder=<fmt:message key="login.login.name"/> >
                        <small id="emailHelp" class="form-text text-muted"><fmt:message key="login.login.text"/></small>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputPassword1"><fmt:message key="login.password.name"/> </label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1"
                               placeholder="<fmt:message key="login.password.name"/>">
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputName"><fmt:message key="registration.first_name"/></label>
                        <input type="text" name="first_name" class="form-control" id="exampleInputName"
                               placeholder="<fmt:message key="registration.first_name"/>">
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputLastName"><fmt:message key="registration.last_name"/></label>
                        <input type="text" name="last_name" class="form-control" id="exampleInputLastName"
                               placeholder="<fmt:message key="registration.last_name"/>">
                    </div>
                </div>
                <button style="margin-left: 30px" type="submit" class="btn btn-primary"><fmt:message key="registration.registr" /></button>
            </form>
        </td>
    </tr>
<div style="margin-left: 30px;color: #942a25">
<c:out value="${requestScope.errorMessage}"/>
</div>
</body>
</html>
