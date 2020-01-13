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
            .noprint {display: none;}
                @page {
                    margin-top: 50px;
                    margin-bottom: 100px;
                    margin-left: 0px;
                    margin-right: 0px;
                    page-break-inside: avoid;
                }
        </style>
    </head>

    <body id="print">
        <div class="top">
            <div class="h_print">Отчет о транспортных расходах</div>
            <div class="h_print">${report.period}</div>

            <div>
                <div>МОЛ (должность, Ф.И.О.): <i>${user.mol}</i></div>
                <c:if test="${!empty allDays}">
                            <table>
                                <tr>
                                <th style="width: 10%">Дата</th>
                                <th style="width: 10%">Сумма</th>
                                <th style="width: 10%">Стоимость за литр</th>
                                <th style="width: 30%">Пункт отправления</th>
                                <th style="width: 30%">Пункт прибытия</th>

                                <c:if test="${report.user.accountancyType eq 'зональная'}">
                                    <th>Зональная стоимость</th>
                                </c:if>
                                </tr>

                            <c:forEach items="${allDays}" var="day">
                                <tr>
                                    <td class=" t_center">${day.date}</td>
                                    <td>${day.summ}</td>
                                    <td>${day.costByLiter}</td>

                                      <c:if test="${!empty day.points}">

                                        <c:forEach items="${day.points}" var="point">
                                            <td>${point.departure.address}</td>
                                            <td>${point.arrival.address}</td>

                                            <c:if test="${report.user.accountancyType eq 'зональная'}">
                                                <td>${point.costByZone}</td>
                                            </c:if>
                                            <tr/>



                                    <td></td>
                                    <td></td>
                                    <td></td>

                                        </c:forEach>
                                    </c:if>

                                <c:if test="${day.shipping}">
                                    <tr><td colspan="100">Надбавка за грузоперевозки: 500р</td></tr>
                                </c:if>


                                </tr>
                            </c:forEach>
                            </table>
                        </c:if>

                <div>Сумма по чекам: ${report.sumSumm} руб</div>
                <div>Компенсация сотовой связи: ${totalMobile} руб</div>
                <div>Цена за километр: ${report.priceForOneKm} руб/км</div>
                <div>Расстояние всего: ${report.sumKmDistance} км</div>
                <div>Проезд в нуждах компании: ${report.company} руб</div>
                <c:if test="${shippingSumm ne 0}">
                    <tr><td>Грузоперевозки: ${shippingSumm}</td></tr>
                </c:if>

                <c:if test="${report.user.accountancyType eq 'зональная'}">
                    <div>Сумма зональной стоимости: ${report.sumZone} руб</div>
                    <div>Итого: ${totalSum + totalMobile} руб</div>
                </c:if>

                <c:if test="${report.user.accountancyType eq 'одометрическая'}">
                    <div>Компенсация амортизации: ${totalAmort} руб</div>
                </c:if>

                <div>Итого: ${totalSum} руб</div>
                <div>Подпись: ${user.shortFullName} _________________________ </div>
                <div>Согласовано: </div>
            </div>
        </div>

        <span class="noprint">
            <input class="print_button" type="button" value="Печать" onclick="print()"></input>
        </span>
    </body>
</html>

