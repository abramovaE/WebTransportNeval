<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
  <head><meta charset="UTF-8">
    <title>Управление отчетами</title>
    <style>
      <%@include file="../../resources/style.css" %>
    </style>



      <script>

      function yesOrNo()
      {
          var result = confirm("Подтвердите удаление");
          if (result ==true)
          {
              return 1;
          }
          else
          {
              return 0;
          }
      }
      function locationConfirm(id)
      {
          var redirect = yesOrNo();
          if(redirect==1) top.location.href="<c:url value="/deleteReport/"/>"+id;
          else if(redirect==0) top.location.href="<c:url value="/reportsManaging"/>";
          return false;
      }
    </script>


  </head>

  <body>

  <header>
    <div class="container">
        <div>Список отчетов пользователя ${name}</div>
        <c:forEach items="${topMenu}" var="btn">
            <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
        </c:forEach>
        <div><a href="<c:url value="/createAReport"/>">Создать новый отчет</a></div>

        <c:if test="${user.id == 19}">
            <div><a href="<c:url value="/usersManaging/createAutoReport/${user.id}/0/0"/>">Сген. отчет</a></div>
        </c:if>


    </div>
  </header>

    <div class="top">

        <c:if test="${text != null}">
            <script type="text/javascript">alert('${text}');</script>
        </c:if>



        <c:if test="${!empty reportsList}">
        <table class="big_table">
          <tr>
            <th>Период</th>
            <th></th>
            <%--<th>Данные из биллинга</th>--%>

          </tr>
          <c:forEach items="${reportsList}" var="report">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${report.closed}">
                            <a id="closed" href="<c:url value="/printReport/${report.id}"/>">${report.period}</a>
                        </c:when>

                        <c:otherwise>
                            <a href="<c:url value="/showReport/${report.id}"/>">${report.period}</a>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${report.closed}">
                            <div id="closed">Запрещено редактировать</div>
                        </c:when>

                        <c:otherwise>
                            <a href="#" onClick="locationConfirm(${report.id});">Удалить</a>
                        </c:otherwise>
                    </c:choose>
                </td>

                <%--<td>--%>
                    <%--<c:choose>--%>
                        <%--<c:when test="${isAdmin}">--%>
                            <%--<a href="<c:url value="/loadFromBilling/${report.id}"/>">Посмотреть</a>--%>
                            <%--&lt;%&ndash;<div id="closed">Запрещено редактировать</div>&ndash;%&gt;--%>
                        <%--</c:when>--%>

                        <%--<c:otherwise>--%>
                        <%--</c:otherwise>--%>
                    <%--</c:choose>--%>
                <%--</td>--%>
            </tr>
          </c:forEach>



        </table>
      </c:if>
    </div>
  </body>
</html>

