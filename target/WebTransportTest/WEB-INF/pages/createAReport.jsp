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
                    <div class="string">МОЛ (должность, Ф.И.О.): ${user.post} ${user.surname} ${user.name} ${user.patronymic}</div>
                        <c:url var="saveTheReportAction" value="/createAReport/saveTheReport"/>
                        <form:form action="${saveTheReportAction}" commandName="report">

                            <div class="report">

                                <div class="string">
                                <c:if test="${report.period != null}">
                                    <div class="left"><form:label path="period"><spring:message text="Период: "/></form:label></div>
                                    <div class="rigth">${report.period}</div>
                                </c:if>

                                <c:if test="${report.period == null}">
                                    <div class="left"><form:label path="period"><spring:message text="Период: "/></form:label></div>
                                    <div class="right"><form:input path="period" cssClass="input_small" placeholder="Месяц год"/></div>
                                </c:if>
                            </div>

                            <div class="string">
                                <c:if test="${text != null}">${text}</c:if>
                            </div>




                                <div class="string"><input class="login_button no_margin" type="submit" value="<spring:message text="Добавить день"/>" /></div>
                                <div class="string"><a class="no_decor" href="<c:url value="/welcome"/>"><div class="welcome_button no_margin">Сохранить и вернуться в меню</div></a></div>


                            </div>
                    </form:form>
                    <div  class="string">Подпись: ${user.surname} ${user.name} ${user.patronymic} _________________________ </div>
                    <div  class="string">Итого: </div>
                    <div  class="string">Согласовано: </div>
                </div>
            </div>
    </body>
</html>
