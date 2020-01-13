<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>
<html>
  <head>
    <title>Управление отчетами</title>
    <style>
      <%@include file="../../resources/style.css" %>
    </style>
  </head>

  <body class="background_blue">
    <div class="top">
      <div class="h">Список отчетов пользователя ${name}</div>
      <c:if test="${!empty reportsList}">
        <table class="big_table">
          <tr>
            <th class="big_table_head">Период</th>
            <th class="big_table_head">Удалить</th>

          </tr>
          <c:forEach items="${reportsList}" var="report">
            <tr>


              <td><a class="no_decor" href="<c:url value="/showReport/${report.id}"/>"><div class="welcome_button no_margin">${report.period}</div></a></td>
              <%--<td>${report.user}</td>--%>
              <td><a class="no_decor" href="<c:url value="/deleteReport/${report.id}"/>"><div class="welcome_button no_margin">Удалить</div></a></td>


            </tr>
          </c:forEach>
        </table>
      </c:if>

      <div class="menu _800">
        <a class="no_decor" href="<c:url value="/createAReport"/>"><div class="welcome_button">Создать новый отчет</div></a>
        <a class="no_decor" href="<c:url value="/welcome"/>"><div class="welcome_button">Назад</div></a>
      </div>

    </div>
  </body>
</html>
