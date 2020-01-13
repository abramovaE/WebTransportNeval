<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>


<html>
<head>
  <title>Редактировать день</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body>

<header>
  <div class="container">
    <div>Редактировать день</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>





<div class="top">
  <c:url var="editNewDayAction" value="/addNewDay/${report.id}/save"/>
  <form:form action="${editNewDayAction}" commandName="day">


      <table class="create_table" id="edit_form">
      <form:hidden path="id"/>

        <form:hidden path="dayDistance"/>


      <%--<tr>--%>
        <%--<td ><form:label path="date"><spring:message text="Дата"/></form:label></td>--%>
        <%--<td><form:input path="date"/></td>--%>
      <%--</tr>--%>


        <tr>
          <td ><form:label path="dayNumber"><spring:message text="Число"/></form:label></td>
          <td><form:input path="dayNumber"/></td>
        </tr>




        <tr>
        <td><form:label path="summ"><spring:message text="Сумма заправки"/></form:label></td>
        <td><form:input type="number" step="0.01" path="summ"/></td>
      </tr>

      <tr>
        <td><form:label path="costByLiter"><spring:message text="Цена за литр"/></form:label></td>
        <td><form:input type="number" step="0.01" path="costByLiter"/></td>
      </tr>


        <c:if test="${isMinibus}">
          <tr>
            <td><form:label path="shipping"><spring:message text="Грузоперевозки"/></form:label></td>
            <td><form:checkbox path="shipping"/></td>
          </tr>
        </c:if>

      </table>




        <table class="small_table">
          <c:if test="${!empty points}">
            <tr>
              <th>Пункт отправления</th>
              <th>Пункт прибытия</th>

              <c:if test="${report.user.accountancyType eq 'зональная'}">
                <th>Зональная стоимость</th>
              </c:if>

              <th>Фактический адрес</th>
              <th>Проезд по ЗСД</th>
              <th></th>
              <th></th>
              <th></th>
            </tr>

            <c:forEach items="${points}" var="point">
              <tr>

                <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.departure.address}</td>
                <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.arrival.address}</td>

                <c:if test="${report.user.accountancyType eq 'зональная'}">
                  <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.costByZone}</td>
                </c:if>

                <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.factAdress}</td>

                <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">
                  <c:if test="${point.zsd eq true}">Да</c:if>
                </td>

                <td>
                  <a href="<c:url value="/editDay/deletePoint/${point.id}"/>">Удалить</a>
                </td>

                <td>
                  <a href="<c:url value="/editPoint/${point.id}"/>">Изменить</a>
                </td>



                <td>
                  <a href="<c:url value="/addMediumPoint/${point.id}"/>">Добавить выезд с ${point.arrival.address}</a>
                </td>


              </tr>
            </c:forEach>
          </c:if>

          <tr>
            <td colspan="6"><input id="submit_button" type="submit" value="<spring:message text="Изменить"/>"/></td>
          </tr>




          <tr>
            <td colspan="6">
              <a  id="save_button" href="<c:url value="/addtheroute/${day.id}"/>">Добавить маршрут</a>
            </td>
          </tr>

        </table>
  </form:form>
</div>
</body>
</html>




<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>--%>
<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>
<%--<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ page session="false" %>--%>


