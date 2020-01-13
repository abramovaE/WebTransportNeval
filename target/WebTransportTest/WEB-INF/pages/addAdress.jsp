<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>




<html>
<head>
  <title>Добавить новый адрес</title>
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
    <c:url var="registrationAction" value="/addAdress"/>
    <form:form action="${registrationAction}" commandName="adressCoordinates">
      <table>
        <tr>
          <td><form:label path="Adress" cssClass="form_label"><spring:message text="Адрес"/></form:label></td>
          <td><form:input path="Adress" cssClass="form_input"/></td>
        </tr>

        <tr>
          <td><form:label path="Coordinates" cssClass="form_label"><spring:message text="Координаты"/></form:label></td>
          <td><form:input path="Coordinates" cssClass="form_input"/></td>
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
