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
Test base de donn�es
<!--items indique la collection sur laquelle on boucle
var �l�ment parcourue de la collection 
varStatus d�finit un ensemble de propri�t�s d�finissant l'�tat courant d'une it�ration :
count   Compteur d'it�rations (commence � 1).
-->

  <c:forEach items="${ messages }" var="message" varStatus="boucle">
            <p>${ boucle.count }. ${ message }</p>
        </c:forEach>

</body>
</html>