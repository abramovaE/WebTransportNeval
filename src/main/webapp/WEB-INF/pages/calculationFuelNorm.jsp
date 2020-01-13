<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
  <title>Расчет расхода топлива автомобилем сотрудника ${user.shortFullName}</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>


<body>

<header>
  <div class="container">
    <div>Расчет расхода топлива автомобилем сотрудника ${user.shortFullName}</div>
      <c:forEach items="${topMenu}" var="btn">
          <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
      </c:forEach>
  </div>
</header>




<div class="top">

  <c:if test="${text != null}">
    <script type="text/javascript">alert('${text}');</script>
  </c:if>

      <table class="small_table" id="calculation">
          <tr>
              <th>Марка автомобиля</th>
              <%--<th>Модель</th>--%>
              <th>Год выпуска</th>
              <th>Объем двигателя (см3)</th>
              <th>Трансмиссия</th>
              <th>Норма расхода (тех. карта)</th>
              <th>Итоговая норма расхода (лето)</th>
              <th>Итоговая норма расхода (зима)</th>
          </tr>

          <tr>
              <td>${user.currentAuto.brand}</td>
              <%--<td>${user.currentAuto.model}</td>--%>
              <td>${user.currentAuto.year}</td>
              <td>${user.currentAuto.engineVolume}</td>
              <td>${user.currentAuto.transmission}</td>
              <td>${user.currentAuto.linkNorm}</td>
              <td>${user.currentAuto.summerNorm}</td>
              <td>${user.currentAuto.winterNorm}</td>
          </tr>
      </table>


      <%--<div class="str">Для расчета расхода топлива данным авто, применяются следующие коэффециенты (в процентах):</div>--%>

      <table class="create_table">
          <tr>
              <td>Для расчета расхода топлива данным авто, применяются следующие коэффециенты (в процентах):</td>
          </tr>
      </table>

      <div></div>

      <table class="small_table">

      </table>





      <%--<c:url var="saveTheAutoLinkAction" value="/saveTheAutoLink/${user.id}"/>--%>
      <%--<form:form action="${saveTheAutoLinkAction}" commandName="user">--%>

          <table class="small_table">

              <tr>
                  <th>Наименование коэффициента</th>
                  <th>Величина коэффициента</th>
                  <th>Примечание</th>
              </tr>

              <tr>

                  <c:if test="${!empty koefficients}">
                  <c:forEach items="${koefficients}" var="koeff">
              <tr>
                  <td>${koeff.name}</td>
                  <td>${koeff.value}</td>
                  <td>${koeff.primechanie}</td>
              </tr>
              </c:forEach>
              </c:if>
              </tr>




              <tr>
                  <td>Норматив смешанного расхода топлива с сайта: </td>
                  <td>${user.currentAuto.linkNorm}</td>


                  <%--<td colspan="2">Ссылка на источник с техничесими характеристиками определенного авто (auto.ru)</td>--%>
                  <td><a href="${user.currentAuto.link}" target="_blank">Посмотреть источник</a></td>
              </tr>

              <tr>
                  <td colspan="3">
                      <a href="<c:url value="/usersManaging/editUser/${user.id}"/>">Изменить данные пользователя</a>
                  </td>

              </tr>

              <%--<tr>--%>
                  <%--<td colspan="3"><input id="submit_button" type="submit" value="<spring:message text="Сохранить"/>"/></td>--%>
              <%--</tr>--%>

          </table>
      <%--</form:form>--%>



</div>
</body>
</html>
