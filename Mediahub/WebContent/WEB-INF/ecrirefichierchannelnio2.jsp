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
<title>ECRITURE ET GENERER FICHIER AVEC CHANNEL NIO2</title>
</head>
<body>
<p>Creer un fichier :</p>
<br/>
<form method="post">
<label  for="nomfichier">Nom Fichier : </label><input type="text" id="nomfichier" name ="nomfichier"/>
 <div class="erreur" id="erreurnomfichier"></div>
</br>
</br>
  <textarea id="texttape" name="texttape"></textarea>
<br/>

<input type="submit" value="Créer le fichier" class="sansLabel" id="submit" /> 
</form>
<br/>
<p>Fichier créé :</p>
Fichier : ${fichiercree.nom}
<br/>
Emplacement : ${fichiercree.chemin}
<br/>
Contenu :
<br/>
  <textarea id="fichier" readonly="readonly">${fichiercree.contenu}</textarea>
  <script src="<c:url value='/js/ecriregenererfichiertext.js'/>" type="text/javascript"></script>
  
</body>
</html>