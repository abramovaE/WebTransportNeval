<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>




<html>
<head>
  <title>${title}</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body>



<header>
  <div class="container">
    <div>${title}</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>

<c:if test="${text != null}">
  <script type="text/javascript">alert('${text}');</script>
</c:if>





<div class="top">
  <c:url var="saveAutoAction" value="/saveAuto"/>
  <form:form action="${saveAutoAction}" commandName="auto">
    <form:hidden path="id"/>
    <form:hidden path="user.id"/>
    <form:hidden path="stsFileName"/>
    <form:hidden path="osagoFileName"/>
    <form:hidden path="osagoDate"/>

    <table class="create_table" id="edit_form">
      <tr>
        <td><form:label path="brand"><spring:message text="Марка"/></form:label></td>
        <td><form:input path="brand"/></td>
      </tr>

      <tr>
        <td><form:label path="number"><spring:message text="Номер"/></form:label></td>
        <td><form:input path="number"/></td>
      </tr>

      <tr>
        <td><form:label path="year"><spring:message text="Год выпуска"/></form:label></td>
        <td><form:input type="number" path="year"/></td>
      </tr>

      <tr>
        <td><form:label path="bodyType"><spring:message text="Тип кузова"/></form:label></td>
        <td><form:select path="bodyType">
          <c:forEach items="${bodyTypes}" var="bodyType">
            <form:option value="${bodyType.name}"/>
          </c:forEach>
        </form:select></td>
      </tr>

      <tr>
        <td><form:label path="engineVolume"><spring:message text="Объем двигателя"/></form:label></td>
        <td><form:input type="number" step="0.01" path="engineVolume"/></td>
      </tr>

      <tr>
        <td><form:label path="enginePower"><spring:message text="Мощность двигателя (л.с.)"/></form:label></td>
        <td><form:input path="enginePower"/></td>
      </tr>

      <tr>
        <td><form:label path="transmission"><spring:message text="Тип коробки (авто/мех)"/></form:label></td>
        <td>
          <form:select path="transmission">
            <c:forEach items="${transmissionTypes}" var="transmissionType">
              <form:option value="${transmissionType.name}"/>
            </c:forEach>
          </form:select>
        </td>
      </tr>

      <tr>
        <td><form:label path="fuelType"><spring:message text="Тип топлива"/></form:label></td>
        <td> <form:select path="fuelType">
          <c:forEach items="${fuelTypes}" var="fuelType">
            <form:option value="${fuelType.name}"/>
          </c:forEach>
        </form:select></td>
      </tr>

      <tr>
        <td><form:label path="climateMachine"><spring:message text="Тип климатической установки"/></form:label></td>
        <td> <form:select path="climateMachine">
          <c:forEach items="${climateMachineTypes}" var="climateMachineType">
            <form:option value="${climateMachineType.name}"/>
          </c:forEach>
        </form:select></td>
      </tr>





      <tr>
        <td><form:label path="linkNorm"><spring:message text="Норматив смешанного расхода"/></form:label></td>

        <c:choose>
          <c:when test="${isAdmin}">
            <td>
              <form:input type="number" step="0.01" path="linkNorm"/>
            </td>
          </c:when>
          <c:otherwise>
            <td>
              <form:input readonly="true" path="linkNorm" cssClass="readonly_style"/>
            </td>
          </c:otherwise>
        </c:choose>
      </tr>


      <tr>
        <td><form:label path="link"><spring:message text="Ссылка на источник (auto.ru)"/></form:label></td>

        <c:choose>
          <c:when test="${isAdmin}">
            <td>
              <form:input path="link"/>
            </td>
          </c:when>
          <c:otherwise>
            <td>
              <form:input readonly="true" path="link" cssClass="readonly_style"/>
            </td>
          </c:otherwise>
        </c:choose>
      </tr>



      <tr>
        <td><form:label path="summerNorm"><spring:message text="Норма расхода (лето)"/></form:label></td>
        <td><form:input readonly="true" path="summerNorm" cssClass="readonly_style"/></td>
      </tr>

      <tr>
        <td><form:label path="winterNorm"><spring:message text="Норма расхода (зима)"/></form:label></td>
        <td><form:input readonly="true" path="winterNorm" cssClass="readonly_style"/></td>
      </tr>

      <tr>
        <td><form:label path="stsNumber"><spring:message text="Номер СТС"/></form:label></td>
        <td><form:input path="stsNumber"/></td>
      </tr>


      <c:if test="${auto.id != 0}">
        <tr>
          <td><form:label path="stsFileName"><spring:message text="СТС"/></form:label></td>
          <td>
            <a id="load_button" href="<c:url value="/load/sts/${auto.id}"/>">Загрузить</a>
            <a id="look_button" href="<c:url value="/look/sts/${auto.id}"/>" target="_blank">Посмотреть</a>
          </td>
        </tr>
        <tr>
          <td><form:label path="osagoFileName"><spring:message text="ОСАГО"/></form:label></td>
          <td>
            <a id="load_button" href="<c:url value="/load/osago/${auto.id}"/>">Загрузить</a>
            <a id="look_button" href="<c:url value="/look/osago/${auto.id}"/>" target="_blank">Посмотреть</a>
          </td>
        </tr>
      </c:if>


      <c:if test="${currentAuto}">
        <tr>
          <td><form:label path="currentAuto"><spring:message text="Использовать как текущий автомобиль"/> </form:label></td>
          <td><form:checkbox path="currentAuto"/></td>
        </tr>
      </c:if>



      <tr>
        <td></td>
        <td><input id="submit_button" type="submit" value="<spring:message text="Сохранить"/>"/></td>
      </tr>
    </table>
  </form:form>
</div>
</body>
</html>
