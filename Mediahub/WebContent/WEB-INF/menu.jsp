<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MENU</title>
</head>
<body>
<div id="menu">
<ul>
<div id="corps">
		<c:forEach var="entry" items="${menu}">
	<a href="<c:url value="${entry.value}"/>" title="<c:out value="${entry.key}"/>"><c:out value="${entry.value}"/></a>
		<br/>
		</c:forEach>
</div>
</ul>
</div>
</body>
</html>