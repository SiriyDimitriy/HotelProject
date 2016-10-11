<%@page import="java.util.List"%>

<%@ page pageEncoding="UTF-8" %>

<c:set var="orders" value='<%= request.getAttribute("orders")%>'/>

<table class="table table-bordered">
    <thead>
        <tr>
            <td align="center"><br><b>№</b></td>
            <td align="center"><br><b>Пользователь</b></td>
            <td align="center"><b>Дата заявки</b></td>
            <td align="center"><b>Дата вьезда</b></td>
            <td align="center"><b>Дата выезда</b></td>
            <td align="center"><b>Кол-во мест</b></td>
            <td align="center"><b>Тип <br>номера</b></td>
            <td align="center"><br><b>Комментарий</b></td>
            <td align="center"><b>№ номера</b></td>
            <td align="center"><br><b>Стоимость</b></td>
            <td></td>
            <td></td>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${orders}">
            <tr>
                <c:set var="name" value='${item.useremail.name}'/>
                <c:set var="email" value='${item.useremail.email}'/>
                <c:set var="tel" value='${item.useremail.telephone}'/>

                <td align="center"><c:out value="${item.id}"/></td>
                <td id='cursor' myemail = '${email}' mytel = '${tel}' myname = '${name}' class='user'><c:out value="${item.useremail.name}"/></td>
                <td><c:out value="${item.orderdate}"/></td>
                <td><c:out value="${item.startdate}"/></td>
                <td><c:out value="${item.enddate}"/></td>

                <td align="center"><c:out value="${item.places}"/></td>
                <td><c:out value="${item.class1}"/></td>
                <td><c:out value="${item.usercomment}"/></td>
                <td align="center">

                    <select  id = "sel${item.id}">
                        <option value="0"><c:out value="${item.roomnumber}"/></option>
                        <c:forEach var="room" items="${item.roomslist}">
                            <option value="${room.number}"><c:out value="${room.number}"/></option>
                        </c:forEach>
                    </select>
                </td>
                <td align="center" id="cost" class="cost"><c:out value="${item.cost}"/></td>
                <td><button type="button" class="btn btn-success update" upd="${item.id}">Применить</button></td>
                <td><button type="button" class="btn btn-danger delete" del="${item.id}">Удалить</button></td>
            </tr>
        </c:forEach>
    <form action="adminorders" method="POST"><button style="visibility: hidden;" type="submit" class="reload"></button></form>
</tbody>
</table>
<script src="js/adminorder_scripts.js"></script>


