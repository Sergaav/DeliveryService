<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Delivery service</title>
<%@include file="WEB-INF/jspf/head.jspf" %>
</head>

<body>
<%@include file="WEB-INF/jspf/header.jspf" %>

<div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="${pageContext.request.contextPath}/img/delivery-service-banner.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/img/delivery-service-banner.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/img/delivery-service-banner.jpg" class="d-block w-100" alt="...">
        </div>
    </div>
</div>

<%@include file="WEB-INF/jspf/footer.jspf" %>
</html>