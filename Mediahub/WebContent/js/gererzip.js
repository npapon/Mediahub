
var boutonCheckBox = document.getElementsByName('cochee');
var elementsACocherTableau =[];
var elementsACocherInput = document.getElementById("elementsacocher");
elementsACocherInput.style.display = "none";





for (var i = 0, c = boutonCheckBox.length; i < c; i++)

{
	boutonCheckBox[i].addEventListener("change", function(e) {

	elementsACocherTableau.push(e.target.id);
	elementsACocherInput.value=elementsACocherTableau;
	
		
	})
}

