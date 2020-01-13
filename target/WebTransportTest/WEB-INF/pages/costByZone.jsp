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

<body class="background_blue">
<div class="top">
  <div>


    <c:if test="${message != null}">
      <div> Сообщение: ${message}</div>
    </c:if>

    <c:if test="${error !=null}">
      <div> Ошибка: ${error}</div>
    </c:if>


    <c:if test="${!empty costByZoneList}">
      <table>
        <tr>
          <th class="long_table_head">Номер зоны</th>
          <th class="long_table_head">Стоимость зоны</th>
          <th></th>
        </tr>

        <c:forEach items="${costByZoneList}" var="costByZone">
          <tr class="big_table_tr">
            <td class="big_table_td">${costByZone.numberZone}</td>
            <td class="big_table_td">${costByZone.costByZone}</td>

            <td class="big_table_td"><a class="no_decor" href="<c:url value="/editCostByZone/${costByZone.id}"/>"><div class="edit_button">Редактировать</div></a></td>
          </tr>
        </c:forEach>
      </table>
    </c:if>

    <div class="menu">
      <a class="no_decor" href="<c:url value="/addZone"/>"><div class="login_button">Добавить зону</div></a>
      <a class="no_decor" href="<c:url value="/welcome"/>"><div class="login_button">Назад</div></a>
    </div>

  </div>
</div>
</body>
</html>
