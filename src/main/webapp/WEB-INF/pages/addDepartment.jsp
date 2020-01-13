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

  <script type="text/javascript">

    var expanded = false;

    function showCheckboxes() {
      var checkboxes = document.getElementById("checkUsers");
      if (!expanded) {
        checkboxes.style.display = "block";
        expanded = true;
      } else {
        checkboxes.style.display = "none";
        expanded = false;
      }
    }
  </script>

</head>

<body class="background_blue">

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
  <%--<div>${dep.id}</div>--%>
  <c:url var="saveDepAction" value="/addDepartment/save"/>

    <div  style="color: red"> Множественный выбор сотрудников с клавишами ctrl или shift</div>
  <form:form action="${saveDepAction}" commandName="dep">
    <table class="create_table" id="edit_form">

        <form:hidden path="id"/>
        <%--<form:hidden path="users"/>--%>

      <tr>
        <td><form:label path="name"><spring:message text="Название"/></form:label></td>
        <td><form:input path="name"/></td>
      </tr>

      <tr>
        <td><form:label path="prim"><spring:message text="Примечание"/></form:label></td>
        <td><form:input path="prim"/></td>
      </tr>


        <tr>
            <td><form:label path="link"><spring:message text="Префикс отдела"/></form:label></td>
            <td><form:input path="link"/></td>
        </tr>

      <tr>
          <td><form:label path="usersId"><spring:message text="Добавить сотрудников"/></form:label></td>
        <td>
          <%--<div>Множественный выбор с ctrl</div>--%>
          <form:select path="usersId" multiple="true">
            <c:forEach items="${usersList}" var="user">
              <form:option label="${user.shortFullName}" value="${user.id}"/>
            </c:forEach>
          </form:select>
        </td>

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
