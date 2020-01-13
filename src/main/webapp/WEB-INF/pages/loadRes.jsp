<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
  <title>Welcome</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>


<body>


<header>
  <div class="container">
    <div>О программе</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>

<div class="top info">

  <c:forEach items="${res}" var="trip">
    ${trip.toString()}
    </br>
  </c:forEach>


</div>

</body>
</html>
