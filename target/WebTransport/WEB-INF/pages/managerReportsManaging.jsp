<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
  <head>
    <title>Управление отчетами</title>
    <style>
      <%@include file="../../resources/style.css" %>
    </style>

  </head>

  <body>
  <header>


      <div class="container">
          <div>Управление отчетами за ${period}</div>
          <c:forEach items="${topMenu}" var="btn">
              <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
          </c:forEach>
      </div>
  </header>

  <div class="top">

      <c:if test="${text != null}">
          <script type="text/javascript">alert('${text}');</script>
      </c:if>

      <c:if test="${!empty reportsList}">
          <table class="big_table">
              <tr>
                  <th>Посмотреть отчет водителя</th>
                  <th>Водитель</th>
                  <th>Посмотреть данные для бухгалтерского отчета</th>

                  <c:if test="${isAdmin || isFinDir|| isBuhgalter}">
                      <th>Сформоровать xlsx-файл и отправить на e-mail</th>
                  </c:if>


                  <c:if test="${isAdmin || isFinDir}">
                      <th>Запретить/разрешить редактирование отчета</th>
                  </c:if>



              </tr>
              <c:forEach items="${reportsList}" var="report">
                  <tr>
                      <td>
                          <c:choose>
                              <c:when test="${report.closed}">
                                <a id="closed" href="<c:url value="/printReport/${report.id}"/>">${report.period}</a>
                              </c:when>
                              <c:otherwise>
                                  <a href="<c:url value="/printReport/${report.id}"/>">${report.period}</a>
                              </c:otherwise>
                          </c:choose>
                      </td>



                      <td>
                          <c:choose>
                              <c:when test="${report.user.surname eq null}">
                                  <c:choose>
                                      <c:when test="${report.closed}">
                                          <div id="closed"> ${report.user.shortFullName}</div>
                                      </c:when>
                                      <c:otherwise>
                                          <div id="label"> ${report.user.shortFullName}</div>
                                      </c:otherwise>
                                  </c:choose>
                              </c:when>

                              <c:otherwise>
                                  <c:choose>
                                      <c:when test="${report.closed}">
                                          <div id="closed">${report.user.shortFullName}</div>
                                      </c:when>
                                      <c:otherwise>
                                          <div id="label">${report.user.shortFullName}</div>
                                      </c:otherwise>
                                  </c:choose>
                              </c:otherwise>
                          </c:choose>
                      </td>



                      <td>
                          <c:choose>
                              <c:when test="${report.closed}">
                                  <a id="closed" href="<c:url value="/showReport/${report.id}"/>">${report.period}</a>
                              </c:when>
                              <c:otherwise>
                                  <a href="<c:url value="/showReport/${report.id}"/>">${report.period}</a>
                              </c:otherwise>
                          </c:choose>
                      </td>


                      <c:if test="${isAdmin || isFinDir || isBuhgalter}">
                          <td>
                              <c:choose>
                                  <c:when test="${report.closed}">
                                      <a id = "closed" href="<c:url value="/makeXlsx/${report.id}"/>">Сформировать</a>
                                  </c:when>
                                  <c:otherwise>
                                      <a href="<c:url value="/makeXlsx/${report.id}"/>">Сформировать</a>
                                  </c:otherwise>
                              </c:choose>
                          </td>
                      </c:if>


                      <c:if test="${isAdmin || isFinDir}">
                          <td>
                          <c:choose>
                              <c:when test="${report.closed}">
                                  <c:choose>
                                      <c:when test="${isFinDir}">
                                          <a id = "closed" href="<c:url value="/openTheReport/${report.id}"/>">Разрешить</a></td>
                                      </c:when>
                                      <c:otherwise>
                                          <a id = "closed" href="<c:url value="/editClosedReport/${report.id}"/>">Редактировать</a></td>
                                      </c:otherwise>
                                  </c:choose>
                              </c:when>


                              <c:otherwise>
                                  <a href="<c:url value="/closeTheReport/${report.id}"/>">Запретить</a>
                              </c:otherwise>
                          </c:choose>
                          </td>
                      </c:if>
                  </tr>
              </c:forEach>

              <tr>
                  <td colspan="5">
                      <a id="save_button" href="<c:url value="/welcome"/>">Назад</a>
                  </td>

              </tr>

          </table>
      </c:if>



  </div>

  </body>
</html>