<%--<html>--%>
  <%--<head>--%>
    <%--<title>Редактировать день</title>--%>
    <%--<style>--%>
      <%--<%@include file="../../resources/style.css" %>--%>
    <%--</style>--%>
  <%--</head>--%>

  <%--<body class="background_blue">--%>

  <%--<header>--%>
    <%--<div class="container">--%>
      <%--<div class="logo">Редактировать день</div>--%>
      <%--<nav>--%>
        <%--<ul>--%>
          <%--<li><a class="no_decor" href="<c:url value="/welcome"/>"><div class="big button_style">В главное меню</div></a></li>--%>
          <%--<li><a target="_blank" class="no_decor" href="<c:url value="/info"/>"><div class="big button_style">О программе</div></a></li>--%>
          <%--<li><a class="no_decor" href="<c:url value="/login?logout"/>"><div class="big button_style">Выйти</div></a></li>--%>
        <%--</ul>--%>
      <%--</nav>--%>
    <%--</div>--%>
  <%--</header>--%>


    <%--<div class="top">--%>
    <%--&lt;%&ndash;<div class="h">Редактировать день</div>&ndash;%&gt;--%>
    <%--<c:url var="editNewDayAction" value="/addNewDay/${report.id}/saveOrUpdateDay"/>--%>
    <%--<form:form action="${editNewDayAction}" commandName="day">--%>
      <%--<table class="edit_form">--%>
        <%--<form:hidden path="id"/>--%>

        <%--<tr>--%>
          <%--<td ><form:label path="date"><spring:message text="Дата"/></form:label></td>--%>
          <%--<td><form:input path="date"/></td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
          <%--<td><form:label path="summ"><spring:message text="Сумма заправки"/></form:label></td>--%>
          <%--<td><form:input type="number" step="0.01" path="summ"/></td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
          <%--<td><form:label path="costByLiter"><spring:message text="Цена за литр"/></form:label></td>--%>
          <%--<td><form:input type="number" step="0.01" path="costByLiter"/></td>--%>
        <%--</tr>--%>

        <%----%>
        <%--<tr>--%>

            <%--<table>--%>
                <%--<c:if test="${!empty day.points}">--%>
              <%--<tr>--%>
                <%--<th class="long_table_head">Пункт отправления</th>--%>
                <%--<th class="long_table_head">Пункт прибытия</th>--%>

                <%--<c:if test="${report.user.accountancyType eq 'зональная'}">--%>
                  <%--<th class="long_table_head">Зональная стоимость</th>--%>
                <%--</c:if>--%>


                <%--<th class="long_table_head">Фактический адрес</th>--%>
                <%--<th class="long_table_head">Проезд по ЗСД</th>--%>
                <%--<th class="long_table_head">Удалить</th>--%>
                <%--<th class="long_table_head">Изменить</th>--%>
              <%--</tr>--%>

              <%--<c:forEach items="${day.points}" var="point">--%>
                <%--<tr>--%>

                  <%--<td class="${point.changed eq true ? "big_table_td changed": point.needChange eq true ? "big_table_td needChange" : "big_table_td"}">${point.departure.address}</td>--%>
                  <%--<td class="${point.changed eq true ? "big_table_td changed": point.needChange eq true ? "big_table_td needChange" : "big_table_td"}">${point.arrival.address}</td>--%>

                  <%--<c:if test="${report.user.accountancyType eq 'зональная'}">--%>
                    <%--<td class="${point.changed eq true ? "big_table_td changed": point.needChange eq true ? "big_table_td needChange" : "big_table_td"}">${point.costByZone}</td>--%>
                  <%--</c:if>--%>

                  <%--<td class="${point.changed eq true ? "big_table_td changed": point.needChange eq true ? "big_table_td needChange" : "big_table_td"}">${point.factAdress}</td>--%>

                  <%--<td class="${point.changed eq true ? "big_table_td changed": point.needChange eq true ? "big_table_td needChange" : "big_table_td"}">--%>
                    <%--<c:if test="${point.zsd eq true}">Да</c:if>--%>
                  <%--</td>--%>

                  <%--<td  class="big_table_td_edit">--%>
                    <%--<a class="no_decor" href="<c:url value="/editDay/${day.id}/deletePoint/${point.id}"/>"><div class="button_style">Удалить</div></a>--%>
                  <%--</td>--%>

                  <%--<td  class="big_table_td_edit">--%>
                    <%--<a class="no_decor" href="<c:url value="/addtheroute/${day.id}/editPoint/${point.id}"/>"><div class="button_style">Изменить</div></a>--%>

                  <%--</td>--%>
                <%--</tr>--%>
              <%--</c:forEach>--%>
                <%--</c:if>--%>

                <%--<tr>--%>
                    <%--<td colspan="6"><input type="submit" class="str big button_style" value="<spring:message text="Изменить"/>"/></td>--%>
                <%--</tr>--%>

                <%--<tr>--%>
                    <%--<td colspan="6">--%>
                        <%--<a class="no_decor" href="<c:url value="/addtheroute/${day.id}"/>"><div class="str big button_style">Добавить маршрут</div></a>--%>
                    <%--</td>--%>

                <%--</tr>--%>

            <%--</table>--%>

        <%--</tr>--%>



      <%--</table>--%>




    <%--</form:form>--%>
    <%--</div>--%>
  <%--</body>--%>
<%--</html>--%>
