<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




<html>
    <head>
        <title>Регистрация нового пользователя</title>
        <style>
            <%@include file="../../resources/style.css" %>
        </style>
    </head>

    <body>


    <header>
        <div class="container">
            <div>Регистрация нового пользователя</div>
            <c:forEach items="${topMenu}" var="btn">
                <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
            </c:forEach>
        </div>
    </header>



    <div class="top">


        <%--<div>${err}</div>--%>
        <%--<h2 style="color:red; "><form:errors path="${err}" /></h2>--%>
<%----%>
        <%--<h2 style="color:red;"><form:errors /></h2>--%>

        <c:if test="${text != null}">

                <script type="text/javascript">alert('${text}');</script>

        </c:if>
                <c:url var="registrationAction" value="/registration"/>
                <form:form action="${registrationAction}" commandName="newUser">



                    <%--<h2 style="color:red;"><form:errors /></h2>--%>
                    <%--<spring:bind path="login"><spring:message message="></spring:message></spring:bind>--%>
                    
                    <table  class="create_table" id="edit_form">
                        <tr>
                                <td><form:label path="login"><spring:message text="Логин"/></form:label></td>
                                <td><form:input path="login"/></td>
                        </tr>

                        <tr>
                            <td><form:label path="password"><spring:message text="Пароль"/></form:label></td>
                            <td><form:password path="password"/></td>
                            <%--<td><form:errors path="password"></form:errors></td>--%>

                        </tr>

                        <tr>
                            <td><form:label path="confirmPassword"><spring:message text="Подтвердите пароль"/></form:label></td>
                            <td><form:password path="confirmPassword"/></td>
                            <%--<td><form:errors path="confirmPassword"></form:errors></td>--%>

                        </tr>


                        <tr>
                            <td></td>
                            <td><input id="submit_button" type="submit" value="<spring:message text="Регистрация"/>" /></td>
                        </tr>

                    </table>
                </form:form>
    </div>

    </body>
</html>
