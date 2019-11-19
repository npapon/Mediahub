<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CONNEXION</title>

</head>
<body>
        <form method="post" action="<c:url value='/connexionSession'/>">
        
            <fieldset>
                <legend>Entrez vos identifiants de connexion</legend>
                <br/>
               
                <label for="login">Login <span class="requis">*</span></label>
                <input type="text" id="login" name="login" value="" size="20" maxlength="60" />
                <br />
                <br />
                
                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />      
                <br />
                <br />
                
                <input type="submit" value="Connexion" class="sansLabel" id="submit" /> 
                <input type="reset" value="Effacer saisie"  id="submit"/>          
                <br />
                            
            </fieldset>
        </form>  
</body>
</html>