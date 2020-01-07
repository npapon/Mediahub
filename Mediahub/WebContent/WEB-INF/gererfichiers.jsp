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
                <table id="table">
                        <caption id="tablebis">Images de profil</caption>
                        <br />
                        <br />
                        <tr>
                                <th>INDEX</th>
                                <th>LIBELLE</th>
                                <th>IMAGE</th>
                                <th>SUPPRIMER</th>
                        </tr>
       
                        <c:forEach var="entry" items="${imagesprofil}" varStatus="status">
                                <tr id="supprimerligne">
                                        <td id="${status.index}">${status.index}</td>
                                        <td><c:out value="${entry.libelle}"/></td>
                                        <td><a href="<c:out value='${entry.emplacement}'/>"><img class ="imageprofil" src="<c:out value='${entry.emplacement}'/>" title="<c:out value="${entry.libelle}"/>" alt="<c:out value="${entry.libelle}"/>"></a></td>
                                        <td class="supprimer"><button id="${entry}" type='button' >
                                                        <img src="<c:out value='img/supprimer.png'/>" title="supprimer" alt="supprimer">
                                                </button></td>
                                </tr>
                        </c:forEach>
                </table>
                 <input type="text" id="scriptsasupprimer" name="scriptsasupprimer" value="" size="20" maxlength="60" />
                <br /> <br /> <input type="submit" value="Valider suppression" class="sansLabel" id="submit" /> 
                
                <a href="<c:out value='${listescriptsvue}'/>"><input type="button" value="Annuler suppresion" id="annulersuppression" /></a>
        </form>
        <script src="<c:url value='/js/listescripts.js'/>" type="text/javascript"></script>

</table>
</body>
</html>