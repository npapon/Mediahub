

var nomfichierInput = document.getElementById("nomfichier");
var erreurNomfichierDiv = document.getElementById("erreurnomfichier");
var submitInput = document.getElementById("submit");
var nomfichierValide = false;


nomfichierInput.addEventListener("blur", function() {
	
	validationNomfichier();

})

function validationNomfichier() {
	if (nomfichierInput.value.trim().length <= 3) {

		erreurNomfichierDiv.innerHTML = "Le nom du fichier doit faire plus de 3 caractères";
		nomfichierValide = false;

	} else {
		nomfichierValide = true;
		erreurNomfichierDiv.innerHTML = "";

	}
}


submitInput.addEventListener("click", function(e) {

	validationFormulaire(e);
})

function validationFormulaire(e) {
	validationNomfichier();
	if (nomfichierValide == false) {
		e.preventDefault();
	}
}
