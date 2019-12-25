
var listescriptsTable = document.getElementById("table");
var buttonsSupprimer = document.getElementsByTagName('button');
var scriptsasupprimerTableau =[];
var scriptsasupprimerInput = document.getElementById("scriptsasupprimer");

scriptsasupprimerInput.style.display = "none";



for (var i = 0, c = buttonsSupprimer.length; i < c; i++)

{
	buttonsSupprimer[i].addEventListener("click", function(e) {

	e.target.parentNode.parentNode.parentNode.removeChild(e.target.parentNode.parentNode);
	scriptsasupprimerTableau.push(e.target.id);
	scriptsasupprimerInput.value=scriptsasupprimerTableau;
	
		
	})
}

