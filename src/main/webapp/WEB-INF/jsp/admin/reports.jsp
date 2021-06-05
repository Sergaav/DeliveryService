<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="resources"/>
<html>
<head>
    <title>Reports</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/headerAdmin.jspf" %>

<form style="margin-left: 400px" id="settings_form" action="controller" method="get">
    <fieldset style="background: #dff0d8; margin-top:10px;border: 3px solid #fff;
    border-radius: 10px;width: 600px;height: 600px;">
        <legend></legend>
        <input type="hidden" name="command" value="report">
        <h3>
            <fmt:message key="admin.reports.name"/>
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
        <div style="margin-top: 20px;margin-left: 30px" class="row-3">
            <input type="date" class="form-control-sm" name="dateArrive" aria-label="date">
            <button type="submit" style="margin-left: 20px" class="btn btn-primary"><fmt:message
                    key="admin.make.report"/></button>
        </div>
        <br><br>
        <h6>Statistic for 2021-06-05 for city arrive Kiev city Departure =all</h6>
        <table class="table table-condensed" style="align-content:center">
            <tr>
                <th>Total orders: </th>
                <td>10</td>
            </tr>
            <tr>
                <th>paid orders: </th>
                <td>5</td>
            </tr>
            <tr>
                <th>new orders: </th>
                <td>5</td>
            </tr>
            <tr>
                <th>closed orders: </th>
                <td>5</td>
            </tr>
            <tr>
                <th>Sum UAH orders: </th>
                <td>600</td>
            </tr>
        </table>

    </fieldset>
</form>

</body>
</html>
