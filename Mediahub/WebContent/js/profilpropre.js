
var inputFichierImage = document.getElementById("fichierimage");
var inputFichierImageErreur = document.getElementById("erreurfichierimage");
var submitInput = document.getElementById("submit");
submitInput.addEventListener("click",function(e){controleFichierAvantEnvoi(e)})


function controleFichierAvantEnvoi(e)
{if (inputFichierImage.value.length == 0 )
	{e.preventDefault();
	inputFichierImageErreur.innerHTML ="Veuillez charger une image";
	
	}

	
}

