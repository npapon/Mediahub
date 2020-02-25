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
<title>PROFIL PROPRE</title>
</head>
<body>

<table>
<tr><td>Login : </td><td><c:out value="${cookielogin.value}"/></td>
<tr><td>Email : </td><td><c:out value="${cookieemail.value}"/></td>
</table>
<p>Votre compte a été créé il y a ${ecartdateformatverbeux}</p>

<form method="post" enctype="multipart/form-data">
 <fieldset>
                <legend>Charger sa photo</legend>
                           <label for="nomimage">Nom de la photo de profil </label>
                <input type="text" id="nomimage" name="nomimage" value="" />
                <span class="succes"><c:out value="${image.description}"/></span>
                <div class="erreur" id="erreurnomimage"></div>
                <br />

                <label for="fichierimage">Charger la photo <span class="requis">*</span></label>
                <input type="file" id="fichierimage" name="fichierimage" />
                 <span class="succes"><c:out value="${image.nomfichier}"/></span>
                 <div class="erreur" id="erreurfichierimage"></div>
                <br />
                
                <input id ="submit" type="submit" value="Envoyer" class="sansLabel" />
                <br />

</fieldset>
</form>
</body>
</html>