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
      <div class="h">Управление отчетами</div>

      <div class="menu _800">
        <a class="no_decor" href="<c:url value="/welcome"/>"><div class="welcome_button">Назад</div></a>
      </div>


      <c:if test="${!empty reportsList}">
        <table class="big_table">
          <tr>
            <th class="big_table_head">Посмотреть отчет водителя</th>
            <th class="big_table_head">Водитель</th>
            <th class="big_table_head">Посмотреть данные для бухгалтерского отчета</th>
            <th class="big_table_head">Сформоровать xlsx-файл и отправить на e-mail</th>
            <%--<th class="big_table_head">Печать</th>--%>

          </tr>
          <c:forEach items="${reportsList}" var="report">
            <tr>


              <td><a class="no_decor" href="<c:url value="/managerShowReport/${report.id}"/>"><div class="welcome_button no_margin">${report.period}</div></a></td>

              <td class="big_table_td">
                <c:choose>
                  <c:when test="${report.user.surname eq null}">${report.user.username}</c:when>
                  <c:otherwise>${report.user.surname}</c:otherwise>
                </c:choose>

              </td>


              <td><a class="no_decor" href="<c:url value="/managerShowReportFull/${report.id}"/>"><div class="welcome_button no_margin">${report.period}</div></a></td>



              <td><a class="no_decor" href="<c:url value="/makeXlsx/${report.id}"/>"><div class="welcome_button no_margin">Сформировать</div></a></td>

              <%--<td><a class="no_decor" href="<c:url value="/printXlsx/${report.id}"/>"><div class="welcome_button no_margin">Печать</div></a></td>--%>


            <%--<td><a class="no_decor" href="<c:url value="/deleteReport/${report.id}"/>"><div class="welcome_button no_margin">Удалить</div></a></td>--%>


            </tr>
          </c:forEach>
        </table>
      </c:if>

      <div class="menu _800">
        <%--<a class="no_decor" href="<c:url value="/createAReport"/>"><div class="welcome_button">Создать новый отчет</div></a>--%>
        <a class="no_decor" href="<c:url value="/welcome"/>"><div class="welcome_button">Назад</div></a>
      </div>

    </div>
  </body>
</html>
