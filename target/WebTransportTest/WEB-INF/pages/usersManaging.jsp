
<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
    <title>usersManaging</title>
    <style>
        <%@include file="../../resources/style.css" %>
    </style>
</head>

<body class="background_blue">
<div class="top">
    <div>


        <c:if test="${message != null}">
            <div> Сообщение: ${message}</div>
        </c:if>

        <c:if test="${error !=null}">
            <div> Ошибка: ${error}</div>
        </c:if>


        <c:if test="${!empty usersList}">
            <table>
                <tr>
                    <th class="long_table_head">login</th>
                    <th class="long_table_head">Фамилия</th>
                    <th class="long_table_head">Имя</th>
                    <th class="long_table_head">Отчество</th>
                    <th class="long_table_head">Должность</th>
                    <th class="long_table_head">Марка машины</th>
                    <th class="long_table_head">Номер машины</th>
                    <th class="long_table_head">Расход летом</th>
                    <th class="long_table_head">Расход зимой</th>
                    <th class="long_table_head">Год выпуска</th>
                    <th class="long_table_head">Объем двигателя</th>
                    <th class="long_table_head">Трансмиссия</th>
                    <th class="long_table_head">Номер транспондера</th>
                    <th class="long_table_head">e-mail</th>
                    <th class="long_table_head">Тип отчетности</th>
                </tr>

                <c:forEach items="${usersList}" var="user">

                    <tr class="big_table_tr">

                        <td class="big_table_td">${user.username}</td>
                        <td class="big_table_td">${user.surname}</td>
                        <td class="big_table_td">${user.name}</td>
                        <td class="big_table_td">${user.patronymic}</td>
                        <td class="big_table_td">${user.post}</td>
                        <td class="big_table_td">${user.auto}</td>
                        <td class="big_table_td">${user.autoNumber}</td>
                        <td class="big_table_td">${user.summerNorm}</td>
                        <td class="big_table_td">${user.winterNorm}</td>
                        <td class="big_table_td">${user.year}</td>
                        <td class="big_table_td">${user.engineVolume}</td>
                        <td class="big_table_td">${user.transmission}</td>
                        <td class="big_table_td">${user.transponder}</td>
                        <td class="big_table_td">${user.eMail}</td>
                        <td class="big_table_td">${user.accountancyType}</td>
                        <td class="big_table_td"><a class="no_decor" href="/usersManaging/editUser/${user.id}"><div class="edit_button">Изменить</div></a></td>

                    </tr>

                </c:forEach>
            </table>
        </c:if>

        <div class="menu">
            <a class="no_decor" href="<c:url value="/registration"/>"><div class="login_button">Зарегистрировать нового кота</div></a>
            <a class="no_decor" href="<c:url value="/welcome"/>"><div class="login_button">Назад</div></a>
        </div>

    </div>
</div>
</body>
</html>
