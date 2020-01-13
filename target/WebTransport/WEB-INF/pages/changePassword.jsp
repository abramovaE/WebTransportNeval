<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Изменить пароль</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body>

<header>
  <div class="container">
    <div>Изменить пароль</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>


<div class="top">

  <c:if test="${text !=null}">
    <script type="text/javascript">alert('${text}');</script>
  </c:if>

      <c:url var="changePasswordAction" value="/changePassword/save"/>
      <form:form action="${changePasswordAction}" commandName="user">
        <table class="create_table" id="edit_form">

          <form:hidden path="id"/>
          <form:hidden path="login"/>
          <form:hidden path="surname"/>
          <form:hidden path="name"/>
          <form:hidden path="patronymic"/>
          <form:hidden path="post"/>
          <%--<form:hidden path="brand"/>--%>
          <%--<form:hidden path="model"/>--%>
          <%--<form:hidden path="autoNumber"/>--%>
          <%--<form:hidden path="summerNorm"/>--%>
          <%--<form:hidden path="winterNorm"/>--%>
          <%--<form:hidden path="year"/>--%>
          <%--<form:hidden path="engineVolume"/>--%>
          <%--<form:hidden path="transmission"/>--%>
          <form:hidden path="transponder"/>
          <form:hidden path="eMail"/>
          <form:hidden path="accountancyType"/>
          <form:hidden path="amortizacia"/>
          <%--<form:hidden path="bodyType"/>--%>
          <%--<form:hidden path="climateMachine"/>--%>
          <%--<form:hidden path="enginePower"/>--%>
          <%--<form:hidden path="stsFileName"/>--%>
          <%--<form:hidden path="fuelType"/>--%>
          <%--<form:hidden path="link"/>--%>
          <%--<form:hidden path="linkNorm"/>--%>
          <form:hidden path="buhgalteria"/>
          <form:hidden path="blocked"/>

          <c:if test="${notDriver eq true}">
            <form:hidden path="currentAuto.id"/>
          </c:if>




          <tr>
            <td><form:label path="password"><spring:message text="Новый пароль"/></form:label></td>
            <td><form:password path="password"/></td>
          </tr>

          <tr>
            <td><form:label path="confirmPassword"><spring:message text="Подтвердите новый пароль"/></form:label></td>
            <td><form:password path="confirmPassword"/></td>
          </tr>

          <tr>
            <td></td>
            <td><input id="submit_button" type="submit" value="<spring:message text="Изменить"/>" /></td>
          </tr>

        </table>
      </form:form>
</div>

</body>
</html>
