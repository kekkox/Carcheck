document.addEventListener('DOMContentLoaded', function() {
	
	let form = document.querySelector(".settings_container form");
	form.addEventListener('submit', (event) => {onFormSubmit(event)});
	
	let passwordElements = document.getElementsByName("password");
	
	//Initializing element
	currPassword_element = passwordElements[0];
	newPassword_element = passwordElements[1];
	repeatedPassword_element = passwordElements[2];
    
}, false);

let onFormSubmit = event =>  {
	event.preventDefault();
	if(testField())
		changePassword();
}

function changePassword() {
	let xhttp = new XMLHttpRequest();
	const params = "oldPassword=" + currPassword_element.value + "&" 
				+ "password=" + newPassword_element.value + "&"
				+ "repeatedPassword=" + repeatedPassword_element.value;
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4)
			if(this.status == 200) {
				let responseJSON = JSON.parse(this.responseText);
				
				if(responseJSON.JsonResponseStatus === 1)
					swal("Congratulazioni!", responseJSON.JsonResponseMessage, "success").then(() => {
						document.location.href = responseJSON.JsonResponseContent;
					})
				else
					swal("OPS!", responseJSON.JsonResponseMessage, "error");
			}
			else {
				console.log("Impossibile ricevere la risposta");
				return;
			}

	};
	xhttp.open("POST", "/CarCheck/RequestHandler/change_password_service", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(params);
}

function testField() {
	if(newPassword_element.value != repeatedPassword_element.value) {
		swal("OPS", "Le due nuove password non coincidono", "error");
		return false;
	}
	
	return true;
}

function switchButtonStatus() {
	if(button.innerHTML === loading_spinner) {
		button.classList.remove("loading");
		button.innerHTML = "CAMBIA PASSWORD";
		button.disabled = false;
	}
	else {
		button.classList.add("loading");
		button.innerHTML = loading_spinner;
		button.disabled = true;
	}
}

let loading_spinner = "<i class=\"fa fa-spinner fa-spin\"></i>";
let currPassword_element;
let newPassword_element;
let repeatedPassword_element;