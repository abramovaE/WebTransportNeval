<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
    <head>
        <title>Login</title>
        <style>
            <%@include file="../../resources/style.css" %>
        </style>
    </head>

    <body class="background_blue">
        <div class="center">
            <div>
                <c:url value="/j_spring_security_check" var="loginUrl" />
                <form action="${loginUrl}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <input type="text" class="form_input" name="j_username" value="Логин" onfocus="if (this.value == 'Логин') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Логин';}">

                    <input type="password" class="form_input" name="j_password" value="Password" onfocus="if (this.value == 'Password') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Password';}">

                    <c:if test="${message != null}">
                        <div> Сообщение: ${message}</div>
                    </c:if>

                    <c:if test="${error !=null}">
                        <div> Ошибка: ${error}</div>
                    </c:if>

                    <div class="menu">
                        <button type="submit" class="login_button">Войти</button>
                        <%--<a class="no_decor" href="<c:url value="/registration"/>"><div class="login_button">Зарегистрироваться</div></a>--%>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
