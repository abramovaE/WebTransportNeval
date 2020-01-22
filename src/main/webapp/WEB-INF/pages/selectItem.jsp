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




<div class="top">

  <c:if test="${text != null}">
    <script type="text/javascript">alert('${text}');</script>
  </c:if>


  <c:if test="${!empty list}">
    <div class="vertical_list">
      <c:forEach items="${list}" var="item">
        <c:choose>
          <c:when test="${user ne null}">
            <div><a href="<c:url value="${urlValue}${item.id}"/>">${item.shortFullName}</a></div>
          </c:when>

          <c:when test="${auto ne null}">
            <div><a href="<c:url value="${urlValue}${item.id}"/>">${item.brand}</a></div>
          </c:when>

          <c:when test="${menuType eq 'menuOfReports'}">
            <div><a href="<c:url value="${map.get(item)}"/>">${item}</a></div>
          </c:when>

          <c:otherwise>
            <div><a href="<c:url value="${urlValue}${item}"/>">${item}</a></div>
          </c:otherwise>
        </c:choose>


      </c:forEach>
    </div>
  </c:if>
</div>
</body>
</html>
