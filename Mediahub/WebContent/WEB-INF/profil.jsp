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
<title>PROFIL</title>
</head>
<body>
<img src="<c:out value='img/NicolasPapon.jpg'/>"  title="profil" alt="Votre photo"/>
<table>
<tr><td>Login : </td><td><c:out value="${cookielogin.value}"/></td>
<tr><td>Email : </td><td><c:out value="${cookieemail.value}"/></td>
</table>
<p>Votre compte a été créé il y a ${ecartdateformatverbeux}</p>


</body>
</html>