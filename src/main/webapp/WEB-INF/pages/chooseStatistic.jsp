<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
    <title>${title}</title>
    <style>
        <%@include file="../../resources/style.css" %>
    </style>
</head>


<body>

<header>
    <div class="container">
        <div>${title}</div>
        <c:forEach items="${topMenu}" var="btn">
            <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
        </c:forEach>
    </div>
</header>


<div class="top">

    <c:url var="chooseStatisticAction" value="/showStatistic"/>
    <form:form action="${chooseStatisticAction}" commandName="stat">


    <table class="create_table" id="edit_form">

        <tr>
            <td>Начало периода</td>
            <td>
                <form:select path="startMonth">
                    <c:forEach items="${monthsList}" var="m">
                        <form:option value="${m}"/>
                    </c:forEach>
                </form:select>

            </td>
            <td><form:input path="startYear" type="number" min="2017" max="2100" required="true" value="2017"/></td>
        </tr>

        <tr>
            <td>Конец периода</td>
            <td>
                <form:select path="endMonth">
                    <c:forEach items="${monthsList}" var="m">
                        <form:option value="${m}"/>
                    </c:forEach>
                </form:select>

            </td>
            <td><form:input path="endYear" type="number" min="2017" max="2100" required="true" value="2017"/></td>
        </tr>


        <tr>
            <td></td>
            <td colspan="2">
                    <div>Множественный выбор с ctrl или shift</div>
                <form:select path="usersId" placeholder="Выберите водителя" multiple="true">
                    <c:forEach items="${drivers}" var="user">
                        <form:option label="${user.shortFullName}" value="${user.id}"/>
                    </c:forEach>
                    <%--<form:option label="все" value="0"/>--%>
                </form:select>
            </td>


        </tr>



        <tr>
            <td colspan="3"><input id="submit_button" type="submit" value="<spring:message text="Считать"/>"/></td>

        </tr>
    </table>


    </form:form>

</div>
</body>
</html>



