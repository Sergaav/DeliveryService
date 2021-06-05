<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<html>
<head>
    <title>Login page</title>

</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<tr>
    <td class="content center">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="login">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="exampleInputEmail1"><fmt:message key="login.login.name"/></label>
                    <input pattern="[A-Za-z0-9]{5,10}" type="text" name="login" class="form-control"
                           id="exampleInputEmail1"
                           aria-describedby="emailHelp" placeholder="<fmt:message key="login.login.text"/>">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="exampleInputPassword1"><fmt:message key="login.password.name"/></label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword1"
                           placeholder="<fmt:message key="login.password.name"/>">
                </div>
            </div>
            <button style="margin-left: 30px" type="submit" class="btn btn-primary"><fmt:message key="login.button.signin"/></button>
            <a href="registration"><fmt:message key="login.button.registration"/></a>
        </form>
    </td>
</tr>
</body>
</html>
