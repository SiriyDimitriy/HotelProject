<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="rooms" value='<%= request.getAttribute("rooms")%>'/>

<div class="row">
    <div class="col-lg-12 col-lg-offset-6">
        <table class="table table-striped table-bordered ">
            <thead>
                <tr>
                    <th>№</th>
                    <th>Мест</th>
                    <th>Тип </th>
                    <th>Комментарий</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${rooms}">
                    <tr>
                        <td align="center"><c:out value="${item.number}"/></td>
                        <td align="center"><c:out value="${item.places}"/></td>
                        <td><c:out value="${item.class1.name}"/></td>
                        <td id='cursor' class="comment" roomid="${item.number}"><c:out value="${item.comment}"/></td>
                        <td><button type="button" class="btn btn-success update" name="${item.number}">Применить</button></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-lg-6"></div>
</div>
<script src="js/adminrooms_scripts.js"></script>