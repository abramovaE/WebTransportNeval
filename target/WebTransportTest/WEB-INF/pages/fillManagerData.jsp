<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
    <head>
      <title>Учетная запись пользователя ${user.username}</title>
      <style>
        <%@include file="../../resources/style.css" %>
      </style>
    </head>


    <body class="background_blue">
        <div class="top">
            <div class="h">Учетная запись пользователя ${user.username}</div>
            <c:url var="fillManagerDataAction" value="/fillManagerData/save"/>
            <form:form action="${fillManagerDataAction}" commandName="user">
                <table class="big_table">
                    <tr>
                        <td><form:hidden path="id"/></td>
                        <td><form:hidden path="password"/></td>

                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="username"><spring:message text="Логин"/> </form:label></td>
                        <td class="big_table_input"><form:input cssClass="input" path="username"/></td>
                    </tr>


                    <tr>
                        <td class="big_table_label"><form:label path="surname"><spring:message text="Фамилия"/></form:label></td>
                        <td class="big_table_input"><form:input cssClass="input" path="surname"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="name"><spring:message text="Имя"/></form:label></td>
                        <td class="big_table_input"><form:input path="name" cssClass="input"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="patronymic"><spring:message text="Отчество"/></form:label></td>
                        <td class="big_table_input"><form:input path="patronymic" cssClass="input"/></td>
                    </tr>


                    <tr>
                        <td><form:hidden path="post"/></td>
                        <td><form:hidden path="auto"/></td>
                        <td><form:hidden path="autoNumber"/></td>
                        <td><form:hidden path="summerNorm"/></td>
                        <td><form:hidden path="winterNorm"/></td>
                        <td><form:hidden path="year"/></td>
                        <td><form:hidden path="engineVolume"/></td>
                        <td><form:hidden path="transmission"/></td>
                        <td><form:hidden path="transponder"/></td>
                    </tr>


                    <tr>
                        <td class="big_table_label"><form:label path="eMail"><spring:message text="адрес e-mail"/></form:label></td>
                        <td class="big_table_input"><form:input path="eMail" cssClass="input"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"></td>
                        <td><a class="no_decor" href="<c:url value="/changePassword"/>"><div class="welcome_button no_margin">Изменить пароль</div></a></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"></td>
                        <td><input class="login_button no_margin" type="submit" value="<spring:message text="Изменить"/>"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"></td>
                        <td><a class="no_decor" href="<c:url value="/welcome"/>"><div class="welcome_button no_margin">Назад</div></a></td>
                    </tr>
                </table>
            </form:form>
        </div>
    </body>
</html>
