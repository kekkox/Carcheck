document.addEventListener('DOMContentLoaded', function() {
    
    email_input = document.getElementsByName("email")[0];
    form = document.querySelector("form");
    button = document.querySelector("button");
    button_value = button.value; 
    
    if(button_value === "MODIFICA ADMIN")
    	form.addEventListener("submit", (event) => {onEditFormSubmit(event)});
    else
    	form.addEventListener("submit", (event) => {onAddFormSubmit(event)});
}, false);

let onAddFormSubmit = event => {
	event.preventDefault();
	
	let request = new XMLHttpRequest();
	let params = "email=" + email_input.value; 
	
	request.onreadystatechange = function() {
		if(this.readyState == 4)
			if(this.status == 200) {
				let responseJSON = JSON.parse(request.responseText);
				//If the email is not stored
				if(responseJSON.JsonResponseContent === false) {
					formSubmit();
				}
				else {
					errorHandler("Email gi&agrave; in uso", email_input, document.querySelector(".inputBox#email label"));
					switchButtonStatus();
					return;
				}
			}
			else {
				console.log("Impossibile ricevere la risposta");
				return;
			}
	}
	
	request.open("POST", "/CarCheck/RequestHandler/mail_check_service", true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.send(params);
	switchButtonStatus();
};

function formSubmit() {
	
	let grade = document.getElementsByName("state")[0].checked === true ? 1 : 0;
	
	let request = new XMLHttpRequest();
	let params = "email=" + email_input.value + "&"
		+ "name=" + document.getElementsByName("name")[0].value + "&"
		+ "surname=" + document.getElementsByName("surname")[0].value + "&"
		+ "grade=" + grade + "&"
		+ "operation=1"; 
	
	request.onreadystatechange = function() {
		if(this.readyState == 4)
			if(this.status == 200) {
				let responseJSON = JSON.parse(request.responseText);
				//If the email is not stored
				if(responseJSON.JsonResponseStatus === 1) {
					swal("Congratulazioni!", responseJSON.JsonResponseMessage, "success").then(() => document.location.href = "/CarCheck/RequestHandler/admins");
				}
				else {
					swal("OPS!", "Qualcosa e' andato storto. Riprova piu' tardi", "error");
					switchButtonStatus();
					return;
				}
			}
			else {
				console.log("Impossibile ricevere la risposta");
				return;
			}
	}
	
	request.open("POST", "/CarCheck/RequestHandler/admin_service", true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.send(params);
}

let onEditFormSubmit = event => {
	event.preventDefault();
	
	let grade = document.getElementsByName("state")[0].checked === true ? 1 : 0;
	
	let request = new XMLHttpRequest();
	let params = "email=" + email_input.value + "&"
		+ "name=" + document.getElementsByName("name")[0].value + "&"
		+ "surname=" + document.getElementsByName("surname")[0].value + "&"
		+ "grade=" + grade + "&"
		+ "operation=3"; 
	
	request.onreadystatechange = function() {
		if(this.readyState == 4)
			if(this.status == 200) {
				let responseJSON = JSON.parse(request.responseText);
				//If the email is not stored
				if(responseJSON.JsonResponseStatus === 1) {
					swal("Congratulazioni!", responseJSON.JsonResponseMessage, "success").then(() => document.location.href = "/CarCheck/RequestHandler/admins");
				}
				else {
					swal("OPS!", "Qualcosa e' andato storto. Riprova piu' tardi", "error");
					switchButtonStatus();
					return;
				}
			}
			else {
				console.log("Impossibile ricevere la risposta");
				return;
			}
	}
	
	request.open("POST", "/CarCheck/RequestHandler/admin_service", true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.send(params);
}

function switchButtonStatus() {
	if(button.innerHTML === loading_spinner) {
		button.classList.remove("loading");
		button.innerHTML = button_value;
		button.disabled = false;
	}
	else {
		//button.classList.add("loading");
		button.innerHTML = loading_spinner;
		button.disabled = true;
	}
}

/*
 * Manage the error status of the form.
 * */
function errorHandler(message, element, label) {

	let signup_container_element = document.querySelector('.signup_container');
	let form_header = document.querySelector('.signup_container .header');
	let errorMessage_element = document.querySelector('.signup_container .header div');
	element.style.borderBottom = "2px solid #F44336";
	
	label.style.color = "#F44336";

	errorMessage_element.style.visibility = "visible";
	errorMessage_element.innerHTML = message;
	form_header.classList.add("error");
	signup_container_element.style.animation = "shake 0.5s 1";
	
	setTimeout(() => {
			signup_container_element.style.animation = "";
		}, 1000);
	
}

let button;
let button_value;
let email_input;
let form;
let loading_spinner = "<i class=\"fa fa-spinner fa-spin\"></i>";