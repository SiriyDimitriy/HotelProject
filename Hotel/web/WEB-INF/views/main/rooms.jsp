<%@page pageEncoding="UTF-8"%>

<c:set var="roomClass" value='<%= request.getAttribute("class")%>'/>
<c:forEach var="item" items="${roomClass}">
    <div class="row">
        <h3><%=rb.getString("ROOMS")%> <c:out value="${item.name}"/></h3>
        <div class="col-lg-9 col-md-10">
            <a href="foto/${item.foto}_large.png" class="fancybox">
                <img src="foto/${item.foto}.png" alt="foto"/>
            </a>
        </div>
        <div class="col-lg-14 col-lg-offset-0 col-md-13 col-md-offset-1">
            <p class="text-left"><c:out value="${item.description}"/></p>
        </div>
    </div><br>
</c:forEach>
<script src="js/fancyboxInit.js"></script>

