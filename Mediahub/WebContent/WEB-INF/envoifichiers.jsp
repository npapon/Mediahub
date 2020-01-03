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
<title>ENVOI FICHIERS</title>
</head>
<body>
  <form  method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Envoi de fichier</legend>

                <label for="description">Description du fichier</label>
                <input type="text" id="description" name="description" value="" />
                <span class="succes"><c:out value="${fichier.description}"/></span>
                <div class="erreur" id="erreurdescription"></div>
                <br />

                <label for="fichier">Emplacement du fichier <span class="requis">*</span></label>
                <input type="file" id="fichier" name="fichier" />
                 <span class="succes"><c:out value="${fichier.nom}"/></span>
                 <div class="erreur" id="erreurfichier"></div>
                <br />
                
                <input id ="submit" type="submit" value="Envoyer" class="sansLabel" />
                <br />                
            </fieldset>
        </form>
        <script src="<c:url value='/js/envoifichiers.js'/>" type="text/javascript"></script>
</body>
</html>