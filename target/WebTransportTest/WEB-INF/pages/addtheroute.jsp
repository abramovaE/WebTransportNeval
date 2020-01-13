<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>




<html>
  <head>
    <title>Добавить маршрут</title>
    <style>
      <%@include file="../../resources/style.css" %>
    </style>
  </head>

  <body class="background_blue">
    <div class="top">


        <table class="big_table">
            <c:if test="${!empty allPoints}">
          <tr>
            <th style="width: 450px" class="long_table_head">Пункт отправления</th>
            <th style="width: 450px" class="long_table_head">Пункт прибытия</th>

              <c:if test="${report.user.accountancyType eq 'зональная'}">
                <th style="width: 200px" class="long_table_head">Зональная стоимость</th>
              </c:if>

            <th style="width: 300px" class="long_table_head">Фактический адрес</th>
            <th style="width: 200px" class="long_table_head">Удалить строку</th>
            <th style="width: 200px" class="long_table_head">Изменить строку</th>
          </tr>
          <c:forEach items="${allPoints}" var="point">
            <tr>
              <td class="big_table_td">${point.depAdress}</td>
              <td class="big_table_td">${point.arrAdress}</td>

              <c:if test="${report.user.accountancyType eq 'зональная'}">
                <td class="big_table_td">${point.costByZone}</td>
              </c:if>

              <td class="big_table_td">${point.factAdress}</td>
              <td><a class="no_decor" href="<c:url value="/addtheroute/${day.id}/deletePoint/${point.id}"/>"><div class="edit_button">Удалить</div></a></td>
              <td><a class="no_decor" href="<c:url value="/addtheroute/${day.id}/editPoint/${point.id}"/>"><div class="edit_button">Изменить</div></a></td>
            </tr>
          </c:forEach>
            </c:if>
        </table>



    <c:url var="addThePointAction" value="/addtheroute/${day.id}/addthepoint"/>
    <form:form action="${addThePointAction}" commandName="point">
      <table class="big_table">
        <tr>
          <th style="width: 450px" class="long_table_head">Пункт отправления</th>
          <th style="width: 450px" class="long_table_head">Пункт прибытия</th>

          <c:if test="${report.user.accountancyType eq 'зональная'}">
            <th style="width: 200px" class="long_table_head">Зональная стоимость</th>
          </c:if>

          <th style="width: 300px" class="long_table_head">Фактический адрес</th>
        </tr>
        <tr>
          <td>
              <input value="${depPoint}" autocomplete="off" name="depAdress" class="input_small" list="depAdressesList">
              <datalist id="depAdressesList">
                <c:forEach var="adress" items="${allAdresses}">
                  <option value="${adress.adress}"></option>
                </c:forEach>
              </datalist>
          </td>
          <td>
              <input autocomplete="off" name="arrAdress" class="input_small" list="arrAdressesList">
              <datalist id="arrAdressesList">
                <c:forEach var="adress" items="${allAdresses}">
                  <option value="${adress.adress}"></option>
                </c:forEach>
              </datalist>
          </td>

          <c:if test="${report.user.accountancyType eq 'зональная'}">
            <td>
              <form:input cssClass="input_small" path="costByZone" readonly="true"/>
            </td>
          </c:if>


          <td>
            <form:input cssClass="input_small" path="factAdress"/>
          </td>


          </tr>


        <tr>

          <td colspan="4">
              <input type="submit" class="login_button _100per" value="<spring:message text="Сохранить и продолжить"/>" />
          </td>


        </tr>

          <tr>
              <td colspan="4">
                  <a class="no_decor" href="<c:url value="/showReport/${report.id}"/>"><div class="login_button">Вернуться в отчет</div></a>
              </td>

          </tr>
      </table>
    </form:form>

    </div>
  </body>
</html>






<%--<td width="200px">--%>
<%--<form:select path="depAdress">--%>
<%--<c:forEach var="adress" items="${allAdresses}">--%>
<%--<form:option value="${adress.adress}"></form:option>--%>
<%--</c:forEach>--%>
<%--</form:select>--%>
<%--</td>--%>
