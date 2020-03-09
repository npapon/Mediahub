
var buttonsSupprimer = document.getElementsByName('supprimer');
var buttonsTelecharger = document.getElementsByName('telechargerimage');


var elementsasupprimerTableau =[];
var elementsasupprimerInput = document.getElementById("elementsasupprimer");
elementsasupprimerInput.style.display = "none";





for (var i = 0, c = buttonsSupprimer.length; i < c; i++)

{
	buttonsSupprimer[i].addEventListener("click", function(e) {

	e.target.parentNode.parentNode.parentNode.removeChild(e.target.parentNode.parentNode);
	elementsasupprimerTableau.push(e.target.id);
	elementsasupprimerInput.value=elementsasupprimerTableau;
	
		
	})
}




for (var i = 0, c = buttonsTelecharger.length; i < c; i++)

{
	buttonsTelecharger[i].addEventListener("click", function(e) {

		

e.target.parentNode.href="images/" +e.target.id;


	
		
	})
}


