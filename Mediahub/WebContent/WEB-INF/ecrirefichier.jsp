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
<title>ECRITURE FICHIER</title>
</head>
<body>
<p>Fichier Lu :</p>
Fichier : ${fichierlu.nom}
<br/>
Emplacement : ${fichierlu.chemin}
<br/>
Contenu :
<br/>
  <textarea id="fichier" readonly="readonly">${fichierlu.contenu}</textarea>
<br/>
<form method="post">
<input type="submit" value="Copier le fichier" class="sansLabel" id="submit" /> 
</form>
<br/>
<p>Fichier Ecri :</p>
Fichier : ${fichierecri.nom}
<br/>
Emplacement : ${fichierecri.chemin}
<br/>
Contenu :
<br/>
  <textarea id="fichier" readonly="readonly">${fichierecri.contenu}</textarea>
  
  
</body>
</html>