
var imagesTable = document.getElementById("table");
var buttonsSupprimer = document.getElementsByTagName('button');
var imagesasupprimerTableau =[];
var imagesasupprimerInput = document.getElementById("imagesasupprimer");
imagesasupprimerInput.style.display = "none";





for (var i = 0, c = buttonsSupprimer.length; i < c; i++)

{
	buttonsSupprimer[i].addEventListener("click", function(e) {

	e.target.parentNode.parentNode.parentNode.removeChild(e.target.parentNode.parentNode);
	imagesasupprimerTableau.push(e.target.id);
	imagesasupprimerInput.value=imagesasupprimerTableau;
	
		
	})
}

