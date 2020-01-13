<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
    <head>
      <title>Новый отчет</title>
      <style>
        <%@include file="../../resources/style.css" %>
      </style>
    </head>

    <body class="background_blue">
        <div class="top">
            <div class="h">Отчет о транспортных расходах</div>

            <div>
                <c:if test="${error != null}">
                    ${error}
                </c:if>
            </div>

            <div>
                <div class="string">МОЛ (должность, Ф.И.О.): ${user.post} ${user.surname} ${user.name} ${user.patronymic}</div>


                    <div class="string">
                        <div class="left"><label path="period">Период: ${report.period}</label></div>
                    </div>


                    <table class="big_table">
                    <tr>


                        <c:if test="${!empty allDays}">

                            <table>

                            <tr>
                                <th class="long_table_head">Удалить день</th>
                                <th class="long_table_head">Дата</th>
                                <th class="long_table_head">Сумма</th>
                                <th class="long_table_head">Стоимость за литр</th>
                                <th style="width: 450px" class="long_table_head">Пункт отправления</th>
                                <th style="width: 450px" class="long_table_head">Пункт прибытия</th>



                                <c:if test="${report.user.accountancyType eq 'зональная'}">
                                    <th class="long_table_head">Зональная стоимость</th>
                                </c:if>

                                <th class="long_table_head">Фактический адрес</th>

                            </tr>

                            <c:forEach items="${allDays}" var="day">




                                  <tr class="big_table_tr">
                                <td><a class="no_decor" href="<c:url value="/addNewDay/${report.id}/deleteNewDay/${day.id}"/>"><div class="edit_button">Удалить</div></a></td>

                                <%--<td class="big_table_td">${day.date}</td>--%>
                                    <td><a class="no_decor" href="<c:url value="/editDay/${day.id}"/>"><div class="edit_button">${day.date}</div></a></td>
                                    <td class="big_table_td">${day.summ}</td>
                                    <td class="big_table_td">${day.costByLiter}</td>

                                      <c:if test="${!empty day.points}">

                                        <c:forEach items="${day.points}" var="point">
                                            <td class="big_table_td">${point.depAdress}</td>
                                            <td class="big_table_td">${point.arrAdress}</td>

                                            <c:if test="${report.user.accountancyType eq 'зональная'}">
                                                <td class="big_table_td">${point.costByZone}</td>
                                            </c:if>

                                            <td class="big_table_td">${point.factAdress}</td>

                                            <tr/>

                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>

                                        </c:forEach>
                                    </c:if>



                                <%--<td><a class="no_decor" href="<c:url value="/editDay/${day.id}"/>"><div class="welcome_button no_margin font_20">Изменить день</div></a></td>--%>


                                    <%--</td>--%>
                                  </tr>




                            </c:forEach>
                            </table>
                        </c:if>





                    </tr>


                    <tr>
                        <div class="margin_top _800">

                            <table class="small_table">

                                <c:url var="addTheRouteAction" value="/addNewDay/${report.id}/saveOrUpdateDay"/>
                                <form:form action="${addTheRouteAction}" commandName="day">
                                    <tr>
                                        <th class="long_table_head">Дата</th>
                                        <th class="long_table_head">Сумма</th>
                                        <th class="long_table_head">Стоимость литра</th>
                                    </tr>

                                    <tr>
                                        <td>
                                            <form:input type="number" min = "1" max = "31" cssClass="input_small" path="date"/>
                                        </td>

                                        <td>
                                            <form:input type="number" step="0.01" cssClass="input_small" path="summ"/>
                                        </td>

                                        <td>
                                            <form:input type="number" step="0.01" cssClass="input_small" path="costByLiter"/>
                                        </td>

                                        <td>
                                            <input type="submit" class="login_button small" value="<spring:message text="Добавить маршрут"/>" />
                                        </td>
                                    </tr>



                                        <tr>
                                            <td colspan="4">
                                                <a class="no_decor no_margin" href="<c:url value="/welcome"/>"><div class="welcome_button">Сохранить и вернуться в меню</div></a>
                                            </td>
                                        </tr>


                                    <tr>
                                        <td colspan="4">
                                            <a class="no_decor no_margin" href="<c:url value="/printReport/${report.id}"/>"><div class="welcome_button">Печать</div></a>
                                        </td>
                                    </tr>
                                    



                                </form:form>


                            </table>

                            <div>

                            </div>


                        </div>



                    </tr>
                        <tr>

                        </tr>

                </table>
                <div  class="string">Подпись: ${user.shortFullName} _________________________ </div>
                <div  class="string">Итого: ${totalSum} р.</div>
                <div  class="string">Согласовано: </div>
          </div>
        </div>
    </body>
</html>

