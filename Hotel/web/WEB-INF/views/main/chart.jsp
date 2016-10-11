<%@page pageEncoding="UTF-8"%>

<c:set var="orders" value='<%=request.getSession().getAttribute("chart")%>'/>

<c:if test = '${orders == null}'>
    <h3><%=rb.getString("CHART ORDERS")%></h3><br>
</c:if>

<c:if test = '${orders != null}'>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <td align='center'><b><%=rb.getString("ORDER NUMBER")%></b></th>
                <td align='center'><b><%=rb.getString("ORDER DATE")%></b></th>
                <td align='center'><b><%=rb.getString("ORDER START DATE")%></b></th>
                <td align='center'><b><%=rb.getString("ORDER END DATE")%></b></th>
                <td align='center'><b><%=rb.getString("ORDER PLACES")%></b></th>
                <td align='center'><b><%=rb.getString("ORDER CLASS")%></b></th>
                <td align='center'><b><%=rb.getString("ORDER COMMENT")%></b></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${orders}">
            <form action="chart" method="POST">
                <tr>
                    <td align='center'><c:out value="${item.id}"/></td>
                <input type="hidden" name="id" value="${item.id}"/>
                <td align='center'><c:out value="${item.orderdate}"/></td>
                <td align='center'><c:out value="${item.startdate}"/></td>
                <td align='center'><c:out value="${item.enddate}"/></td>
                <td align='center'><c:out value="${item.places}"/></td>
                <td align='center'><c:out value="${item.class1}"/></td>
                <td align='center'><c:out value="${item.usercomment}"/></td>
                <td><button type="submit" class="btn btn-danger" name="deleted"><%=rb.getString("DELETE")%></button></td>
                </tr>
            </form>
        </c:forEach>
    </tbody>
</table>
</c:if>