var creerInput = document.getElementById("creer");
var modifierInput = document.getElementById("modifier");
var codescriptInput = document.getElementById("codescript");
var erreurCodescriptDiv = document.getElementById("erreurcodescript");
var libellescriptInput = document.getElementById("libellescript");
var erreurLibellescriptDiv = document.getElementById("erreurlibellescript");
var creerDiv = document.getElementById("creerscript");
var modifierDiv = document.getElementById("modifierscript");
var submitInput = document.getElementById("submit");
var codescriptValide = false;
var libellescriptValide = false;
modifierDiv.style.display = "none";
var codescriptamodifierSelect = document.getElementById("codescriptamodifier");
var codescriptamodifierOption = document.getElementById("codescriptamodifieroption");
var libellescriptamodifierInput =document.getElementById("libellescriptamodifier");

codescriptamodifierSelect.addEventListener("change",function(e){
libellescriptamodifierInput.value = codescriptamodifierSelect.options[codescriptamodifierSelect.selectedIndex].innerHTML;
}
)


modifierInput.addEventListener("change", function() {


	creerDiv.style.display = "none";
	modifierDiv.style.display = "block";
;

})

creerInput.addEventListener("change", function() {


	creerDiv.style.display = "block";
	modifierDiv.style.display = "none";
	

})



codescriptInput.addEventListener("blur", function() {
	validationCodescript();

})

function validationCodescript() {
	if (codescriptInput.value.trim().length !== 3) {
	
		 erreurCodescriptDiv.innerHTML = "Le code doit faire 3 caractères";
		 codescriptValide = false;

	} else {
		
		codescriptValide = true;
		erreurCodescriptDiv.innerHTML = "";

	}
}

libellescriptInput.addEventListener("blur", function() {
	validationLibellescript();
})

function validationLibellescript() {
	if (libellescriptInput.value.trim().length <= 3) {

		erreurLibellescriptDiv.innerHTML = "Le libellé doit faire au moins 3 caractères";
		libellescriptValide = false;
	} else {
		libellescriptValide = true;
		erreurLibellescriptDiv.innerHTML = "";

	}
}

submitInput.addEventListener("click", function(e) {

	validationFormulaire(e);
})

function validationFormulaire(e) {
	validationCodescript() ;
	validationLibellescript() ;
	if (codescriptValide == false || libellescriptValide== false) {
		e.preventDefault();
	}
}
