
var buttonsSupprimer = document.getElementsByTagName('button');
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

