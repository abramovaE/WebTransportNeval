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

  <c:url var="addOrEditZoneAction" value="/saveZone"/>
  <form:form action="${addOrEditZoneAction}" commandName="costByZone">
    <table class="create_table" id="edit_form">
      <form:hidden path="id"/>
      <tr>
        <td><form:label path="numberZone"><spring:message text="Номер зоны"/></form:label></td>
        <td><form:input path="numberZone"/></td>
      </tr>

      <tr>
        <td><form:label path="costByZone"><spring:message text="Стоимость зоны"/></form:label></td>
        <td><form:input path="costByZone"/></td>
      </tr>

      <tr>
        <td></td>
        <td><input id="submit_button" type="submit" value="<spring:message text="Сохранить"/>" /></td>
      </tr>
    </table>
  </form:form>
</div>
</body>
</html>
