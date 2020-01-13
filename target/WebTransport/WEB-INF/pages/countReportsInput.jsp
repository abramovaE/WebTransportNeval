<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
    <title>Пересчитать отчет</title>
    <style>
        <%@include file="../../resources/style.css" %>
    </style>
</head>


<body>




<div class="top">

    <c:url var="countReportAction" value="/countReportsSave"/>
    <form:form action="${countReportAction}" commandName="attr">

    <table class="create_table" id="edit_form">
        <tr>
            <td>
                <form:select path="monthNumber" placeholder="Выберите месяц">
                    <c:forEach items="${months}" var="m">
                        <form:option label="${m.key}" value="${m.value}"/>
                    </c:forEach>
                </form:select>
            </td>
        </tr>



        <tr>
            <td>
                <form:input path="year" type="number" min="2017" max="2100" required="true" value="${currentYear}"/>
            </td>

        </tr>

        <tr>
            <td>
                    <%--<div>Множественный выбор с ctrl</div>--%>
                <form:select path="userId" placeholder="Выберите пользователя">
                    <c:forEach items="${drivers}" var="user">
                        <form:option label="${user.shortFullName}" value="${user.id}"/>
                    </c:forEach>
                    <form:option label="все" value="0"/>
                </form:select>
            </td>

        </tr>



        <tr>
            <td colspan="3"><input id="submit_button" type="submit" value="<spring:message text="Пересчитать"/>"/></td>

        </tr>
    </table>


    </form:form>

</div>
</body>
</html>



