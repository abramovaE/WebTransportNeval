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


<body>

<header>
    <div class="container">
        <div>${login}</div>
        <c:forEach items="${topMenu}" var="btn">
            <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
        </c:forEach>
    </div>
</header>


<div class="top">
    <c:if test="${text != null}">
        <script type="text/javascript">alert('${text}');</script>
    </c:if>


    <div class="vertical_list" >
        <c:if test="${isAdmin}">
            <div class="tabs">
                <input type="radio" name="inset" value="" id="tab_1" checked>
                <label for="tab_1">Пользователи</label>

                <input type="radio" name="inset" value="" id="tab_2">
                <label for="tab_2">Адреса и зоны</label>

                <input type="radio" name="inset" value="" id="tab_3">
                <label for="tab_3">Мои данные</label>

                <input type="radio" name="inset" value="" id="tab_4">
                <label for="tab_4">Написать разработчику</label>

                <input type="radio" name="inset" value="" id="tab_5">
                <label for="tab_5">Админ</label>

                <div id="txt_1">
                    <div><a href="<c:url value="/mainSettings"/>">Основные настройки</a></div>
                    <div><a href="<c:url value="/usersManaging/all"/>">Все пользователи</a></div>
                    <div><a href="<c:url value="/usersManaging/drivers"/>">Водители</a></div>
                    <div><a href="<c:url value="/usersManaging/other"/>">Не водители</a></div>
                    <div><a href="<c:url value="/selectItem/selectDriver"/>">Расчет нормативов расхода топлива</a></div>
                    <div><a href="<c:url value="/selectItem/menuOfReports"/>">Посмотреть отчеты</a></div>
                    <div><a href="<c:url value="/selectItem/fuelYear"/>">Посмотреть графики расхода топлива</a></div>
                    <div><a href="<c:url value="/selectItem/mobileYear"/>">Компенсация мобильной связи</a></div>
                    <div><a href="<c:url value="/showExpenses"/>">Посмотреть затраты на адрес</a></div>
                    <div><a href="<c:url value="/depManaging"/>">Управление отделами</a></div>

                </div>

                <div id="txt_2">
                    <div><a href="<c:url value="/addressesManaging"/>">Управление адресами</a></div>
                    <div><a href="<c:url value="/costByZone"/>">Стоимость зон</a></div>
                </div>


                <div id="txt_3">
                    <div><a href="<c:url value="/usersManaging/editUser/${autorizedUser.id}"/>">Учетная запись</a></div>
                    <div><a href="<c:url value="/selectItem/menuOfUsersAuto/${autorizedUser.id}"/>">Мои автомобили</a></div>
                    <div><a href="<c:url value="/reportsManaging"/>">Посмотреть мои отчеты</a></div>
                    <div><a href="<c:url value="/createAReport"/>">Создать новый отчет</a></div>
                </div>

                <div id="txt_4">
                    <c:url var="sendALetterAction" value="/writeToDeveloper"/>
                    <form:form action="${sendALetterAction}" commandName="message">
                        <table  class="create_table" id="edit_form">
                            <tr>
                                <td><form:label path="subject"><spring:message text="Тема письма"/> </form:label></td>
                                <td><form:input path="subject"/></td>
                            </tr>

                            <tr>
                                <td><form:label path="text"><spring:message text="Текст сообщения"/> </form:label></td>
                                <td><form:textarea id="big" path="text"/></td>
                            </tr>

                            <tr>
                                <td><form:label path="copyToManager"><spring:message text="Отправить копию мне на почту"/> </form:label></td>
                                <td><form:checkbox path="copyToManager"/></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td><input id="submit_button" type="submit" value="<spring:message text="Отправить"/>" /></td>
                            </tr>
                        </table>
                    </form:form>
                </div>

                <div id="txt_5">
                    <%--<div><a href="<c:url value="/countReports"/>">Пересчитать отчеты</a></div>--%>



                    <a href='#' onclick='javascript:window.open("<c:url value="/countReportsInput"/>", "_blank", "scrollbars=0,resizable=0,height=600,width=700");
                            ' title='Пересчитать отчеты' class="modal">
                        Пересчитать отчеты</a>





                </div>


            </div>
        </c:if>

        <c:if test="${isBuhgalter}">
            <div><a href="<c:url value="/usersManaging/editUser/${autorizedUser.id}"/>">Учетная запись</a></div>
            <div><a href="<c:url value="/usersManaging/createAutoReport/35/0/0"/>">Создать новый отчет Шокин</a></div>
            <div><a href="<c:url value="/usersManaging/createAutoReport/36/0/0"/>">Создать новый отчет Григорьев</a></div>
            <div><a href="<c:url value="/selectItem/menuOfReports"/>">Посмотреть отчеты</a></div>
        </c:if>

        <c:if test="${!isAdmin && isManager}">
            <div class="tabs">
                <input type="radio" name="inset" value="" id="tab_1" checked>
                <label for="tab_1">Пользователи</label>
                <input type="radio" name="inset" value="" id="tab_3">
                <label for="tab_3">Мои данные</label>
                <input type="radio" name="inset" value="" id="tab_4">
                <label for="tab_4">Написать разработчику</label>
                <div id="txt_1">
                    <div><a href="<c:url value="/usersManaging/drivers"/>">Управление водителями</a></div>

                    <c:if test="${isFinDir || isAdmin}">
                        <div><a href="<c:url value="/selectItem/selectDriver"/>">Расчет нормативов расхода топлива</a></div>
                        <div><a href="<c:url value="/showExpenses"/>">Посмотреть затраты на адрес</a></div>
                        <div><a href="<c:url value="/showStatistic"/>">Посмотреть статистику</a></div>
                    </c:if>

                    <div><a href="<c:url value="/selectItem/menuOfReports"/>">Посмотреть отчеты</a></div>
                    <div><a href="<c:url value="/selectItem/fuelYear"/>">Посмотреть графики расхода топлива</a></div>
                    <div><a href="<c:url value="/selectItem/mobileYear"/>">Компенсация мобильной связи</a></div>
                </div>

                <div id="txt_3">
                    <div><a href="<c:url value="/usersManaging/editUser/${autorizedUser.id}"/>">Учетная запись</a></div>
                </div>

                <div id="txt_4">
                    <c:url var="sendALetterAction" value="/writeToDeveloper"/>
                    <form:form action="${sendALetterAction}" commandName="message">
                        <table  class="create_table" id="edit_form">
                            <tr>
                                <td><form:label path="subject"><spring:message text="Тема письма"/> </form:label></td>
                                <td><form:input path="subject"/></td>
                            </tr>

                            <tr>
                                <td><form:label path="text"><spring:message text="Текст сообщения"/> </form:label></td>
                                <td><form:textarea id="big" path="text"/></td>
                            </tr>

                            <tr>
                                <td><form:label path="copyToManager"><spring:message text="Отправить копию мне на почту"/> </form:label></td>
                                <td><form:checkbox path="copyToManager"/></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td><input id="submit_button" type="submit" value="<spring:message text="Отправить"/>" /></td>
                            </tr>
                        </table>
                    </form:form>
                </div>
            </div>


        </c:if>






        <c:if test="${!isAdmin && !isManager && !isBuhgalter}">
            <div><a href="<c:url value="/usersManaging/editUser/${autorizedUser.id}"/>">Учетная запись</a></div>
            <div><a href="<c:url value="/selectItem/menuOfUsersAuto/${autorizedUser.id}"/>">Мои автомобили</a></div>
            <div><a href="<c:url value="/reportsManaging"/>">Посмотреть мои отчеты</a></div>
            <div><a href="<c:url value="/createAReport"/>">Создать новый отчет</a></div>
            <div><a href="<c:url value="/calculationFuelNorm/${autorizedUser.id}"/>">Расчет норматива расхода топлива</a></div>
        </c:if>
    </div>
</div>
</body>
</html>
