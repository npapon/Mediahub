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
<title>GESTIONNAIRE DE ZIP</title>
</head>
<body>
    <form method="post">
    <fieldset>
    <legend>Créer le fichier zip</legend>
     <label for="creerzip">Entre le nom du fichier zip à créer <span class="requis">*</span></label>
     <input type="text" id="creerzip" name="creerzip" placeholder="ne pas préciser l'extension" size="20" maxlength="60"/>
     <div class="erreur" id="erreurcreationzip"></div>
     
</fieldset>
                <table>
               
                        <caption>Fichiers Text à ranger dans le zip
                        <br/>
                        dossier :  ${dossierrangementfichierstext}
                        </caption>
                        <br />
                        <br />  
                    
                        <tr>
                                <th>INDEX</th>
                                <th>LIBELLE</th>
                                <th>FICHIER</th>
                                <th>SELECTION</th>
                        </tr>
                        
       
                        <c:forEach var="entry" items="${fichierstext}" varStatus="status">
                                <tr id="supprimerligne">
                                        <td id="${status.index}">${status.index}</td>
                                        <td><c:out value="${entry.pathNomFichier}"/></td>
                                        <td>
                                        <a href="<c:out value='${entry.path}'/>">
                                  
                                        </a>
                                        </td>
                                        <td><button id="${entry.pathNomFichier}" type='button' class="supprimer">
                                                        <img src="<c:out value='img/supprimer.png'/>" title="supprimer" alt="supprimer">
                                                </button></td>
                                </tr>
                        </c:forEach>
                </table>
                 <input type="text" id="elementsasupprimer" name="elementsasupprimer" value="" size="20" />
                <br /> <br /> <input type="submit" value="Creer le Zip" class="sansLabel" id="submit" /> 
                
                <a href="<c:out value='${gererfichiersvue}'/>"><input type="button" value="Annuler" id="annuler" /></a>
        </form>
</table>
   <script src="<c:url value='/js/gererfichiers.js'/>" type="text/javascript"></script>
</body>
</html>