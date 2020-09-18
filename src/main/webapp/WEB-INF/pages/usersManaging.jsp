
<%@ page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>



<html>
<head>
    <meta charset="UTF-8">
    <c:choose>
        <c:when test="${usersType eq 'drivers'}">
            <title>Управление водителями</title>
        </c:when>

        <c:otherwise>
            <title>Управление пользователями</title>
        </c:otherwise>
    </c:choose>

    <style>
        <%@include file="../../resources/style.css" %>
    </style>
</head>

<body>

<header>
    <div class="container">
        <c:choose>
            <c:when test="${usersType eq 'drivers'}">
                <div>Управление водителями</div>
            </c:when>
            <c:otherwise>
                <div>Управление пользователями</div>
            </c:otherwise>
        </c:choose>

        <c:forEach items="${topMenu}" var="btn">
            <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
        </c:forEach>
        <c:if test="${isAdmin}">
            <div><a href="<c:url value="/registration"/>">Добавить пользователя</a></div>
        </c:if>
    </div>
</header>





<div class="top">

    <c:if test="${text != null}">
        <script type="text/javascript">alert('${text}');</script>
    </c:if>

    <c:if test="${message != null}">
        <div> Сообщение: ${message}</div>
    </c:if>

    <c:if test="${error !=null}">
        <div> Ошибка: ${error}</div>
    </c:if>



        <table class="small_table" id="users">
        <c:if test="${!empty usersList}">
            <tr>

                <th>login</th>
                <th class="grey"><a href="<c:url value="/usersManaging/sortBy/surname?usersType=${usersType}"/>">Фамилия</a></th>
                <th>Имя</th>
                <th>Отчество</th>

                <c:choose>
                    <c:when test="${usersType eq 'drivers'}">
                        <th>Марка машины</th>
                        <th>Номер машины</th>
                        <th class="grey"><a href="<c:url value="/usersManaging/sortBy/summerNorm?usersType=${usersType}"/>">Расход летом</a></th>
                        <th class="grey"><a href="<c:url value="/usersManaging/sortBy/winterNorm?usersType=${usersType}"/>">Расход зимой</a></th>
                        <th class="grey"><a href="<c:url value="/usersManaging/sortBy/year?usersType=${usersType}"/>">Год выпуска</a></th>
                        <th class="grey"><a href="<c:url value="/usersManaging/sortBy/engineVolume?usersType=${usersType}"/>">Объем двигателя</a></th>
                        <th class="grey"><a href="<c:url value="/usersManaging/sortBy/transmission?usersType=${usersType}"/>">Трансмиссия</a></th>
                        <th class="grey"><a href="<c:url value="/usersManaging/sortBy/bodyType?usersType=${usersType}"/>">Тип кузова</a></th>
                        <th class="grey"><a href="<c:url value="/usersManaging/sortBy/climateMachine?usersType=${usersType}"/>">Тип климатический установки</a></th>
                        <th class="grey"><a href="<c:url value="/usersManaging/sortBy/enginePower?usersType=${usersType}"/>">Мощность двигателя (л.с.)</a></th>
                        <th class="grey"><a href="<c:url value="/usersManaging/sortBy/fuelType?usersType=${usersType}"/>">Тип топлива</a></th>
                        <%--<th class="grey"><a href="<c:url value="/usersManaging/sortBy/accountancyType?usersType=${usersType}"/>">Тип отчетности</a></th>--%>
                        <th class="grey"><a href="<c:url value="/usersManaging/sortBy/amortizacia?usersType=${usersType}"/>">Коэфф. аморт.</a></th>
                        <c:if test="${isAdmin || isManager || isFinDir || isBuhgalter}">
                            <th class="grey"><a href="<c:url value="/usersManaging/sortBy/buhgalteria?usersType=${usersType}"/>">Бухгалтерия/Простой</a></th>
                        </c:if>
                    </c:when>


                    <c:otherwise>

                        <c:if test="${usersType eq 'other'}">
                            <th>Должность</th>
                            <th>e-mail</th>
                        </c:if>

                        <c:if test="${usersType eq 'all' && isAdmin}">
                            <th></th>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </tr>



            <c:forEach items="${usersList}" var="user">
                <tr class="${user.firedColor}">


                    <c:choose>
                        <c:when test="${isFinDir}">
                            <td>${user.login}</td>
                        </c:when>
                        <c:otherwise>
                            <td class="grey"><a href="<c:url value="/usersManaging/editUser/${user.id}"/>">${user.login}</a></td>
                        </c:otherwise>
                    </c:choose>


                    <c:choose>
                        <c:when test="${usersType eq 'drivers'}">
                            <td class="poster">${user.surname}
                                <div class="descr">${user.post}</div>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>${user.surname}</td>
                        </c:otherwise>
                    </c:choose>
                            <td>${user.name}</td>
                            <td>${user.patronymic}</td>


                    <c:choose>
                        <c:when test="${usersType eq 'drivers'}">
                            <td class="poster">${user.currentAuto.brand}
                                <div class="descr">
                                    Транспондер № ${user.transponder}, <br>
                                    e-mail: ${user.eMail}
                                </div>
                            </td>

                            <td>${user.currentAuto.number}</td>
                            <td>${user.currentAuto.summerNorm}</td>
                            <td>${user.currentAuto.winterNorm}</td>
                            <td>${user.currentAuto.year}</td>
                            <td>${user.currentAuto.engineVolume}</td>
                            <td>${user.currentAuto.transmission}</td>
                            <td>${user.currentAuto.bodyType}</td>
                            <td>${user.currentAuto.climateMachine}</td>
                            <td>${user.currentAuto.enginePower}</td>
                            <td>${user.currentAuto.fuelType}</td>
                            <td>${user.amortizacia}</td>







                            <c:if test="${isAdmin || isManager || isFinDir || isBuhgalter}">
                                <c:choose>
                                        <c:when test="${isAdmin || isFinDir}">
                                            <td class="grey"><a href="<c:url value="/usersManaging/setBuhgalteria/${user.id}"/>">
                                                <c:choose>
                                                    <c:when test="${user.buhgalteria}">
                                                        Бухгалтерия
                                                    </c:when>
                                                    <c:otherwise>
                                                        Простой
                                                    </c:otherwise>
                                                </c:choose>
                                            </a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                            <c:choose>
                                                <c:when test="${user.buhgalteria}">Бухгалтерия</c:when>
                                                <c:otherwise>Простой</c:otherwise>
                                            </c:choose>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>





                            </c:if>


                            <c:choose>
                                <c:when test="${user.currentAuto.stsFileName eq null || empty user.currentAuto.stsFileName}"><td></td></c:when>
                                <c:otherwise><td class="grey">
                                    <a href="<c:url value="/look/sts/${user.currentAuto.id}"/>" target="_blank">СТС</a>
                                </td></c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${user.currentAuto.osagoFileName eq null || empty user.currentAuto.osagoFileName}"><td></td></c:when>
                                <c:otherwise>
                                    <td class="${user.currentAuto.classColor}">
                                    <a href="<c:url value="/look/osago/${user.currentAuto.id}"/>" target="_blank" class="${user.currentAuto.classColor}">ОСАГО</a>
                                </td></c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${user.vuFileName eq null || empty user.vuFileName}"><td></td></c:when>
                                <c:otherwise>
                                    <td class="${user.classColor}">
                                    <a href="<c:url value="/look/vu/${user.id}"/>" target="_blank" class="${user.classColor}">ВУ</a>
                                </td></c:otherwise>
                            </c:choose>


                            <c:if test="${isAdmin || isFinDir}">
                                <td class="grey"><a href="<c:url value="/usersManaging/blockUser/${user.id}"/>">
                                    <c:choose>
                                        <c:when test="${user.blocked}">
                                            Разблокировать
                                        </c:when>
                                        <c:otherwise>
                                            Заблокировать
                                        </c:otherwise>
                                    </c:choose>
                                </a></td>
                            </c:if>

                            <c:if test="${isAdmin || isFinDir}">
                                <td class="grey"><a href="<c:url value="/usersManaging/fireUser/${user.id}"/>">
                                    <c:choose>
                                        <c:when test="${!user.fired}">
                                            Уволить
                                        </c:when>
                                        <c:otherwise>
                                            <%--Заблокировать--%>
                                        </c:otherwise>
                                    </c:choose>
                                </a></td>
                            </c:if>



                            <c:if test="${isAdmin}">
                                <c:if test="${user.login eq 'shokin' || user.login eq 'GrigorievY'}">
                                    <td class="grey"><a href="<c:url value="/usersManaging/createAutoReport/${user.id}/0/0"/>">Сген. отчет</a></td>
                                </c:if>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${usersType eq 'other'}">
                                <td>${user.post}</td>
                                <td>${user.eMail}</td>
                            </c:if>

                            <c:if test="${usersType eq 'all' && isAdmin}">
                                <td class="grey"><a href="<c:url value="/usersManaging/delUser/${user.id}"/>">Удалить</a></td>

                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </c:if>
        </table>
</div>
</body>
</html>
