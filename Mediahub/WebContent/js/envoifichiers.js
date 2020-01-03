var descriptionInput = document.getElementById("description");
var erreurDescriptionDiv = document.getElementById("erreurdescription");
var fichierInput = document.getElementById("fichier");
var erreurFichierDiv = document.getElementById("erreurfichier");
var submitInput = document.getElementById("submit");
var descriptionValide = false;
var fichierValide = false;

descriptionInput.addEventListener("blur", function() {
	validationDescription();

})

function validationDescription() {
	if (descriptionInput.value.trim().length <= 3) {

		erreurDescriptionDiv.innerHTML = "Le description du fichier doit faire plus de 3 caractères";
		descriptionValide = false;

	} else {
		descriptionValide = true;
		erreurDescriptionDiv.innerHTML = "";

	}
}

fichierInput.addEventListener("blur", function() {
	validationFichier();
})

function validationFichier() {
	if (fichierInput.value.trim() == "") {

		erreurFichierDiv.innerHTML = "Aucun fichier n'a été ajouté";
		fichierValide = false;
	} else {
		fichierValide = true;
		erreurFichierDiv.innerHTML = "";

	}
}

submitInput.addEventListener("click", function(e) {

	validationFormulaire(e);
})

function validationFormulaire(e) {
	validationDescription();
	validationFichier();
	if (descriptionValide == false || fichierValide == false) {
		e.preventDefault();
	}
}