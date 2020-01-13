<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
    <head>
      <title>Отчет пользователя ${report.user.shortFullName} о транспортных расходах за ${report.period}</title>
      <style>
        <%@include file="../../resources/style.css" %>
      </style>
    </head>

    <body>
    <header>
        <div class="container">
            <div>Отчет о транспортных расходах</div>
            <c:forEach items="${topMenu}" var="btn">
                <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
            </c:forEach>
            <%--<div></div>--%>
        </div>
    </header>

    <div class="top">
    <div>
    <c:if test="${text != null}">
    <script type="text/javascript">alert('${text}');</script>
    </c:if>
    </div>

        <c:url var="saveTheReportAction" value="/createAReport/saveTheReport"/>
        <form:form action="${saveTheReportAction}" commandName="report">
            <table class="create_table">
                <tr>
                    <td colspan="2">МОЛ (должность, Ф.И.О.): ${user.post} ${user.surname} ${user.name} ${user.patronymic}</td>
                </tr>

                <tr>
                    <td><form:label path="year"><spring:message text="Год: "/></form:label></td>
                    <td><form:input path="year" type="number" min="${year-1}" max="2100" required="true"/></td>
                </tr>

                <tr>
                    <td><form:label path="month"><spring:message text="Месяц: "/></form:label></td>
                    <td>
                        <form:select path="month">
                        <c:forEach items="${monthsList}" var="m">
                            <form:option value="${m}"/>
                        </c:forEach>
                        </form:select>
                </tr>

                <tr>
                    <td><form:label path="auto"><spring:message text="Текущий автомобиль"/></form:label></td>
                    <td>
                        <form:select path="auto.id">
                            <c:forEach items="${userAutos}" var="userAuto">
                                <form:option label="${userAuto.brand}" value="${userAuto.id}"/>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>

                <form:hidden path="id"/>
                <form:hidden path="sumKmDistance"/>
                <form:hidden path="sumSumm"/>
                <form:hidden path="sumZone"/>
                <form:hidden path="blockedReportData.id"/>

                <form:hidden path="closed"/>
                <form:hidden path="mobileWeeks"/>
                <form:hidden path="mobile"/>
                <form:hidden path="mediumFuelPrice"/>

                <%--<form:hidden path="monthNumber"/>--%>
                </tr>

                <tr>
                    <td colspan="2"><input id="submit_button" type="submit" value="<spring:message text="Сохранить"/>"/></td>
                </tr>
            </table>
        </form:form>



        <c:if test="${!empty allDays}">
            <table class="small_table" id="report">
                <tr>
                    <c:if test="${canDelete}">
                        <th></th>
                    </c:if>
                    <th>Дата</th>
                    <th>Сумма</th>
                    <th>Стоимость за литр</th>

                    <c:if test="${isAdmin || isManager}">
                        <th>Расстояние за день</th>
                    </c:if>


                    <c:if test="${isAdmin || isManager || isFinDir}">
                        <th colspan="4">Данные из биллинга</th>
                    </c:if>


                    <th>Пункт отправления</th>
                    <th>Пункт прибытия</th>
                    <c:if test="${report.user.accountancyType eq 'зональная'}">
                        <th>Зональная стоимость</th>
                    </c:if>
                    <th>Фактический адрес</th>
                    <th>Проезд по ЗСД</th>
                    <%--<c:if test="${isAdmin eq true || isManager eq true}">--%>
                        <th>Расстояние</th>
                    <%--</c:if>--%>




                    <c:if test="${isAdmin || isManager}">
                        <th></th>
                    </c:if>

                </tr>


                <tr>
                    <td colspan="100" id="tr_day"> </td>
                </tr>
                <c:forEach items="${allDays}" var="day">
                    <c:if test="${isAdmin eq true || isManager eq true}">
                        <c:if test="${day.summ ne null && day.summ != 0}">
                            <tr><td colspan="15">Пройдено на данной заправке: ${day.prevFillingDistance/1000}км</td></tr>
                            <tr>
                                <td colspan="100" id="tr_day"> </td>
                            </tr>
                            <tr><td colspan="15" id="pred"><div id="pred" ></div></td> </tr>
                            <tr>
                                <td colspan="100" id="tr_day"> </td>
                            </tr>
                        </c:if>
                    </c:if>


                        <tr>
                            <c:if test="${canDelete}">
                                <c:choose>
                                    <c:when test="${report.closed}"><td  rowspan="${day.points.size()}"></td></c:when>
                                    <c:otherwise>   <td rowspan="${day.points.size()}" class="grey"><a href="<c:url value="/deleteDay/${day.id}"/>">Удалить</a></td></c:otherwise>
                                </c:choose>
                            </c:if>

                            <c:choose>
                                <c:when test="${report.closed}">
                                    <td rowspan="${day.points.size()}">${day.date}</td>
                                </c:when>
                                <c:otherwise>
                                    <td rowspan="${day.points.size()}" class="grey"><a href="<c:url value="/editDay/${day.id}"/>">${day.date}</a></td>
                                </c:otherwise>
                            </c:choose>

                            <td rowspan="${day.points.size()}">${day.summ}</td>
                            <td rowspan="${day.points.size()}">${day.costByLiter}</td>

                            <c:if test="${isAdmin || isManager}">
                                <td rowspan="${day.points.size()}" id="${day.dayDistance/1000 > 150 ? "longDist": ""}">${day.dayDistance/1000}км</td>
                            </c:if>
                            <c:if test="${!empty day.points}">
                            <c:forEach items="${day.points}" var="point" varStatus="counter">


                                <c:if test="${isAdmin || isManager || isFinDir}">
                                    <c:if test="${! empty day.departmentTrip}">
                                        <td>${day.departmentTrip.get(counter.index).startTime}</td>
                                        <td>${day.departmentTrip.get(counter.index).stopTime}</td>
                                        <td id="${day.departmentTrip.get(counter.index).getIdColorForAddr(point.departure.address, point.arrival.address)}">
                                                ${day.departmentTrip.get(counter.index).addr}</td>
                                        <td>${day.departmentTrip.get(counter.index).comm}</td>
                                    </c:if>

                                    <c:if test="${empty day.departmentTrip}">
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </c:if>
                                </c:if>



                            <td id="${point.idColor}">${point.departure.address}</td>
                            <td id="${point.idColor}">${point.arrival.address}</td>
                            <c:if test="${report.user.accountancyType eq 'зональная'}">
                                <td id="${point.idColor}">${point.costByZone}</td>
                            </c:if>
                            <td id="${point.idColor}">${point.factAdress}</td>
                            <td id="${point.idColor}">
                                <c:if test="${point.zsd eq true}">Да</c:if>
                            </td>
                                <%--<c:if test="${isAdmin eq true || isManager eq true}">--%>
                            <td id="${point.idColor}">${point.distance/1000}км</td>
                                <%--</c:if>--%>

                            <c:if test="${isAdmin || isManager}">
                                <c:choose>
                                    <c:when test="${report.closed}"/>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${point.changed eq true}">
                                                <td><a href="<c:url value="/setIsNeedChange/${point.id}"/>">Утвердить изменения</a></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="grey"><a href="<c:url value="/setIsNeedChange/${point.id}"/>">Исправить</a></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </tr>
                        </c:forEach>
                        </c:if>

                        </tr>

                    <c:if test="${day.shipping}">
                        <tr><td colspan="100">Надбавка за грузоперевозки: 500р</td></tr>
                    </c:if>


                    <tr>
                        <td colspan="100" id="tr_day"> </td>
                    </tr>


                </c:forEach>




            </table>
        </c:if>

        <c:if test="${canDelete}">
            <table class="create_table">
                <c:url var="addTheRouteAction" value="/addNewDay/${report.id}/save"/>
                <form:form action="${addTheRouteAction}" commandName="day">
                    <tr>
                        <th>Число</th>
                        <th>Сумма</th>
                        <th>Стоимость литра</th>

                        <c:if test="${shipping}">
                            <th>Грузоперевозки</th>
                        </c:if>

                    </tr>
                    <tr>
                        <td><form:input type="number" min = "1" max = "31" path="dayNumber" required="true"/></td>
                        <td><form:input type="number" step="0.01" path="summ"/></td>
                        <td><form:input type="number" step="0.01" path="costByLiter"/></td>

                        <c:if test="${shipping}">
                            <td><form:checkbox path="shipping"/></td>
                        </c:if>



                        <td><input id="submit_button" type="submit" value="<spring:message text="Добавить маршрут"/>" /></td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <a id="save_button"  href="<c:url value="/welcome"/>">Сохранить и вернуться в меню</a>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <a id="save_button" href="<c:url value="/printReport/${report.id}"/>">Печать</a>
                        </td>
                    </tr>



                </form:form>
            </table>
        </c:if>

        <table class="create_table">
            <tr><td>Сумма по чекам: ${report.sumSumm} руб</td></tr>
            <tr><td>Компенсация сотовой связи: ${totalMobile} руб</td></tr>
            <tr><td>Цена за километр: ${report.priceForOneKm} руб/км</td></tr>
            <tr><td>Расстояние всего: ${report.sumKmDistance} км</td></tr>
            <tr><td>Проезд в нуждах компании: ${report.company} руб</td></tr>

            <c:if test="${shippingSumm ne 0}">
                <tr><td>Грузоперевозки: ${shippingSumm}</td></tr>
            </c:if>

            <c:if test="${report.user.accountancyType eq 'зональная'}">
                <tr><td>Сумма зональной стоимости: ${report.sumZone} руб</td></tr>
                <tr><td>Итого: ${report.sumZone + totalMobile + shippingSumm}руб</td></tr>
            </c:if>

            <c:if test="${report.user.accountancyType eq 'одометрическая'}">
                <tr><td>Компенсация амортизации: ${totalAmort} руб</td></tr>
            </c:if>

            <tr><td>Итого: ${totalSum} руб</td></tr>

            <tr><td>Подпись: ${user.shortFullName} _________________________ </td></tr>
            <tr><td>Согласовано: </td></tr>
        </table>
    </div>


    </body>
</html>