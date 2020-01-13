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

        <%--<script>--%>
            <%--function showAlert(text)--%>
            <%--{--%>
                <%--alert(text);--%>
            <%--}--%>

        <%--</script>--%>


    </head>

    <body>
    <header>
        <div class="container">
            <div>Новый отчет</div>
            <c:forEach items="${topMenu}" var="btn">
                <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
            </c:forEach>
        </div>
    </header>

            <div class="top">

                <c:if test="${text != null}">
                    <script type="text/javascript">alert('${text}');</script>
                </c:if>


                <div style="color: red">Внимание! В учетной записи появилась кнопка "Добавить автомобиль".
                    При изменении автомобиля теперь необходимо создавать новый и устанавливать его как текущий.</div>

            <c:url var="saveTheReportAction" value="/createAReport/saveTheReport"/>
            <form:form action="${saveTheReportAction}" commandName="report">

                <table class="create_table">
                    <tr>
                        <td colspan="2">МОЛ (должность, Ф.И.О.): ${user.post} ${user.surname} ${user.name} ${user.patronymic}</td>
                    </tr>


                    <%--<c:if test="${report.period != null}">--%>
                        <%--<tr>--%>
                            <%--<td><form:label path="period"><spring:message text="Период: "/></form:label></td>--%>
                            <%--<td>${report.period}</td>--%>
                        <%--</tr>--%>
                    <%--</c:if>--%>

                    <%--<c:if test="${report.year eq null && report.month eq null}">--%>
                        <tr>
                            <td><form:label path="year"><spring:message text="Год: "/></form:label></td>
                            <td><form:input path="year" value="${year}" type="number" min="${year-1}" max="2100" required="true"/></td>
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

                    <form:hidden path="monthNumber"/>

                    <%--</c:if>--%>
                    <tr>
                        <td colspan="2"><input id="submit_button" type="submit" value="<spring:message text="Добавить день"/>"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><a id="save_button" href="<c:url value="/welcome"/>">Сохранить и вернуться в меню</a></td>
                    </tr>
                    <tr>
                        <td colspan="2">Подпись: ${user.surname} ${user.name} ${user.patronymic} _________________________ </td>
                    </tr>
                    <tr>
                        <td colspan="2">Итого: </td>
                    </tr>
                    <tr>
                        <td colspan="2">Согласовано: </td>
                    </tr>
                </table>
            </form:form>
            </div>
    </body>
</html>

