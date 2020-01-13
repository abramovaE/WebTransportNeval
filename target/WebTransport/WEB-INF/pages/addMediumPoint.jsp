<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>




<html>
<head>
  <title>Добавить промежуточный пункт</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body class="longsheet">

<header>
  <div class="container">
    <div>Добавить маршрут</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>


<div class="top">
  <c:if test="${text != null}">
    <script type="text/javascript">alert('${text}');</script>
  </c:if>



  <%--<table class="small_table">--%>
    <%--<c:if test="${!empty allPoints}">--%>
      <%--<tr>--%>
        <%--<th>Пункт отправления</th>--%>
        <%--<th>Пункт прибытия</th>--%>

        <%--<c:if test="${report.user.accountancyType eq 'зональная'}">--%>
          <%--<th>Зональная стоимость</th>--%>
        <%--</c:if>--%>

        <%--<th>Фактический адрес</th>--%>
        <%--<th>Проезд по ЗСД</th>--%>

        <%--<th>Удалить строку</th>--%>
        <%--<th>Изменить строку</th>--%>
      <%--</tr>--%>
      <%--<c:forEach items="${allPoints}" var="point">--%>
        <%--<tr>--%>
          <%--<td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.departure.address}</td>--%>
          <%--<td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.arrival.address}</td>--%>

          <%--<c:if test="${report.user.accountancyType eq 'зональная'}">--%>
            <%--<td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.costByZone}</td>--%>
          <%--</c:if>--%>

          <%--<td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.factAdress}</td>--%>
          <%--<td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">--%>
            <%--<c:if test="${point.zsd eq true}">Да</c:if>--%>
          <%--</td>--%>
          <%--<td><a href="<c:url value="/addtheroute/deletePoint/${point.id}"/>">Удалить</a></td>--%>
          <%--<td><a href="<c:url value="/editPoint/${point.id}"/>">Изменить</a></td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</c:if>--%>
  <%--</table>--%>



  <c:url var="addTheMediumPointAction" value="/addTheMediumPoint/${day.id}/save"/>
  <form:form action="${addTheMediumPointAction}" commandName="newPoint">

    <form:hidden path="distance"/>
    <form:hidden path="needChange"/>
    <form:hidden path="changed"/>
    <form:hidden path="position" value="${newPointPos}"/>
    <form:hidden path="departure.id" value="${departure.id}"/>



    <table class="create_table">
      <tr>
        <th>Пункт отправления</th>
        <th>Пункт прибытия</th>

        <c:if test="${report.user.accountancyType eq 'зональная'}">
          <th>Зональная стоимость</th>
        </c:if>

        <th>Фактический адрес</th>
        <th>Проезд по ЗСД</th>

      </tr>
      <tr>
        <td>
          <form:label path="departure.id">${departure.address}</form:label>
        </td>


        <td>

          <form:select path="arrival.id">
            <c:forEach items="${allAdresses}" var="adr">
              <form:option label="${adr.address}" value="${adr.id}"/>
            </c:forEach>
          </form:select>

          <%----%>
          <%--<input autocomplete="off" name="arrAdress" list="arrAdressesList">--%>
          <%--<datalist id="arrAdressesList">--%>
            <%--<c:forEach var="address" items="${allAdresses}">--%>
              <%--<option class="option" value="${address.address}"></option>--%>
            <%--</c:forEach>--%>
          <%--</datalist>--%>
        </td>

        <c:if test="${report.user.accountancyType eq 'зональная'}">
          <td>
            <form:input path="costByZone" readonly="true"/>
          </td>
        </c:if>


        <td>
          <form:input path="factAdress" />
        </td>

        <td>
          <form:checkbox path="zsd"/>
        </td>
      </tr>


      <tr>
        <td colspan="4">
          <input type="submit" id="submit_button" value="<spring:message text="Сохранить и продолжить"/>" />
        </td>
      </tr>

      <tr>
        <td colspan="4">
          <a id="save_button" href="<c:url value="/showReport/${report.id}"/>">Вернуться в отчет</a>
        </td>
      </tr>
    </table>
  </form:form>

</div>
</body>
</html>


