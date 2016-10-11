<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="css/popup_user_orders.css" />

<c:set var="users" value='<%= request.getAttribute("users")%>'/>

<div class="row">
    <div class="col-lg-12 col-lg-offset-6">
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>E-mail</th>
                    <th>Имя и фамилия</th>
                    <th>Телефон</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${users}">
                    <c:set var="email" value="${item.email}"/>
                    <tr class='popup-link-1 ' id="${email}">
                        <td><c:out value="${item.email}"/></td>
                        <td><c:out value="${item.name}"/></td>
                        <td><c:out value="${item.telephone}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-lg-6"></div>
</div>
<script src="js/adminusers_scripts.js"></script>