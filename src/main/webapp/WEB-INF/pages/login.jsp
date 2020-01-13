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

    <body>
        <div class="menu" id="login">
                <c:url value="/j_spring_security_check" var="loginUrl" />
            <form action="${loginUrl}" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <input type="text" id="form_input" name="j_login" value="Логин" onfocus="if (this.value == 'Логин') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Логин';}">

                <input type="password" id="form_input" name="j_password" value="Password" onfocus="if (this.value == 'Password') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Password';}">




                <c:if test="${message != null}">
                    <script type="text/javascript">alert('Сообщение: ${message}');</script>
                </c:if>

                <c:if test="${error !=null}">
                    <script type="text/javascript">alert('Ошибка: ${error}');</script>
                </c:if>

                <button type="submit" id="submit_button">Войти</button>

            </form>



                <%--&lt;%&ndash;<c:url value="/j_spring_security_check" var="loginUrl" />&ndash;%&gt;--%>
                <%--<form action="${loginUrl}" method="post">--%>
                    <%--<input type="text" class="form-control" name="j_username" placeholder="Логин" required>--%>
                    <%--<input type="password" class="form-control" name="j_password" placeholder="Пароль" required>--%>
                        <%--<button type="submit" id="submit_button">Войти</button>--%>
                <%--</form>--%>

        </div>
    </body>
</html>
