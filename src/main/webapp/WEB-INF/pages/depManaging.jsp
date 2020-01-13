<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
  <title>Управление отделами</title>
  <style>
    <%@include file="../../resources/style.css" %>
  </style>





</head>

<body>

<header>
  <div class="container">
    <div>Список всех отделов</div>
    <c:forEach items="${topMenu}" var="btn">
      <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
    </c:forEach>
  </div>
</header>

<div class="top">

  <c:if test="${text != null}">
    <script type="text/javascript">alert('${text}');</script>
  </c:if>



    <table class="small_table" id="users">

    <c:if test="${!empty depList}">

        <th>Название отдела</th>
        <th>Примечание</th>
        <th>Сотрудники</th>
        <th></th>



      <c:forEach items="${depList}" var="dep">
        <tr>
          <td class="grey">
           <a href="<c:url value="/editDep/${dep.id}"/>">${dep.name}</a>

          </td>

          <td>${dep.prim}</td>

          <td>
            <c:if test="${! empty dep.users}">
              <c:forEach items="${dep.users}" var="user">
                <div class="inside_td">
                  <div>${user.shortFullName}</div>
                  <div><a href="<c:url value="/delUserFromDep/${user.id}"/>">Удалить из отдела</a></div>
                </div>
              <br>
              </c:forEach>
            </c:if>
          </td>

          <td class="grey">
            <a href="<c:url value="/delDep/${dep.id}"/>">Удалить отдел</a>
          </td>

        </tr>
      </c:forEach>
    </c:if>
      <tr>
        <td colspan="4">
          <a id="save_button" href="<c:url value="/addDepartment"/>">Создать новый отдел</a>
        </td>
      </tr>


    </table>
</div>
</body>
</html>

