<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
    <head>
      <title>Новый отчет</title>
        <style type="text/css">

        <%@include file="../../resources/style.css" %>
      </style>


        <style media='print' type='text/css'>
            #navbar-iframe {display: none; height: 0px; visibility: hidden;}
            .noprint {display: none;}
            body {background:#FFF; color:#000;}
            a {text-decoration: underline; color:#00F;}
            @page {
                margin: 0cm;
            }
        </style>


    </head>

    <body class="background_print">
        <div class="top">
            <div class="h_print">Отчет о транспортных расходах</div>
            <div class="h_print">${report.period}</div>

            <div>
                <div>МОЛ (должность, Ф.И.О.): <i>${mol}</i></div>


                        <c:if test="${!empty allDays}">

                            <table class="big_table_print">

                            <tr class="tr_print">

                                <th style="width: 10%" class="th_print">Дата</th>
                                <th style="width: 10%" class="th_print">Сумма</th>
                                <th style="width: 10%" class="th_print">Стоимость за литр</th>


                                <th style="width: 30%" class="th_print">Пункт отправления</th>
                                <th style="width: 30%" class="th_print">Пункт прибытия</th>



                                <c:if test="${report.user.accountancyType eq 'зональная'}">
                                    <th class="th_print">Зональная стоимость</th>
                                </c:if>

                                <%--<th class="th_print">Фактический адрес</th>--%>



                            </tr>

                            <c:forEach items="${allDays}" var="day">




                                  <tr class="tr_print">
                                    <td class="td_print t_center">${day.date}</td>
                                    <td class="td_print">${day.summ}</td>
                                    <td class="td_print">${day.costByLiter}</td>

                                      <c:if test="${!empty day.points}">

                                        <c:forEach items="${day.points}" var="point">
                                            <td class="td_print">${point.depAdress}</td>
                                            <td class="td_print">${point.arrAdress}</td>

                                            <c:if test="${report.user.accountancyType eq 'зональная'}">
                                                <td class="td_print">${point.costByZone}</td>
                                            </c:if>

                                            <%--<td>${point.factAdress}</td>--%>


                                            <tr class="tr_empty"/>

                                    <td class="td_empty"></td>
                                    <td class="td_empty"></td>
                                    <td class="td_empty"></td>

                                        </c:forEach>
                                    </c:if>

                                  </tr>




                            </c:forEach>
                            </table>
                        </c:if>

                <div>Подпись: ${user.shortFullName} _________________________ </div>
                <div>Итого: ${totalSum} р.</div>
                <div>Согласовано: </div>





          </div>
        </div>

        <span class="noprint">
            <input class="print_button" type="button" value="Печать" onclick="print()"></input>
        </span>


    </body>
</html>

