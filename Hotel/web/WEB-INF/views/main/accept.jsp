<%@page pageEncoding="UTF-8"%>

<c:set var="errors" value='<%= request.getAttribute("errors")%>'/>

<p class="text-left">

<c:if test = '${errors != "no"}'>
    <h3><%=rb.getString("ACCEPT ERROR0")%></h3><br>
</c:if>

<c:if test = '${errors.contains("nameError")}'>
    <%=rb.getString("ACCEPT ERROR1")%><br><br>
</c:if>
    
<c:if test = '${errors.contains("emailError")}'>
    <%=rb.getString("ACCEPT ERROR2")%><br><br>
</c:if>
    
<c:if test = '${errors.contains("dateError")}'>
    <%=rb.getString("ACCEPT ERROR3")%><br><br>
</c:if>
    
<c:if test = '${errors.contains("telephoneError")}'>
    <%=rb.getString("ACCEPT ERROR4")%><br><br>
</c:if>
    
<c:if test = '${errors.contains("dateOrderError")}'>
    <%=rb.getString("ACCEPT ERROR5")%><br><br>
</c:if>
    
<c:if test = '${errors == "no"}'>
    <%=rb.getString("ACCEPT ERROR NO")%><br>
</c:if> 

</p>
