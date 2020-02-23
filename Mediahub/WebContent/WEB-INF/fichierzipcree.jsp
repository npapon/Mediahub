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
      

Fichier créé : ${fichierzipcree.nom}      
<ul>
<c:forEach items="${erreurscreationzip}" var="entry">
<li>${entry}</li>
</c:forEach>
</ul>
</body>
</html>