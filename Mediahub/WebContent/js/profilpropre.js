
var inputFichierImage = document.getElementById("fichierimage");
var inputFichierImageErreur = document.getElementById("erreurfichierimage");
var submitInput = document.getElementById("submit");
//$ signifie dans la regex finie par .jpg par ex

var regex=/.jpg$|.png$/;
	

submitInput.addEventListener("click",
		function(e){
	controleFichierAvantEnvoi(e);
	controleExtensionFichierEnvoi(e);	
})


function controleFichierAvantEnvoi(e)
{
	if (inputFichierImage.value.length == 0 )
	{e.preventDefault();
	inputFichierImageErreur.innerHTML ="Veuillez charger une image";
	}	
}

function controleExtensionFichierEnvoi(e)

{var nomFichier = inputFichierImage.value;

	if (regex.test(nomFichier) )
		{/*image valide*/}
	else
	{e.preventDefault();
	
	inputFichierImageErreur.innerHTML ="Le format doit être png ou jpg";
	
	}
	
}


