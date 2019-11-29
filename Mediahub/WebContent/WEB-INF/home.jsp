<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
</head>
<body>
<c:if test="${!empty sessionScope.session}">
Bienvenue ${sessionScope.session.login}
(Connexion réussie le : ${sessionScope.session.dateDeConnexion})
</c:if>
</body>
</html>