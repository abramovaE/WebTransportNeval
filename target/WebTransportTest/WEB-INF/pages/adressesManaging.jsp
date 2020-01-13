<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
  <title>adressessManaging</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>
</head>

<body class="background_blue">
<div class="top">
  <div>


      <c:if test="${message != null}">
        <div> Сообщение: ${message}</div>
      </c:if>

      <c:if test="${error !=null}">
        <div> Ошибка: ${error}</div>
      </c:if>


      <c:if test="${!empty adressesList}">
        <table>
          <tr>
            <th class="long_table_head">Адрес</th>
            <th class="long_table_head">Координаты</th>
              <th></th>
          </tr>

          <c:forEach items="${adressesList}" var="adressCoordinates">
            <tr class="big_table_tr">
              <%--<td><form:hidden path="id"/>${adressCoordiates.id}</td>--%>
              <td class="big_table_td">${adressCoordinates.adress}</td>
              <td class="big_table_td">${adressCoordinates.coordinates}</td>
                  <td><a class="no_decor" href="<c:url value="/addNewDay/${report.id}/deleteNewDay/${day.id}"/>"></a></td>

                  <td class="big_table_td"><a class="no_decor" href="<c:url value="/editAdress/${adressCoordinates.id}"/>"><div class="edit_button">Редактировать</div></a></td>
              <%--<td class="big_table_td"><a class="no_decor" href="/usersManaging/delete/${user.id}"><div class="edit_button">Удалить</div></a></td>--%>
              </tr>
          </c:forEach>
        </table>
      </c:if>

      <div class="menu">
        <a class="no_decor" href="<c:url value="/addAdress"/>"><div class="login_button">Добавить новый адрес</div></a>
          <a class="no_decor" href="<c:url value="/welcome"/>"><div class="login_button">Назад</div></a>
      </div>

  </div>
</div>
</body>
</html>
