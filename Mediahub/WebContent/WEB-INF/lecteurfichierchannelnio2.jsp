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
<title>LECTURE FICHIER AVEC CHANNEL NIO2</title>
</head>
<body>
Fichier : ${fichierlu.nom}
<br/>
Emplacement : ${fichierlu.chemin}
<br/>
Contenu :
<br/>
  <textarea id="fichier" readonly="readonly">${fichierlu.contenu}</textarea>
</body>
</html>