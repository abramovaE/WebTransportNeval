<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>




<html>
<head>
  <title>Компенсация мобильной связи: ${report.user.shortFullName} за ${report.period}</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body class="background_blue">

<header>
  <div class="container">
    <div>Компенсация мобильной связи: ${report.user.shortFullName} за ${report.period}</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>

<c:if test="${text != null}">
  <script type="text/javascript">alert('${text}');</script>
</c:if>


<div class="top">
  <c:url var="saveMobileAction" value="/mobile/save/${report.id}"/>
  <form:form action="${saveMobileAction}" commandName="report">
    <table class="create_table" id="edit_form">
      <tr>
        <td><form:label path="mobile"><spring:message text="Сумма"/></form:label></td>
        <td><form:input path="mobile"/></td>
      </tr>

      <tr>
        <td></td>
        <td><input id="submit_button" type="submit" value="<spring:message text="Сохранить"/>"/></td>
      </tr>
    </table>
  </form:form>
</div>

</body>
</html>
