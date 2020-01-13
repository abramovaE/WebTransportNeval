<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>

  <head>
    <title>Редактировать  пункт маршрута</title>
    <style>
      <%@include file="../../resources/style.css" %>
    </style>
  </head>

  <body class="background_blue">
      <div class="top">
        <div class="h">Редактировать  пункт маршрута</div>
        <c:url var="editNewPointAction" value="/addtheroute/${day.id}/addthepoint"/>
        <form:form action="${editNewPointAction}" commandName="point">
          <table class="big_table">
            <tr>
              <td><form:hidden path="id"/></td>
            </tr>

            <tr>
              <td class="big_table_label"><form:label path="depAdress"><spring:message text="Пункт отправления"/></form:label></td>
              <td class="big_table_input">
                <form:select cssClass="input" path="depAdress">
                  <c:forEach var="adress" items="${allAdresses}">
                    <form:option value="${adress.adress}"></form:option>
                  </c:forEach>
                </form:select>
              </td>
            </tr>

            <tr>
              <td class="big_table_label"><form:label path="arrAdress"><spring:message text="Пункт прибытия"/></form:label></td>
              <td class="big_table_input">
                <form:select cssClass="input" path="arrAdress">
                  <c:forEach var="adress" items="${allAdresses}">
                    <form:option value="${adress.adress}"></form:option>
                  </c:forEach>
                </form:select>
              </td>
            </tr>


            <c:if test="${report.user.accountancyType eq 'зональная'}">
              <tr>
                <td class="big_table_label"><form:label path="costByZone"><spring:message text="Зональная стоимость"/></form:label></td>
                <td class="big_table_input"><form:input cssClass="input" path="costByZone" readonly="true"/></td>
              </tr>
            </c:if>



            <tr>
              <td class="big_table_label"><form:label path="factAdress"><spring:message text="Фактический адрес"/></form:label></td>
              <td class="big_table_input"><form:input cssClass="input" path="factAdress"/></td>
            </tr>

            <tr>
              <td colspan="2"><input class="welcome_button _100per" type="submit" value="<spring:message text="Изменить"/>"/></td>
            </tr>
          </table>
        </form:form>
      </div>
  </body>
</html>
