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
  <c:url var="saveAdressAction" value="/saveAdress"/>
  <form:form action="${saveAdressAction}" commandName="address">


    <table class="create_table" id="edit_form">

      <form:hidden path="id"/>

      <tr>
        <td><form:label path="address"><spring:message text="Адрес"/></form:label></td>
        <td><form:input path="address" readonly="false"/></td>
      </tr>
      <tr>
        <td><form:label path="coordinates"><spring:message text="Координаты"/></form:label></td>
        <td><form:input path="coordinates"/></td>
      </tr>
      <tr>
        <td><form:label path="numberOfZone"><spring:message text="Номер зоны"/></form:label></td>
        <td><form:input path="numberOfZone"/></td>
      </tr>


        <c:choose>
            <c:when test="${isAdmin}">
                <tr>
                    <td><form:label path="usedForAutoReports"><spring:message text="Использовать для авто отчета"/></form:label></td>
                    <td><form:checkbox path="usedForAutoReports"/></td>
                </tr>
            </c:when>
            <c:otherwise>
                <form:hidden path="usedForAutoReports"/>
            </c:otherwise>
        </c:choose>

        <tr>
        <td></td>
        <td><input id="submit_button" type="submit" value="<spring:message text="Сохранить"/>"/></td>
      </tr>
    </table>
  </form:form>
</div>
</body>
</html>
