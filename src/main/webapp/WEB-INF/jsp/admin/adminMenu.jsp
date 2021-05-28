<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="resources"/>
<html>
<head>
    <title>Admin page</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<header>
    <a style="margin-left: 30px" href="controller?command=changeLocale&locale=uk" class="language" rel="uk"><img
            src="img/uk.png" alt="uk"/>
        <a href="controller?command=changeLocale&locale=en" class="language" rel="en"><img src="img/en.png"
                                                                                           alt="en"/>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">Delivery Service</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="index.jsp"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="controller?command?listOrders"><fmt:message
                                    key="admin.page.header.list_orders"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=destination">Destination</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=price">Pricing</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="controller?command=logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </a>
    </a>
</header>

</body>
</html>
