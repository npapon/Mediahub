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
<title>CREER SCRIPT</title>
</head>
<body>
		<form method="post">

				<fieldset>
						<legend>Action</legend>
						Créer/Modifier un script
						<p>
								<input type="radio" name="actionscript" value="creer" id="creer" checked /><label for="creer">Creer</label><br /> 
								<input type="radio" name="actionscript" value="modifier"
										id="modifier" /><label for="modifier">Modifier</label><br />
						</p>

				</fieldset>
				<div id="creerscript">
						<fieldset>
								<legend>Créer un nouveau script</legend>
								<br /> <label for="codescript">Code : <span class="requis">*</span></label> <input type="text" id="codescript" name="codescript" value="" size="20" maxlength="60" />
								<div class="erreur" id="erreurcodescript"></div>
								<br /> <br /> <label for="libellescript">Libellé : <span class="requis">*</span></label>
								 <input type="text" id="libellescript" name="libellescript" value=""
										size="20" maxlength="20" />
								<div class="erreur" id="erreurlibellescript"></div>
								<br /> <br /> <input type="submit" value="Créer le script" class="sansLabel" id="submit" /> <input type="reset" value="Effacer saisie" id="reset" /> <br />

						</fieldset>
		
		</div>

		<div id="modifierscript">
				<fieldset>
						<legend>Modifier un script</legend>
						<label for="codescriptamodifier">Sélectionnez un script :</label> </br> </br> 
						<select name="codescriptamodifier" id="codescriptamodifier">
						<option selected="selected"></option>
								<c:forEach var="entry" items="${session.listeScripts}">
										<option name="codescriptamodifieroption" id="codescriptamodifieroption" value="<c:out value="${entry.key}"/>"><c:out value="${entry.value.libelle}" /></option>
								</c:forEach>
						</select> 
				
                                <br/>
                                <label for="libellescriptamodifier">Libellé : <span class="requis">*</span></label> 
                                <input type="text" id="libellescriptamodifier" name="libellescriptamodifier" value=""
                                        size="20" maxlength="20" />
                                <div class="erreur" id="erreurlibellescript"></div>
						<br /> <br /> <input type="submit" value="Modifier le script" class="sansLabel" id="submit" /> <input type="reset" value="Effacer saisie" id="reset" />
				</fieldset>

		</div>
		<script src="<c:url value='/js/creerscript.js'/>" type="text/javascript"></script>
		</form>
</body>
</html>