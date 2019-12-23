<c:if test="${!empty session}">
<c:import url="/deconnexionbouton" var="file" scope="page" />
    ${file}
    </c:if>
<c:import url="/menu" var="file" scope="page" />
    ${file}
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
</head>
<body>
WELCOME !

<br/>
<c:if test="${!empty sessionScope.session}">
Bienvenue ${sessionScope.session.login}
(Connexion réussie le : ${sessionScope.session.dateDeConnexion})
</c:if>
</body>
</html>