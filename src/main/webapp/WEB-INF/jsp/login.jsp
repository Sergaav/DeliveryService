<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Login</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<br>
<br>
<br>
<br>
<br>
<h5>
Please sign in or register
</h5>
<br>
<br>
<br>
<form method="post" action="servlet">
    <div class="form-row justify-content-center">
        <div class="col-auto">
            <label class="sr-only" for="inlineFormInput">Login</label>
            <input type="text" pattern="[A-Za-z0-9]{5,10}" class="form-control mb-2" id="inlineFormInput" placeholder="Enter your login">
        </div>
        <div class="col-auto">
            <label class="sr-only" for="inlineFormInputGroup">Password</label>
            <div class="input-group mb-2">
                <input type="password" class="form-control" id="inlineFormInputGroup" placeholder="Enter password">
            </div>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary mb-2">Submit</button>
        </div>
        <a href="registration">Click for registration.</a>
    </div>

</form>

</body>
</html>
