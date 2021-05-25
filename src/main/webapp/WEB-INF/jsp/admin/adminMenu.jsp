<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Delivery Service</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="index.jsp"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="controller?command?listOrders">List of orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=destination">Destination</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"  href="controller?command=price">Pricing</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"  href="controller?command=logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
</header>

</body>
</html>
