<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Change order</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:if test="${sessionScope.role == 0}">
    <%@include file="/WEB-INF/jspf/headerAdmin.jspf" %>
</c:if>
<c:if test="${sessionScope.role == 1}">
    <%@include file="/WEB-INF/jspf/headerUser.jspf" %>
</c:if>
<h3><fmt:message key="order.change.view"/></h3>

<form style="margin-left: 50px" id="settings_form" action="controller" method="get">
    <input type="hidden" name="command" value="updateOrder">
    <input type="hidden" name="id" value="${sessionScope.orderBean.id}">
    <input type="hidden" name="userId" value="${sessionScope.orderBean.user.id}">
    <input type="hidden" name="parcelId" value="${sessionScope.orderBean.parcel.id}">
    <fieldset style="background: #dff0d8;outline: 2px solid #000;border: 3px solid #fff;
    border-radius: 10px;width: 1000px;height: 500px;">
        <h5>
            <fmt:message key="order.change.name"/>
        </h5>
        <div class="row">
            <fieldset style="margin-left:20px ;background: whitesmoke;outline: 2px solid #000;border: 3px solid #fff;
    border-radius: 10px;width: 400px;height:150px;">
                <legend><fmt:message key="order.form.name.choose_city"/></legend>
                <select class="form-select" name="city_arr" style="margin-left: 20px" aria-label="City Arrive">
                    <c:set var="select" value=""/>
                    <c:set var="k" value="0"/>
                    <c:forEach var="cityArr" items="${sessionScope.cities}">
                        <c:if test="${sessionScope.orderBean.cityArriveId eq k}"><c:set var="select" value="selected"/></c:if>
                        <c:set var="k" value="${k+1}"/>
                        <option ${select} value="${cityArr.ordinal()}"><fmt:message
                                key="city.${cityArr.name}"/></option>
                        <c:set var="select" value=""/>
                    </c:forEach>
                </select>
                <br>
                <select class="form-select" name="city_dep" style="margin-left: 20px" aria-label="City Departure">
                    <c:set var="select" value=""/>
                    <c:set var="k" value="0"/>
                    <c:forEach var="cityDep" items="${sessionScope.cities}">
                        <c:if test="${sessionScope.orderBean.cityDepartureId eq k}"><c:set var="select"
                                                                                           value="selected"/></c:if>
                        <c:set var="k" value="${k+1}"/>
                        <option ${select} value="${cityDep.ordinal()}"><fmt:message
                                key="city.${cityDep.name}"/></option>
                        <c:set var="select" value=""/>
                    </c:forEach>
                </select>
            </fieldset>
            <fieldset style="margin-left: 10px;background: whitesmoke;outline: 2px solid #000;border: 3px solid #fff;
    border-radius: 10px;width: 580px;height: 150px;">
                <legend><fmt:message key="order.form.name.recipient_data"/></legend>
                <div class="row" style="margin-left: 2px">
                    <div class="input-group">
                        <input type="text" value="${sessionScope.orderBean.recipientName}" name="recipientData"
                               class="form-control" aria-label="Sizing example input">
                    </div>
                    <br>
                    <div class="input-group input-group">
                        <input type="text" value="${sessionScope.orderBean.address}" class="form-control"
                               name="recipientAddress"
                               aria-label="Sizing example input">
                    </div>
                </div>
            </fieldset>
        </div>
        <br>
        <fieldset style="margin-left: 10px;background: whitesmoke;outline: 2px solid #000;border: 3px solid #fff;
    border-radius: 10px;width: 980px;height: 150px;">
            <legend><fmt:message key="order.form.parcel.data"/></legend>
            <div class="row">
                <select class="custom-select-" style="margin-left: 20px" name="weight" aria-label="Weight choose">
                    <c:set var="select" value=""/>
                    <c:set var="k" value="0"/>
                    <c:forEach var="priceBean" items="${sessionScope.priceBeans}">
                        <c:if test="${sessionScope.orderBean.parcel.weightRateId eq k}"><c:set var="select"
                                                                                               value="selected"/></c:if>
                        <c:set var="k" value="${k+1}"/>
                        <option ${select} value="${priceBean.maxWeight}"><fmt:message
                                key="price.name.id${priceBean.id}"/></option>
                        <c:set var="select" value=""/>
                    </c:forEach>
                </select>
                <div class="col-3">
                    <input type="number" class="form-control" name="length"
                           value="${sessionScope.orderBean.parcel.length}"
                           aria-label="length">
                </div>
                <div class="col-3">
                    <input type="number" class="form-control" name="height"
                           value="${sessionScope.orderBean.parcel.height}"
                           aria-label="height">
                </div>
                <div class="col-3">
                    <input type="number" class="form-control" name="width"
                           value="${sessionScope.orderBean.parcel.width}"
                           aria-label="width">
                </div>
            </div>

            <div style="margin-top: 10px" class="col-12">
                <input type="text" class="form-control" name="description"
                       value="${sessionScope.orderBean.parcel.description}"
                       aria-label="description">
            </div>
        </fieldset>

        <div style="margin-top: 20px" class="col-3">
            <input type="date" class="form-control" name="dateArrive" value="${sessionScope.orderBean.dateCreation}"
                   aria-label="date">
        </div>

        <button type="submit" style="margin-left: 400px;margin-top: 20px" class="btn btn-primary"><fmt:message
                key="order.button.confirm"/></button>
    </fieldset>
</form>
<div style="margin-left: 30px;color: #942a25">
    <c:out value="${sessionScope.errorMessage}"/>
</div>

</body>
</html>
