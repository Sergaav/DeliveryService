<html>

<head>
    <title>Registration</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>
    <tr >
        <td class="content center">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="registration">
                <input type="hidden" name="role" value="1">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Login</label>
                        <input pattern="[A-Za-z0-9]{5,10}" type="text" name="login" class="form-control" id="exampleInputEmail1"
                               aria-describedby="emailHelp" placeholder="Enter login">
                        <small id="emailHelp" class="form-text text-muted">Only latin letters and digits!</small>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1"
                               placeholder="Password">
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputName">First Name</label>
                        <input type="text" name="first_name" class="form-control" id="exampleInputName"
                               placeholder="First Name">
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputLastName">Last Name</label>
                        <input type="text" name="last_name" class="form-control" id="exampleInputLastName"
                               placeholder="Last Name">
                    </div>
                </div>
                <button style="margin-left: 30px" type="submit" class="btn btn-primary">Submit</button>
            </form>
        </td>
    </tr>
</body>
</html>
