<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
  <title>costByZone</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body>



<header>
  <div class="container">
    <div>Таблица стоимости зон</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>


<div class="top">








    <c:if test="${message != null}">
      <div> Сообщение: ${message}</div>
    </c:if>

    <c:if test="${error !=null}">
      <div> Ошибка: ${error}</div>
    </c:if>



      <table class="small_table" id="adresses_width">
      <c:if test="${!empty costByZoneList}">


        <tr>
          <th>Номер зоны</th>
          <th>Стоимость зоны</th>
          <th></th>
        </tr>

        <c:forEach items="${costByZoneList}" var="costByZone">
          <tr>
            <td>${costByZone.numberZone}</td>
            <td>${costByZone.costByZone}</td>

            <td><a href="<c:url value="/editCostByZone/${costByZone.id}"/>">Редактировать</a></td>
          </tr>
        </c:forEach>


      </c:if>

        <tr><td colspan="3"><a id="save_button"  href="<c:url value="/addZone"/>">Добавить зону</a></td>

        </tr>
      </table>





</div>
</body>
</html>
