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
<title>TESTS SQL JSP</title>
</head>
<body>
Test base de données
<!--items indique la collection sur laquelle on boucle
var élément parcourue de la collection 
varStatus définit un ensemble de propriétés définissant l'état courant d'une itération :
count   Compteur d'itérations (commence à 1).
-->

  <c:forEach items="${ messages }" var="message" varStatus="boucle">
            <p>${ boucle.count }. ${ message }</p>
        </c:forEach>

</body>
</html>