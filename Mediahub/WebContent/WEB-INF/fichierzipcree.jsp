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
<title>FICHIER ZIP CREE</title>

</head>
<body>
<c:if test="${!empty session}">
Vous êtes déjà connecté
</c:if>
lol
      
${fichierzipcree}      

<script src="<c:url value='/js/connexion.js'/>" type="text/javascript"></script>
</body>
</html>