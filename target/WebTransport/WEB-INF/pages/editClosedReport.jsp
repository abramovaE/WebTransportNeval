<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Редактирование закрытого отчета пользователя ${report.user.shortFullName} за ${report.period}</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body>
<header>
  <div class="container">
    <div>Редактирование закрытого отчета пользователя ${report.user.shortFullName} за ${report.period}</div>
      <c:forEach items="${topMenu}" var="btn">
          <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
      </c:forEach>
  </div>
</header>

<div class="top">

    <div class="mini_logo">Данные пользователя ${report.user.shortFullName} из отчета за ${report.period}</div>
    <c:url var="editClosedReportAction" value="/editClosedReport/${report.id}/save"/>
    <form:form action="${editClosedReportAction}" commandName="blockedReportData">



      <table class="create_table" id="edit_closedReport_form">
        <form:hidden path="id"/>
        <form:hidden path="login"/>

          <tr>
              <td><form:label path="name"><spring:message text="Имя"/></form:label></td>
              <td><form:input path="name"/></td>
              <td></td>
              <td></td>
              <td><form:label path="brand"><spring:message text="Марка автомобиля"/></form:label></td>
              <td><form:input path="brand" readonly="true" cssClass="readonly_style"/></td>
          </tr>



          <tr>
            <td><form:label path="patronymic"><spring:message text="Отчество"/></form:label></td>
            <td><form:input path="patronymic"/></td>
              <td></td>
              <td></td>
              <td><form:label path="number"><spring:message text="Номер автомобиля"/></form:label></td>
              <td><form:input path="number" readonly="true" cssClass="readonly_style"/></td>
          </tr>


          <tr>
            <td><form:label path="surname"><spring:message text="Фамилия"/></form:label></td>
            <td><form:input path="surname"/></td>
              <td></td>
              <td></td>
              <td><form:label path="autoYear"><spring:message text="Год выпуска авто"/></form:label></td>
              <td><form:input path="autoYear" readonly="true" cssClass="readonly_style"/></td>
          </tr>


          <tr>
            <td><form:label path="post"><spring:message text="Должность"/></form:label></td>
            <td><form:input path="post"/></td>
              <td></td>
              <td></td>
              <td><form:label path="bodyType"><spring:message text="Тип кузова"/></form:label></td>
              <td><form:input path="bodyType" readonly="true" cssClass="readonly_style"/></td>
          </tr>



          <tr>
              <td><form:label path="transponder"><spring:message text="Номер транспондера"/></form:label></td>
              <td><form:input path="transponder" /></td>
              <td></td>
              <td></td>
              <td><form:label path="engineVolume"><spring:message text="Объем двигателя"/></form:label></td>
              <td><form:input path="engineVolume" readonly="true" cssClass="readonly_style"/></td>
          </tr>


          <tr>
              <td><form:label path="accountancyType"><spring:message text="Тип отчетности"/></form:label></td>
              <td><form:input path="accountancyType" readonly="true" cssClass="readonly_style"/></td>
              <td></td>
              <td></td>
              <td><form:label path="enginePower"><spring:message text="Мощность двигателя"/></form:label></td>
              <td><form:input path="enginePower" readonly="true" cssClass="readonly_style"/></td>
          </tr>


          <tr>
              <td><form:label path="amortizacia"><spring:message text="Коэффициент амортизации"/></form:label></td>
              <td><form:input type="number" step="0.1" path="amortizacia"/></td>
              <td></td>
              <td></td>
              <td><form:label path="transmission"><spring:message text="Тип трансмиссии"/></form:label></td>
              <td><form:input path="transmission" readonly="true" cssClass="readonly_style"/></td>
          </tr>


          <tr>
              <td><form:label path="period"><spring:message text="Период"/></form:label></td>
              <td><form:input path="period" readonly="true" cssClass="readonly_style"/></td>
              <td></td>
              <td></td>
              <td><form:label path="fuelType"><spring:message text="Тип топлива"/></form:label></td>
              <td><form:input path="fuelType" readonly="true" cssClass="readonly_style"/></td>
          </tr>


          <tr>
              <td><form:label path="sumKmDistance"><spring:message text="Суммарное расстояние"/></form:label></td>
              <td><form:input path="sumKmDistance" readonly="true" cssClass="readonly_style"/></td>
              <td></td>
              <td></td>
              <td><form:label path="climateMachine"><spring:message text="Тип климатической установки"/></form:label></td>
              <td><form:input path="climateMachine" readonly="true" cssClass="readonly_style"/></td>
          </tr>


          <tr>
              <td><form:label path="sumSumm"><spring:message text="Сумма заправок"/></form:label></td>
              <td><form:input path="sumSumm" readonly="true" cssClass="readonly_style"/></td>
              <td></td>
              <td></td>
              <td><form:label path="stsFileName"><spring:message text="СТС"/></form:label></td>
              <td><form:input path="stsFileName" readonly="true" cssClass="readonly_style"/></td>
          </tr>




          <tr>
              <td><form:label path="sumZone"><spring:message text="Сумма зональная"/></form:label></td>
              <td><form:input path="sumZone" readonly="true" cssClass="readonly_style"/></td>
              <td></td>
              <td></td>
              <td><form:label path="osagoFileName"><spring:message text="ОСАГО"/></form:label></td>
              <td><form:input path="osagoFileName" readonly="true" cssClass="readonly_style"/></td>
          </tr>

          <tr>
              <td><form:label path="mobileWeeks"><spring:message text="Компенсация мобильной связи (количество недель)"/></form:label></td>
              <td><form:input type="number" step="1" path="mobileWeeks"/></td>
              <td></td>
              <td></td>
              <td><form:label path="link"><spring:message text="Ссылка на источник (auto.ru)"/></form:label></td>
              <td><form:input path="link" readonly="true" cssClass="readonly_style"/></td>
          </tr>



          <tr>
              <td><form:label path="mobile"><spring:message text="Компенсация мобильной связи (сумма)"/></form:label></td>
              <td><form:input type="number" step="1" path="mobile"/></td>
              <td></td>
              <td></td>
              <td><form:label path="linkNorm"><spring:message text="Норматив смешанного расхода"/></form:label></td>
              <td><form:input path="linkNorm" readonly="true" cssClass="readonly_style"/></td>
          </tr>


          <tr>
              <td><form:label path="mediumFuelPrice"><spring:message text="Средняя цена топлива"/></form:label></td>
              <td><form:input path="mediumFuelPrice" readonly="true" cssClass="readonly_style"/></td>
              <td></td>
              <td></td>
              <td><form:label path="summerNorm"><spring:message text="Расход летом"/></form:label></td>
              <td><form:input path="summerNorm" readonly="true" cssClass="readonly_style"/></td>
          </tr>



          <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td><form:label path="winterNorm"><spring:message text="Расход зимой"/></form:label></td>
              <td><form:input path="winterNorm" readonly="true" cssClass="readonly_style"/></td>
          </tr>









          <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>

          </tr>






          <tr>
            <%--<td></td>--%>
            <td colspan="6"><input id="submit_button" type="submit" value="<spring:message text="Изменить"/>"/></td>
          </tr>





      </table>
    </form:form>









  <%--<div>--%>
    <%--<c:if test="${text != null}">--%>
      <%--<script type="text/javascript">alert('${text}');</script>--%>
    <%--</c:if>--%>
  <%--</div>--%>

    <div class="mini_logo">Отчет пользователя ${report.user.shortFullName} о транспортных расходах за ${report.period}</div>
  <table class="create_table">
    <tr><td>МОЛ (должность, Ф.И.О.): ${blockedReportData.post} ${blockedReportData.surname} ${blockedReportData.name} ${blockedReportData.patronymic}</td></tr>
    <tr><td><label path="period">Период: ${report.period}</label></td></tr>
  </table>

  <c:if test="${!empty allDays}">
    <table class="small_table">
      <tr>
        <c:if test="${canDelete}">
          <th></th>
        </c:if>
        <th>Дата</th>
        <th>Сумма</th>
        <th>Стоимость за литр</th>
        <th>Пункт отправления</th>
        <th>Пункт прибытия</th>
        <c:if test="${blockedReportData.accountancyType eq 'зональная'}">
          <th>Зональная стоимость</th>
        </c:if>
        <th>Фактический адрес</th>
        <th>Проезд по ЗСД</th>
          <%--<c:if test="${isAdmin eq true || isManager eq true}">--%>
        <th>Расстояние</th>
          <%--</c:if>--%>
        <c:if test="${isAdmin eq true || isManager eq true}">
          <th></th>
        </c:if>
      </tr>

      <c:forEach items="${allDays}" var="day">
        <tr>
        <%--<c:if test="${canDelete}">--%>
          <%--<td><a href="<c:url value="/deleteDay/${day.id}"/>">Удалить</a></td>--%>
        <%--</c:if>--%>
        <td><a href="<c:url value="/editDay/${day.id}"/>">${day.date}</a></td>
        <td>${day.summ}</td>
        <td>${day.costByLiter}</td>

        <c:if test="${!empty day.points}">
          <c:forEach items="${day.points}" var="point">
            <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.departure.address}</td>
            <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.arrival.address}</td>
            <c:if test="${blockedReportData.accountancyType eq 'зональная'}">
              <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.costByZone}</td>
            </c:if>
            <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.factAdress}</td>
            <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">
              <c:if test="${point.zsd eq true}">Да</c:if>
            </td>
            <%--<c:if test="${isAdmin eq true || isManager eq true}">--%>
            <td id="${point.changed eq true ? "changed": point.needChange eq true ? "needChange" : ""}">${point.distance/1000}км</td>
            <%--</c:if>--%>

            <%--<c:if test="${isAdmin eq true || isManager eq true}">--%>
              <%--<c:choose>--%>
                <%--<c:when test="${point.changed eq true}">--%>
                  <%--<td><a href="<c:url value="/setIsNeedChange/${point.id}"/>">Утвердить изменения</a></td>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                  <%--<td><a href="<c:url value="/setIsNeedChange/${point.id}"/>">Исправить</a></td>--%>
                <%--</c:otherwise>--%>
              <%--</c:choose>--%>
            <%--</c:if>--%>

            <tr/>
            <td id="empty"></td>
            <td id="empty"></td>
            <td id="empty"></td>
            <c:if test="${canDelete}">
              <td id="empty"></td>
            </c:if>
          </c:forEach>
        </c:if>
        </tr>
      </c:forEach>
    </table>
  </c:if>

  <c:if test="${canDelete}">
    <table class="create_table">
      <c:url var="addTheRouteAction" value="/addNewDay/${report.id}/save"/>
      <form:form action="${addTheRouteAction}" commandName="day">
        <tr>
          <th>Дата</th>
          <th>Сумма</th>
          <th>Стоимость литра</th>
        </tr>
        <tr>
          <td><form:input type="number" min = "1" max = "31" path="date"/></td>
          <td><form:input type="number" step="0.01" path="summ"/></td>
          <td><form:input type="number" step="0.01" path="costByLiter"/></td>
          <td><input id="submit_button" type="submit" value="<spring:message text="Добавить маршрут"/>" /></td>
        </tr>
        <tr>
          <td colspan="4">
            <a id="save_button"  href="<c:url value="/welcome"/>">Сохранить и вернуться в меню</a>
          </td>
        </tr>
        <tr>
          <td colspan="4">
            <a id="save_button" href="<c:url value="/printReport/${report.id}"/>">Печать</a>
          </td>
        </tr>
      </form:form>
    </table>
  </c:if>

  <table class="create_table">
    <tr><td>Сумма по чекам: ${report.sumSumm} руб</td></tr>
    <tr><td>Компенсация сотовой связи: ${report.totalMobile} руб</td></tr>
    <tr><td>Цена за километр: ${report.priceForOneKm} руб/км</td></tr>
    <tr><td>Расстояние всего: ${report.sumKmDistance} км</td></tr>
    <tr><td>Проезд в нуждах компании: ${report.company} руб</td></tr>
    <c:if test="${blockedReportData.accountancyType eq 'зональная'}">
      <tr><td>Сумма зональной стоимости: ${report.sumZone} руб</td></tr>
      <tr><td>Итого: ${report.sumZone + report.totalMobile} руб</td></tr>
    </c:if>

    <c:if test="${blockedReportData.accountancyType eq 'одометрическая'}">
      <tr><td>Компенсация амортизации: ${report.totalAmort} руб</td></tr>
      <tr><td>Итого: ${report.totalOdom} руб</td></tr>
    </c:if>

    <c:if test="${blockedReportData.accountancyType eq 'чековая'}">
      <tr><td>Итого: ${report.totalCheck} руб</td></tr>
    </c:if>
    <tr><td>Подпись: ${user.shortFullName} _________________________ </td></tr>
    <tr><td>Согласовано: </td></tr>
  </table>
</div>
</body>
</html>