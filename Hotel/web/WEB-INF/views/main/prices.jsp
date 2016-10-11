<%@page pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-14 col-lg-offset-5">
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <td align="center"><b><%=rb.getString("ORDER CLASS")%></b></td>
                    <td align="center"><b><%=rb.getString("ORDER PLACES")%></b></td>
                    <td align="center"><b><%=rb.getString("PRICE")%></b></td>
                </tr>
            </thead>
            <tbody>
                <c:set var="room" value='<%= request.getAttribute("room")%>'/>
                <c:forEach var="item" items="${room}">
                    <tr>
                        <td align="center"><c:out value="${item.class1.name}"/></td>
                        <td align="center"><c:out value="${item.places}"/></td>
                        <td align="center"><c:out value="${item.price}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>  
    <div class="col-lg-5"></div>       
</div>
