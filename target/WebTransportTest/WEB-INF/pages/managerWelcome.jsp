<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
    <title>Welcome</title>
    <style>
        <%@include file="../../resources/style.css" %>
    </style>
</head>


<body class="background_blue">

<div class="top">


    <div class="h">${username}</div>



    <div class="menu">

        <a class="no_decor" href="<c:url value="/fillManagerData"/>"><div class="welcome_button">Учетная запись</div></a>
        <%--<a class="no_decor" href="<c:url value="/fillDriversData"/>"><div class="welcome_button">Заполнить карточку водителя</div></a>--%>
        <a class="no_decor" href="<c:url value="/managerReportsManaging"/>"><div class="welcome_button">Посмотреть отчеты</div></a>
        <%--<a class="no_decor" href="<c:url value="/createAReport"/>"><div class="welcome_button">Создать новый отчет</div></a>--%>
        <a class="no_decor" href="<c:url value="/login?logout"/>"><div class="welcome_button">Выйти</div></a>

    </div>


</div>




</body>
</html>
