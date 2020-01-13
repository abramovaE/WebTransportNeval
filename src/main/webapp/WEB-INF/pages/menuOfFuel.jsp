<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>




<html>
<head>
  <title>Выберите пользователя</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body>

<header>
  <div class="container">
    <div>Выберите пользователя</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>


<div class="top">

  <%--<c:if test="${text != null}">--%>
  <%--<script type="text/javascript">alert('${text}');</script>--%>
  <%--</c:if>--%>
  <%----%>

  <c:if test="${!empty users}">
    <div class="vertical_list">
      <div><a href="<c:url value="/graphicsOfUsersFuel/${year}"/>">Сводный отчет за ${year} год</a> </div>
      <c:forEach items="${users}" var="user">
        <div><a href="<c:url value="/graphicsOfUsersFuel/${year}/${user.id}"/>">${user.shortFullName}</a></div>
      </c:forEach>
    </div>
  </c:if>
</div>
</body>
</html>
