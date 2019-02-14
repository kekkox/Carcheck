document.addEventListener('DOMContentLoaded', function() {
	
	let form = document.querySelector(".login_container form");
	form.addEventListener('submit', (event) => {onFormSubmit(event)});

	//Initializing element
	button_element = document.querySelector(".login_container button");
	email_input = document.getElementsByName("email")[0];
	password_input = document.getElementsByName("password")[0];
	errorMessage_element = document.querySelector(".login_container .header div");
	form_header = document.querySelector(".login_container .header");
	login_container_element = document.querySelector(".login_container");
    
}, false);

function onFormSubmit(event) {
	event.preventDefault();

	switchButtonStatus();
	
	checkAccount(email_input.value, password_input.value);
}

function checkAccount(email,password) {
	let xhttp = new XMLHttpRequest();
	const params = "email=" + email + "&password=" + password;
	xhttp.onreadystatechange = (event) => {responseHandler(event.target)};
	xhttp.open("POST", "../RequestHandler/loginAdmin", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(params);
}

function responseHandler(request) {
	if(request.readyState == 4) {
		if(request.status == 200)
			responseManager(request.responseText);
		else 
			errorHandler();
		
		switchButtonStatus();
	}
}

function switchButtonStatus() {
	if(button_element.innerHTML === loading_spinner) {
		button_element.classList.remove("loading");
		button_element.innerHTML = "LOGIN";
		button_element.disabled = false;
	}
	else {
		button_element.classList.add("loading");
		button_element.innerHTML = loading_spinner;
		button_element.disabled = true;
	}
}

function responseManager(response) {
	let jsonObject = JSON.parse(response);
	
	if(jsonObject.JsonResponseStatus === 0)
		errorHandler(jsonObject.JsonResponseMessage);
	else
		document.location.href = jsonObject.JsonResponseContent;
}

function errorHandler(message) {

	email_input.style.borderBottom = borderStyle;
	password_input.style.borderBottom = borderStyle;

	let labels = document.querySelectorAll(".login_container .inputBox label");
	
	for(let label of labels) {
		label.style.color = "#F44336";
	}

	errorMessage_element.style.visibility = "visible";
	errorMessage_element.innerText = message;
	form_header.classList.add("error");
	login_container_element.style.animation = "shake 0.5s 1";
	
	button_element.style.backgroundColor = "#F44336";
	setTimeout(() => {
			login_container_element.style.animation = "";
			button_element.style.backgroundColor = "#06c"
		}, 1000);
	
}

let button_element;
let loading_spinner = "<i class=\"fa fa-spinner fa-spin\"></i>";

let borderStyle =  "2px solid #F44336";

let email_input;
let password_input;
let errorMessage_element;
let form_header;
let login_container_element;