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
    <div class="h">Редактировать день</div>
    <c:url var="editNewDayAction" value="/addNewDay/${report.id}/saveOrUpdateDay"/>
    <form:form action="${editNewDayAction}" commandName="day">
      <table class="big_table">
        <tr>
          <td><form:hidden path="id"/></td>
        </tr>
        <tr>
          <td ><form:label cssClass="form_label" path="date"><spring:message text="Дата"/></form:label></td>
          <td><form:input cssClass="form_input" path="date"/></td>
        </tr>

        <tr>
          <td><form:label cssClass="form_label" path="summ"><spring:message text="Сумма заправки"/></form:label></td>
          <td><form:input type="number" step="0.01" cssClass="form_input" path="summ"/></td>
        </tr>

        <tr>
          <td><form:label cssClass="form_label" path="costByLiter"><spring:message text="Цена за литр"/></form:label></td>
          <td><form:input type="number" step="0.01" cssClass="form_input" path="costByLiter"/></td>
        </tr>

        <tr>

            <table>
                <c:if test="${!empty day.points}">
              <tr>
                <th class="long_table_head">Пункт отправления</th>
                <th class="long_table_head">Пункт прибытия</th>

                <c:if test="${report.user.accountancyType eq 'зональная'}">
                  <th class="long_table_head">Зональная стоимость</th>
                </c:if>


                <th class="long_table_head">Фактический адрес</th>
                <th class="long_table_head">Удалить</th>
                <th class="long_table_head">Изменить</th>
              </tr>

              <c:forEach items="${day.points}" var="point">
                <tr>
                  <td class="big_table_td">${point.depAdress}</td>
                  <td class="big_table_td">${point.arrAdress}</td>

                  <c:if test="${report.user.accountancyType eq 'зональная'}">
                    <td class="big_table_td">${point.costByZone}</td>
                  </c:if>

                  <td class="big_table_td">${point.factAdress}</td>
                  <td>
                    <a class="no_decor" href="<c:url value="/editDay/${day.id}/deletePoint/${point.id}"/>"><div class="edit_button">Удалить</div></a>
                  </td>

                  <td>
                    <a class="no_decor" href="<c:url value="/addtheroute/${day.id}/editPoint/${point.id}"/>"><div class="edit_button">Изменить</div></a>

                  </td>
                </tr>
              </c:forEach>
                </c:if>

                <tr>
                    <td colspan="6"><input type="submit" class="welcome_button _100per" value="<spring:message text="Изменить"/>"/></td>
                </tr>

                <tr>
                    <td colspan="6">
                        <a class="no_decor" href="<c:url value="/addtheroute/${day.id}"/>"><div class="welcome_button">Добавить маршрут</div></a>
                    </td>

                </tr>

            </table>

        </tr>



      </table>




    </form:form>
    </div>
  </body>
</html>
