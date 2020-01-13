<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Регистрация нового пользователя</title>
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
  <div class="registration _800">
    <c:url var="changePasswordAction" value="/changePassword/save"/>
    <form:form action="${changePasswordAction}" commandName="user">
      <table>


        <tr>
          <td><form:hidden path="id"/></td>
          <td><form:hidden path="username"/></td>
          <td><form:hidden path="surname"/></td>
          <td><form:hidden path="name"/></td>
          <td><form:hidden path="patronymic"/></td>
          <td><form:hidden path="post"/></td>
          <td><form:hidden path="auto"/></td>
          <td><form:hidden path="autoNumber"/></td>
          <td><form:hidden path="summerNorm"/></td>
          <td><form:hidden path="winterNorm"/></td>
          <td><form:hidden path="year"/></td>
          <td><form:hidden path="engineVolume"/></td>
          <td><form:hidden path="transmission"/></td>
          <td><form:hidden path="transponder"/></td>
          <td><form:hidden path="eMail"/></td>
        </tr>



        <tr>
          <td><form:label path="password" cssClass="form_label"><spring:message text="Новый пароль"/></form:label></td>
          <td><form:password path="password" cssClass="form_input"/></td>
        </tr>

        <tr>
          <td><form:label path="confirmPassword" cssClass="form_label"><spring:message text="Подтвердите новый пароль"/></form:label></td>
          <td><form:password path="confirmPassword" cssClass="form_input"/></td>
        </tr>



        <tr>
          <td></td>
          <td><input class="login_button" type="submit" value="<spring:message text="Изменить"/>" /></td>
        </tr>



      </table>
    </form:form>
  </div>
</div>
</body>
</html>
