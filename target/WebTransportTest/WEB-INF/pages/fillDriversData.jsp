<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
    <head>
      <title>Заполнить карточку водителя</title>
      <style>
        <%@include file="../../resources/style.css" %>
      </style>
    </head>


    <body class="background_blue">
        <div class="top">
            <div class="h">Карточка водителя</div>
            <c:url var="fillDriversDataAction" value="/fillDriversData/save"/>
            <form:form action="${fillDriversDataAction}" commandName="user">
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
                        <td class="big_table_label"><form:label path="post"><spring:message text="Должность"/></form:label></td>
                        <td class="big_table_input"><form:input path="post" cssClass="input"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="auto"><spring:message text="Марка машины"/></form:label></td>
                        <td class="big_table_input"><form:input path="auto" cssClass="input"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="autoNumber"><spring:message text="Номер машины"/></form:label></td>
                        <td class="big_table_input"><form:input path="autoNumber" cssClass="input"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="summerNorm"><spring:message text="Норма расхода (лето)"/></form:label></td>
                        <td class="big_table_input"><form:input type="number" step="0.01" path="summerNorm" cssClass="input"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="winterNorm"><spring:message text="Норма расхода (зима)"/></form:label></td>
                        <td class="big_table_input"><form:input type="number" step="0.01" path="winterNorm" cssClass="input"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="year"><spring:message text="Год выпуска"/></form:label></td>
                        <td class="big_table_input"><form:input type="number" path="year" cssClass="input"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="engineVolume"><spring:message text="Объем двигателя"/></form:label></td>
                        <td class="big_table_input"><form:input type="number" step="0.01" path="engineVolume" cssClass="input"/></td>
                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="transmission"><spring:message text="Тип коробки (авто/мех)"/></form:label></td>
                        <td class="big_table_input">
                            <form:select path="transmission" cssClass="input">
                                <form:option value="механика"></form:option>
                                <form:option value="автомат"></form:option>
                            </form:select>
                            <%--<form:input path="transmission" cssClass="input"/></td>--%>
                    </tr>

                    <tr>
                        <td class="big_table_label"><form:label path="transponder"><spring:message text="Номер транспондера"/></form:label></td>
                        <td class="big_table_input"><form:input path="transponder" cssClass="input"/></td>
                    </tr>

                    <tr>
                        <td><form:hidden path="eMail"/></td>
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
