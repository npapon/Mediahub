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
<title>CONNEXION</title>

</head>
<body>
<c:if test="${!empty session}">
Vous �tes d�j� connect�
</c:if>
<c:if test="${empty session}">
        <form method="post">
        
            <fieldset>
                <legend>Entrez vos identifiants de connexion</legend>
                <br/>
               
                <label for="login">Login <span class="requis">*</span></label>
                <input type="text" id="login" name="login" value="<c:out value='${cookielogin.value}'/>" size="20" maxlength="60" />
                <div class="erreur" id="erreurlogin"></div>
                <br />
                <br />
                
                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="<c:out value='${cookiemotdepasse.value}'/>" size="20" maxlength="20" />
                 <div class="erreur" id="erreurmotdepasse"></div>      
                <br />
                <br />
                
                <input type="submit" value="Connexion" class="sansLabel" id="submit" /> 
                <input type="reset" value="Effacer saisie"  id="reset"/>          
                <br />
                            
            </fieldset>
        </form>        
</c:if>
<script src="<c:url value='/js/connexion.js'/>" type="text/javascript"></script>
</body>
</html>