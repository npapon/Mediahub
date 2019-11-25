<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PROFIL</title>
</head>
<body>
<table>
<tr><td>Login : </td><td><c:out value="${session.login}"/></td>
<tr><td>Email : </td><td><c:out value="${cookieEmail}"/></td>
</table>

</body>
</html>