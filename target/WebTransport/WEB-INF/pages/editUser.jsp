<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Учетная запись пользователя ${user.login}</title>
    <style>
        <%@include file="../../resources/style.css" %>
    </style>
</head>


<body>

<header>
    <div class="container">
        <div>Учетная запись пользователя ${user.login}</div>
        <c:forEach items="${topMenu}" var="btn">
            <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
        </c:forEach>
    </div>
</header>


<div class="top">
    <c:if test="${text != null}">
        <script type="text/javascript">alert('${text}');</script>
    </c:if>


        <div class="str">
            <div class="stolb_first">


        <c:url var="fillDriversDataAction" value="/usersManaging/editUser/save"/>
        <form:form action="${fillDriversDataAction}" commandName="user">
            <input type="hidden" name="form" value="editUserForm"/>


        <div class="mini_logo">Данные пользователя</div>

                <table class="create_table" id="edit_form">

                    <form:hidden path="id"/>
                    <form:hidden path="password"/>
                    <form:hidden path="blocked"/>
                    <form:hidden path="buhgalteria"/>
                    <form:hidden path="vuFileName"/>
                    <form:hidden path="vuDate"/>
                    <form:hidden path="fired"/>

                    <form:hidden path="department.id"/>
                    <form:hidden path="department.name"/>
                    <form:hidden path="department.prim"/>
                    <form:hidden path="department.link"/>



                    <tr>
                        <td><form:label path="login"><spring:message text="Логин"/></form:label></td>
                        <td><form:input readonly="true" path="login" cssClass="readonly_style"/></td>
                    </tr>

                    <tr>
                        <td><form:label path="surname"><spring:message text="Фамилия"/></form:label></td>
                        <td><form:input path="surname"/></td>
                    </tr>

                    <tr>
                        <td><form:label path="name"><spring:message text="Имя"/></form:label></td>
                        <td><form:input path="name"/></td>
                    </tr>

                    <tr>
                        <td><form:label path="patronymic"><spring:message text="Отчество"/></form:label></td>
                        <td><form:input path="patronymic"/></td>
                    </tr>

                    <tr>
                        <td><form:label path="post"><spring:message text="Должность"/></form:label></td>
                        <td><form:input path="post"/></td>
                    </tr>

                    <c:if test="${!isManager}">
                        <tr>
                            <td><form:label path="transponder"><spring:message text="Номер транспондера"/></form:label></td>
                            <td><form:input path="transponder"/></td>
                        </tr>
                    </c:if>

                    <tr>
                        <td><form:label path="eMail"><spring:message text="e-mail"/></form:label></td>
                        <td><form:input path="eMail"/></td>
                    </tr>





                    <c:if test="${!notDriver}">


                        <tr>
                            <td><form:label path="accountancyType"><spring:message text="Тип отчетности"/></form:label></td>
                            <c:choose>
                                <c:when test="${isAdmin}">
                                    <td>

                                        <form:select path="accountancyType">
                                            <c:forEach items="${accTypes}" var="accType">
                                                <form:option value="${accType.name}"/>
                                            </c:forEach>
                                        </form:select>
                                    </td>
                                </c:when>

                                <c:otherwise>
                                    <td>
                                        <form:input readonly="true" path="accountancyType" cssClass="readonly_style"/>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>


                        <tr>
                            <c:choose>
                                <c:when test="${isAdmin}">
                                    <td><form:label path="amortizacia"><spring:message text="Коэффициент амортизации"/></form:label></td>
                                    <td><form:input type="number" step="0.1" path="amortizacia"/></td>
                                </c:when>
                                <c:otherwise>
                                    <form:hidden path="amortizacia"/>
                                </c:otherwise>
                            </c:choose>
                        </tr>



                        <tr>
                            <td><form:label path="currentAuto"><spring:message text="Текущий автомобиль"/></form:label></td>
                            <td>
                                <form:select path="currentAuto.id">
                                    <c:forEach items="${userAutos}" var="userAuto">
                                        <form:option label="${userAuto.brand}" value="${userAuto.id}"/>
                                    </c:forEach>
                                </form:select>
                            </td>
                        </tr>
                    </c:if>


                    <tr>
                        <td><form:label path="vuFileName"><spring:message text="Водительское удостоверение"/></form:label></td>
                        <td>
                            <a id="load_button" href="<c:url value="/load/vu/${user.id}"/>">Загрузить</a>
                            <a id="look_button" href="<c:url value="/look/vu/${user.id}"/>" target="_blank" class="${user.classColor}">Посмотреть</a>
                        </td>
                    </tr>


                    <tr>
                        <td><form:label path="vuNumber"><spring:message text="Номер ВУ"/></form:label></td>
                        <td><form:input path="vuNumber"/></td>
                    </tr>

                    <tr>
                        <td><form:label path="vuCat"><spring:message text="Категория ВУ"/></form:label></td>
                        <td><form:input path="vuCat"/></td>
                    </tr>


                    <tr>
                        <td colspan="2"><a id="save_button" href="<c:url value="/addAuto/${user.id}"/>">Добавить автомобиль</a></td>
                    </tr>


                    <tr>
                        <td colspan="2"><a id="save_button" href="<c:url value="/selectItem/menuOfUsersAuto/${user.id}"/>">Автомобили</a></td>
                    </tr>


                    <tr>
                        <td colspan="2"><a id="save_button" href="<c:url value="/changePassword/${user.id}"/>">Изменить пароль</a></td>
                    </tr>

                    <tr>
                        <c:if test="${isAdmin || isManager || isBuhgalter}">
                            <td colspan="2"><a id="save_button" href="<c:url value="/lookChangesLog/${user.id}"/>">Посмотреть историю изменений данных</a></td>
                        </c:if>

                    </tr>


                    <tr>
                        <td></td>
                    </tr>

                    <c:if test="${!isAdmin}">
                        <tr>
                            <td></td>
                            <td></td>
                        </tr>
                    </c:if>
                </table>

            <div id="save_edit_user">
                <input id="submit_button" type="submit" value="<spring:message text="Сохранить"/>"/>
            </div>

        </form:form>

            </div>



            <div class="stolb_last">
                <c:if test="${(auto != null)}">
                    <c:url var="saveAutoAction" value=""/>
                    <form:form commandName="auto">

                    <div class="mini_logo">Данные автомобиля</div>



                    <table class="create_table" id="edit_form">
                        <tr>
                            <td><form:label path="brand"><spring:message text="Марка"/></form:label></td>
                            <td><form:input path="brand" readonly="true" cssClass="readonly_style"/></td>
                        </tr>


                        <tr>
                            <td><form:label path="number"><spring:message text="Номер"/></form:label></td>
                            <td><form:input path="number" readonly="true" cssClass="readonly_style"/></td>
                        </tr>

                        <tr>
                            <td><form:label path="year"><spring:message text="Год выпуска"/></form:label></td>
                            <td><form:input type="number" readonly="true" path="year" cssClass="readonly_style"/></td>
                        </tr>

                        <tr>
                            <td><form:label path="bodyType"><spring:message text="Тип кузова"/></form:label></td>
                            <td><form:input path="bodyType" readonly="true" cssClass="readonly_style"/> </td>
                        </tr>

                        <tr>
                            <td><form:label path="engineVolume"><spring:message text="Объем двигателя"/></form:label></td>
                            <td><form:input type="number" step="0.01" path="engineVolume" readonly="true" cssClass="readonly_style"/></td>
                        </tr>

                        <tr>
                            <td><form:label path="enginePower"><spring:message text="Мощность двигателя (л.с.)"/></form:label></td>
                            <td><form:input path="enginePower" readonly="true" cssClass="readonly_style"/></td>
                        </tr>

                        <tr>
                            <td><form:label path="transmission"><spring:message text="Тип коробки (авто/мех)"/></form:label></td>
                            <td>
                                <form:input path="transmission" readonly="true" cssClass="readonly_style"/>
                            </td>
                        </tr>

                        <tr>
                            <td><form:label path="fuelType"><spring:message text="Тип топлива"/></form:label></td>
                            <td>
                                <form:input path="fuelType" readonly="true" cssClass="readonly_style"/>
                            </td>
                        </tr>

                        <tr>
                            <td><form:label path="climateMachine"><spring:message text="Тип климатической установки"/></form:label></td>
                            <td>
                                <form:input path="climateMachine" readonly="true" cssClass="readonly_style"/>
                            </td>
                        </tr>





                    <tr>
                            <td><form:label path="linkNorm"><spring:message text="Норматив смешанного расхода"/></form:label></td>

                     <td>
                         <form:input path="linkNorm" readonly="true" cssClass="readonly_style"/>
                     </td>
                        </tr>


                        <tr>
                            <td><form:label path="link"><spring:message text="Ссылка на источник (auto.ru)"/></form:label></td>

                            <td>
                                <form:input path="link" readonly="true" cssClass="readonly_style"/>
                            </td>
                        </tr>



                        <tr>
                            <td><form:label path="summerNorm"><spring:message text="Норма расхода (лето)"/></form:label></td>
                            <td><form:input readonly="true" path="summerNorm" cssClass="readonly_style"/></td>
                        </tr>

                        <tr>
                            <td><form:label path="winterNorm"><spring:message text="Норма расхода (зима)"/></form:label></td>
                            <td><form:input readonly="true" path="winterNorm" cssClass="readonly_style"/></td>
                        </tr>


                        <tr>
                            <td><form:label path="stsFileName"><spring:message text="СТС"/></form:label></td>
                            <td>
                                <%--<a id="load_button" href="<c:url value="/load/sts/${user.id}"/>">Загрузить</a>--%>
                                <a id="look_button" href="<c:url value="/look/sts/${auto.id}"/>" target="_blank">Посмотреть</a>
                            </td>
                        </tr>


                        <tr>
                            <td><form:label path="osagoFileName"><spring:message text="ОСАГО"/></form:label></td>
                            <td>
                                <%--<a id="load_button" href="<c:url value="/load/osago/${user.id}"/>">Загрузить</a>--%>
                                <a id="look_button" href="<c:url value="/look/osago/${auto.id}"/>" target="_blank" class="${auto.classColor}">Посмотреть</a>
                            </td>
                        </tr>

                    </table>
                    </form:form>

                </c:if>
            </div>

        </div>

</div>
</body>
</html>
