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
<title>ADMINISTRATION</title>
</head>
<body>
<ol>
        <c:forEach var="entry" items="${menuadministration}">
   <li> <a href="<c:url value="${entry.value}"/>" title="<c:out value="${entry.key}"/>"><c:out value="${entry.value}"/></a></li>
        <br/>
        </c:forEach>
</ol>
</body>
</html>