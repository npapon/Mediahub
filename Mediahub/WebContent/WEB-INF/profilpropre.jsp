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
<title>EDITER SON PROFIL</title>
</head>
<body>
<img class ="imageprofil" src="<c:out value='${imageprofil.chemin}'/>"  title="profil" alt="Votre photo"/>


<form method="post" enctype="multipart/form-data">

 <fieldset>
                <legend>Charger sa photo</legend>
        

                <label for="fichierimage">Charger la photo <span class="requis">*</span></label>
                <input type="file" id="fichierimage" name="fichierimage" />
                 <span class="succes"><c:out value="${imageprofil.nom}"/></span>
                 <div class="erreur" id="erreurfichierimage"></div>
                <br />
                
                <input id ="submit" type="submit" value="Envoyer" class="sansLabel" />
                <br />

</fieldset>
</form>
   <script src="<c:url value='/js/profilpropre.js'/>" type="text/javascript"></script>
</body>
</html>