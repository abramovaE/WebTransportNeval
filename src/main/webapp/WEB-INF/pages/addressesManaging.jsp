<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
  <title>Управление адресами</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body>

<header>
    <div class="container">
        <div>Управление адресами</div>
        <c:forEach items="${topMenu}" var="btn">
            <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
        </c:forEach>
        <div><a href="<c:url value="/addAdress"/>">Добавить новый адрес</a></div>
    </div>
</header>


<div class="top">


    <c:if test="${text != null}">
          <script type="text/javascript">alert('${text}');</script>
    </c:if>

    <c:if test="${error !=null}">
        <div> Ошибка: ${error}</div>
    </c:if>

    <c:if test="${!empty adressesList}">
              <table class="small_table">
                  <tr>
                      <th>Адрес</th>
                      <th>Координаты</th>
                      <th>Номер зоны</th>

                      <c:if test="${isAdmin eq true}">
                          <th>Использовать для автоматического создания отчетов</th>
                      </c:if>
                      <th></th>

                  </tr>

                  <c:forEach items="${adressesList}" var="adressCoordinates">
                      <tr>
                          <td>${adressCoordinates.address}</td>
                          <td>${adressCoordinates.coordinates}</td>
                          <td>${adressCoordinates.numberOfZone}</td>


                          <c:if test="${isAdmin eq true}">
                              <c:choose>
                                  <c:when test="${adressCoordinates.usedForAutoReports eq false}">
                                      <td>
                                          <a href="<c:url value="/useForAutoReports/${adressCoordinates.id}"/>">+</a></td>
                                      </td>
                                  </c:when>
                                  <c:otherwise>
                                      <td></td>
                                  </c:otherwise>
                              </c:choose>
                          </c:if>
                          <td><a href="<c:url value="/editAdress/${adressCoordinates.id}"/>">Редактировать</a></td>
                      </tr>
                  </c:forEach>
              </table>
          </c:if>

</div>
</body>
</html>
