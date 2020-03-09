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
<title>GERER FICHIERS</title>
</head>
<body>
    <form method="post">
                <table >
               
                        <caption>Images de profil
                        <br/>
                        dossier :  ${dossierrangementimagesprofil}
                        </caption>
                        <br />
                        <br />  
                    
                        <tr>
                                <th>INDEX</th>
                                <th>LIBELLE</th>
                                <th>IMAGE</th>
                                <th>SUPPRIMER</th>
                                 <th>TELECHARGER</th>
                        </tr>
                        
       
                        <c:forEach var="entry" items="${imagesprofil}" varStatus="status">
                                <tr id="supprimerligne">
                                        <td id="${status.index}">${status.index}</td>
                                        <td><c:out value="${entry.libelle}"/></td>
                                        <td>
                                        <a href="<c:out value='${entry.emplacement}'/>">
                                        <img class ="imageprofil" 
                                        src="<c:out value='${entry.emplacement}'/>" 
                                        title="<c:out value="${entry.libelle}"/>" 
                                        alt="<c:out value="${entry.libelle}"/>">
                                        </a>
                                        </td>
                                        <td><button id="${entry.libelle}" type='button' name="supprimer">
                                                        <img src="<c:out value='img/supprimer.png'/>" title="supprimer" alt="supprimer">
                                                </button></td>
                                         
                                                <td>
                                                <a>
                                                <button id="${entry.libelle}" type="button" name="telechargerimage">   
                                                <img src="<c:out value='img/telecharger.png'/>" title="telecharger" alt="telecharger">
                                                </button>
                                                </a>
                                                
                                                </td>
                                </tr>
                        </c:forEach>
                </table>
           
        <br/>
        <br/>
</table>

                <table>
               
                        <caption>Fichiers Text
                        <br/>
                        dossier :  ${dossierrangementfichierstext}
                        </caption>
                        <br />
                        <br />  
                    
                        <tr>
                                <th>INDEX</th>
                                <th>LIBELLE</th>
                                <th>FICHIER</th>
                                <th>SUPPRIMER</th>
                        </tr>
                        
       
                        <c:forEach var="entry" items="${fichierstext}" varStatus="status">
                                <tr id="supprimerligne">
                                        <td id="${status.index}">${status.index}</td>
                                        <td><c:out value="${entry.pathNomFichier}"/></td>
                                        <td>
                                        <a href="<c:out value='${entry.path}'/>">
                                  
                                        </a>
                                        </td>
                                        <td><button id="${entry.pathNomFichier}" type='button' name="supprimer">
                                                        <img src="<c:out value='img/supprimer.png'/>" title="supprimer" alt="supprimer">
                                                </button></td>
                                </tr>
                        </c:forEach>
                </table>
                 <input type="text" id="elementsasupprimer" name="elementsasupprimer" value="" size="20" />
                <br /> <br /> <input type="submit" value="Valider suppression" class="sansLabel" id="submit" /> 
                
                <a href="<c:out value='${gererfichiersvue}'/>"><input type="button" value="Annuler suppression" id="annulersuppression" /></a>
        </form>
</table>
   <script src="<c:url value='/js/gererfichiers.js'/>" type="text/javascript"></script>
</body>
</html>