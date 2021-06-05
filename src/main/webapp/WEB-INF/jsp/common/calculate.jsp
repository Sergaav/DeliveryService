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


<form style="margin-left: 400px" id="settings_form" action="controller" method="get">
    <fieldset style="background: #dff0d8; margin-top:10px;border: 3px solid #fff;
    border-radius: 10px;width: 500px;height: 500px;">
        <legend></legend>
        <input type="hidden" name="command" value="calculate">
        <h3>
            <fmt:message key="calculate.header_info"/>
        </h3>
        <br><br>
        <select class="form-select" name="city_arr" style="margin-left: 20px" aria-label="City Arrive">
            <option selected><fmt:message key="city.choose.arrive"/></option>
            <c:set var="k" value="0"/>
            <c:forEach var="cityArr" items="${sessionScope.cities}">
                <c:set var="k" value="${k+1}"/>
                <option value="${cityArr.ordinal()}"><fmt:message key="city.${cityArr.name}"/></option>
            </c:forEach>
        </select>
        <select class="form-select" name="city_dep" style="margin-left: 20px" aria-label="City Departure">
            <option selected><fmt:message key="city.choose.arrive"/></option>
            <c:set var="k" value="0"/>
            <c:forEach var="cityDep" items="${sessionScope.cities}">
                <c:set var="k" value="${k+1}"/>
                <option value="${cityDep.ordinal()}"><fmt:message key="city.${cityDep.name}"/></option>
            </c:forEach>
        </select>
        <br><br><br>
        <select class="form-select" style="margin-left: 20px" name="weight" aria-label="Weight choose">
            <option selected><fmt:message key="price.choose.name"/></option>
            <c:set var="k" value="0"/>
            <c:forEach var="priceBean" items="${sessionScope.priceBeans}">
                <c:set var="k" value="${k+1}"/>
                <option value="${priceBean.maxWeight}"><fmt:message key="price.name.id${priceBean.id}"/></option>
            </c:forEach>
        </select>
        <br><br><br>
        <div class="row" style="margin-left: 20px">
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="length"><fmt:message key="calculate.length"/> </span>
                <input type="text" name="length" class="form-control-sm-4"
                       placeholder="<fmt:message key="calculate.volume_param_text"/>"
                       aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-sm">
            </div>
            <br>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="width"><fmt:message key="calculate.width"/></span>
                <input type="text" class="form-control-sm-4" name="width"
                       placeholder="<fmt:message key="calculate.volume_param_text"/>"
                       aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-sm">
            </div>
            <br>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="height"><fmt:message key="calculate.height"/></span>
                <input type="text" class="form-control-sm-4" name="height" placeholder="<fmt:message
            key="calculate.volume_param_text"/>" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-sm"/>
            </div>
        </div>
        <button type="submit" style="margin-left: 50px" class="btn btn-primary"><fmt:message
                key="calculate.buton.calculate"/></button>

        <div style="margin-left: 30px;color: #942a25">
            <c:out value="${requestScope.errorMessage}"/>
        </div>
        <div style="font-size:large ; margin-left: 30px;color: #942a25">
            <fmt:message key="user_menu.shipping_cost"/> <c:out value="${sessionScope.cost}"/> <fmt:message
                key="user_menu.UAH"/>
        </div>
    </fieldset>
</form>


</body>
</html>
