<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="error" value='<%= request.getAttribute("auth")%>'/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Вход Администратора</title>
        <script src="plugins/jquery.js"></script>
        <script src="plugins/jquery-ui.js"></script>
        <script src="plugins/bootstrap.js"></script>
        <link href="plugins/jquery-ui.css" rel="stylesheet">
        <link href="plugins/jquery-ui.structure.css" rel="stylesheet">
        <link href="plugins/jquery-ui.theme.css" rel="stylesheet">
        <link href="plugins/bootstrap-responsive.css" rel="stylesheet">
        <link href="plugins/signin.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="check" method="POST">
                <h2 class="form-signin-heading">Вход Администратора:</h2>
                <input type="text" name="login" class="input-block-level" placeholder="Логин">
                <input type="password" name="password" class="input-block-level" placeholder="Пароль">
                <button class="btn btn-large btn-primary" type="submit" name="submitted">Вход</button><br><br>
                <c:if test = '${error == "wrong"}'>
                    <span class="alert_message">Проверьте корректность Логина и Пароля.</span>
                </c:if>
            </form>
        </div>
    </body>
</html>
