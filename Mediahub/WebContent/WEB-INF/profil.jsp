<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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