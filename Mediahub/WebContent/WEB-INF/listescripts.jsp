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
<title>LISTE SCRIPTS</title>
</head>
<body>
		<form method="post">
				<table id="table">
						<caption id="tablebis">Liste des scripts</caption>
						<br />
						<br />
						<tr>
								<th>INDEX</th>
								<th>CODE</th>
								<th>SCRIPT</th>
								<th>SUPPRIMER</th>
						</tr>
						<c:forEach var="entry" items="${session.listeScripts}" varStatus="status">
								<tr id="supprimerligne">
										<td id="${status.index}">${status.index}</td>
										<td>${entry.key}</td>
										<td>${entry.value.libelle}</td>
										<td><button id="${entry.key}" type='button' class="supprimer">
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
</body>
</html>