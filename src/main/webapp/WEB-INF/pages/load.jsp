
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>



<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
    $(function() { $( ".datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' }); });
</script>


<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <style>
        <%@include file="../../resources/style.css" %>
    </style>

</head>


<body>

<header>
    <div class="container">
        <div>${title}</div>
        <c:forEach items="${topMenu}" var="btn">
            <div><a href="<c:url value="${btn.value}"/>">${btn.key}</a></div>
        </c:forEach>
    </div>
</header>



<div class="top">
    <c:if test="${text != null}">
        <script type="text/javascript">alert('${text}');</script>
    </c:if>

    <div class="menu">
            <form:form action="${urlValue}/${object.id}?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" commandName="object">
            <input type="hidden" name="form" value=${formName}/>

            <%--<div class="menu">--%>
                <div class="file_button">
                    Выбрать файл
                    <input type="file" name="file" class="file_button_hidden" accept="image/*">
                </div>

                <c:if test="${date}">

                        <input name="date" placeholder="Дата окончания" id="inputDate" type="date" class="datepicker"/>


                </c:if>

                <c:if test="${!date}">
                    <input hidden name="date">
                </c:if>

                <input id="file_button" type="submit" value="Загрузить">
        </form:form>
    </div>
</div>
</body>
</html>
