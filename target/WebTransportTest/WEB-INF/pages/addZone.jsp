<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>




<html>
<head>
  <title>Добавить зону</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body class="background_blue">

<c:if test="${errorMessage !=null}">
  <tr>
    <td></td>
    <td>${errorMessage}</td>
  </tr>
</c:if>

<div class="center">
  <div class="registration">
    <c:url var="registrationAction" value="/addZone"/>
    <form:form action="${registrationAction}" commandName="costByZone">
      <table>
        <tr>
          <td><form:label path="numberZone" cssClass="form_label"><spring:message text="Номер зоны"/></form:label></td>
          <td><form:input path="numberZone" cssClass="form_input"/></td>
        </tr>

        <tr>
          <td><form:label path="costByZone" cssClass="form_label"><spring:message text="Стоимость зоны"/></form:label></td>
          <td><form:input path="costByZone" cssClass="form_input"/></td>
        </tr>


        <tr>
          <td></td>
          <td><input class="login_button" type="submit" value="<spring:message text="Добавить"/>" /></td>
        </tr>



      </table>
    </form:form>
  </div>
</div>
</body>
</html>
