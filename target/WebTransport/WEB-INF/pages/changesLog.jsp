<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>


<%@ page pageEncoding="UTF-8"%>
<html>
<head>
  <title>История изменений данных пользователя ${user.shortFullName}</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>

</head>

<body>
<header>
  <div class="container">
    <div>История изменений данных пользователя ${user.shortFullName}</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>

<div class="top">

  <c:if test="${text != null}">
    <script type="text/javascript">alert('${text}');</script>
  </c:if>




  <c:if test="${!empty changesLog}">
    <table class="small_table">
      <tr>
        <th>Измененный параметр</th>
        <th>Старое значение</th>
        <th>Новое значение</th>
        <th>Кто внес изменения</th>
        <th>Время изменения</th>
      </tr>


      <c:forEach items="${changesLog}" var="changedItem">
          <tr>
            <c:if test="${changedItem.subject ne 'password'}">
              <td>${changedItem.subject}</td>
              <td>${changedItem.oldData}</td>
              <td>${changedItem.newData}</td>
              <td>${changedItem.whoChanged.shortFullName}</td>
              <td>${changedItem.date}</td>
            </c:if>

          </tr>
      </c:forEach>


    </table>
  </c:if>

  <%--<c:if test="${empty changesLog}">--%>
    <%--<table class="big_table">--%>



      <%--<tr>--%>
        <%--<td>--%>
          <%--Данные пользователя пока никак не изменялись--%>
        <%--</td>--%>

      <%--</tr>--%>

    <%--</table>--%>
  <%--</c:if>--%>


</div>
</body>
</html>
