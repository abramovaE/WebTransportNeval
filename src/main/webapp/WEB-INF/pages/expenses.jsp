<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>




<html>
<head>
  <title>${title} ${titleAdr}</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body>



<header>
  <div class="container">
    <div>${title} ${titleAdr}</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>

<c:if test="${text != null}">
  <script type="text/javascript">alert('${text}');</script>
</c:if>


<div class="top">
  <c:url var="showExpensesAction" value="/showExpenses/save"/>
  <form:form action="${showExpensesAction}" commandName="exp">


    <table class="create_table">
      <%--<tr>--%>
        <%--<td>--%>
          <%--<input autocomplete="off" name="address" list="addressesList">--%>
          <%--<datalist id="addressesList">--%>
            <%--<c:forEach var="address" items="${adressesList}">--%>
              <%--<option class="option" value="${address.address}"></option>--%>
            <%--</c:forEach>--%>
          <%--</datalist>--%>
        <%--</td>--%>
      <%--</tr>--%>

        <tr>
          <td>
            <form:select path="address.id">
              <c:forEach items="${addressesList}" var="address">
                <form:option label="${address.address}" value="${address.id}"/>
              </c:forEach>
            </form:select>
          </td>
        </tr>

        <tr>
          <td>
            <form:select path="user.id">
              <c:forEach items="${userList}" var="user">
                <form:option label="${user.shortFullName}" value="${user.id}"/>
              </c:forEach>
            </form:select>
          </td>
        </tr>


        <%--<tr>--%>
        <%--<td>--%>
          <%--<input autocomplete="off" name="user" list="usersList">--%>
          <%--<datalist id="usersList">--%>
            <%--<c:forEach var="usr" items="${userList}">--%>
              <%--<option class="option" value="${usr.shortFullName}"></option>--%>
            <%--</c:forEach>--%>
          <%--</datalist>--%>
        <%--</td>--%>
      <%--</tr>--%>

        <tr>
          <td><form:input path="startDay" type="number" min="1" max="31" required="true"/></td>
          <td>

            <form:select path="startMonth">
              <c:forEach items="${monthsList}" var="m">
                <form:option value="${m}"/>
              </c:forEach>
            </form:select>

          </td>
          <td><form:input path="startYear" type="number" min="2017" max="2100" required="true"/></td>
        </tr>

        <tr>
          <td><form:input path="endDay" type="number" min="1" max="31" required="true"/></td>
          <td>

            <form:select path="endMonth">
              <c:forEach items="${monthsList}" var="m">
                <form:option value="${m}"/>
              </c:forEach>
            </form:select>

          </td>
          <td><form:input path="endYear" type="number" min="2017" max="2100" required="true"/></td>
        </tr>




        <tr>
          <td><input id="submit_button" type="submit" value="<spring:message text="Показать"/>" onclick="window.close();"/></td>
        </tr>

    </table>


    <c:if test="${points ne null}">
      <table class="small_table">
        <th>Дата</th>
        <th>Адрес отправления</th>
        <th>Адрес прибытия</th>
        <th>Дистанция, км</th>
        <th>Цена за км, руб</th>
        <th>Сумма</th>
        <th></th>
        <c:forEach items="${points}" var="point">
          <tr>
            <td>${point.day.date}</td>
            <td>${point.departure.address}</td>
            <td>${point.arrival.address}</td>
            <td>${point.distance/1000}</td>
            <td>${point.day.report.priceForOneKm}</td>
            <td>${point.expensePrice}</td>
          </tr>
        </c:forEach>
        <tr>
          <td>Сумма затрат на адрес: </td>
          <td>${pointsSum} руб</td>
        </tr>
      </table>
    </c:if>




    <%--<table class="create_table" id="edit_form">--%>

      <%--<form:hidden path="id"/>--%>

      <%--<tr>--%>
        <%--<td><form:label path="address"><spring:message text="Адрес"/></form:label></td>--%>
        <%--<td><form:input path="address" readonly="false"/></td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td><form:label path="coordinates"><spring:message text="Координаты"/></form:label></td>--%>
        <%--<td><form:input path="coordinates"/></td>--%>
      <%--</tr>--%>
      <%--<tr>--%>
        <%--<td><form:label path="numberOfZone"><spring:message text="Номер зоны"/></form:label></td>--%>
        <%--<td><form:input path="numberOfZone"/></td>--%>
      <%--</tr>--%>


      <%--<c:choose>--%>
        <%--<c:when test="${isAdmin}">--%>
          <%--<tr>--%>
            <%--<td><form:label path="usedForAutoReports"><spring:message text="Использовать для авто отчета"/></form:label></td>--%>
            <%--<td><form:checkbox path="usedForAutoReports"/></td>--%>
          <%--</tr>--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
          <%--<form:hidden path="usedForAutoReports"/>--%>
        <%--</c:otherwise>--%>
      <%--</c:choose>--%>

      <%--<tr>--%>
        <%--<td></td>--%>
        <%--<td><input id="submit_button" type="submit" value="<spring:message text="Сохранить"/>"/></td>--%>
      <%--</tr>--%>
    <%--</table>--%>
  </form:form>
</div>
</body>
</html>
