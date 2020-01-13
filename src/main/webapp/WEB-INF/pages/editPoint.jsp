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

  <body>
  <header>
    <div class="container">
      <div>Редактировать пункт маршрута</div>
      <c:forEach items="${topMenu}" var="btn">
        <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
      </c:forEach>
    </div>
  </header>



  <div class="top">
          <c:url var="editNewPointAction" value="/addtheroute/${day.id}/save"/>
          <form:form action="${editNewPointAction}" commandName="point">




            <table class="create_table" id="edit_form">
                <form:hidden path="id"/>
                <form:hidden path="distance"/>
                <form:hidden path="needChange"/>
                <form:hidden path="changed"/>
              <form:hidden path="position"/>

                <tr>
                <td><form:label path="depAdress"><spring:message text="Пункт отправления"/></form:label></td>
                <td>
                  <input value="${point.departure.address}" autocomplete="off" name="depAdress" list="depAdressesList">
                  <datalist id="depAdressesList">
                    <c:forEach var="address" items="${allAdresses}">
                      <option value="${address.address}"></option>
                    </c:forEach>
                  </datalist>
                </td>
              </tr>

              <tr>
                <td><form:label path="arrAdress"><spring:message text="Пункт прибытия"/></form:label></td>
                <td>
                  <input value="${point.arrival.address}" autocomplete="off" name="arrAdress" list="arrAdressesList">
                  <datalist id="arrAdressesList">
                    <c:forEach var="address" items="${allAdresses}">
                      <option value="${address.address}"></option>
                    </c:forEach>
                  </datalist>
                </td>
              </tr>


              <c:if test="${report.user.accountancyType eq 'зональная'}">
                <tr>
                  <td><form:label path="costByZone"><spring:message text="Зональная стоимость"/></form:label></td>
                  <td><form:input path="costByZone" readonly="true"/></td>
                </tr>
              </c:if>


              <tr>
                <td><form:label path="factAdress"><spring:message text="Фактический адрес"/></form:label></td>
                <td><form:input path="factAdress"/></td>
              </tr>



              <tr>
                <td><form:label path="zsd"><spring:message text="Проезд по ЗСД"/></form:label></td>
                <td><form:checkbox path="zsd"/></td>
              </tr>



              <tr>
                <td></td>
                <td><input id="submit_button" type="submit" value="<spring:message text="Изменить"/>"/></td>
              </tr>

            </table>
          </form:form>

      </div>
  </body>
</html>
