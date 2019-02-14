/*
 * Called when the document is loaded
 */
document.addEventListener('DOMContentLoaded', function() {
    
	//Initializing DOM element
    region_select = document.querySelector("select#region");
    province_select = document.querySelector("select#province");
    city_select = document.querySelector("select#city");
    address_input = document.getElementsByName("address")[0];
    result_ul = document.querySelector("ul#addressResult");
    form_element = document.querySelector('form');
    button = document.querySelector("form button");
    
    //Adding event listener
    region_select.addEventListener('change', onRegionChangeHandler);
    province_select.addEventListener('change',onProvinceChangeHandler);
    city_select.addEventListener('change', onCityChangeHandler);
    address_input.addEventListener('keyup', (event) => onKeyUpAddressHandler(event));
    form_element.addEventListener('submit', (event) => {onFormSubmitHandler(event, document.querySelector(".inputBox#email input").value)});
    
    //Send request to load region
    let regionRequest = new XMLHttpRequest();
	regionRequest.onreadystatechange = (event) => {locationResponseHandler(event.target, region_select, 'id', 'name');};
	regionRequest.open("POST", region_select.attributes['data-source'].value, true);
	regionRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    regionRequest.send();
    
}, false);

/*
 * Manage every response that work with Region, Province and City.
 * */
let locationResponseHandler = (response, element, key, value) => {
    if(response.readyState == 4)
        if(response.status == 200) {
        	responseManager(response.responseText, element, key, value);
        }
        else
            console.log("Impossibile ricevere la risposta");
}

/**
 * When user click on a element of the region's select, this handler manage the DOM.
 * It disable all the other components except the provice's select and make a request to get the province of the selected region.
 */
let onRegionChangeHandler = () => {
	province_select.disabled = false;
	province_select.innerHTML = option_value;
	
	city_select.disabled = true;
	city_select.innerHTML = option_value;
	
	address_input.disabled = true;
	address_input.value = "";
	result_ul.innerHTML = "";
	
	const params = "region=" + region_select.options[region_select.selectedIndex].value;
	let provinceRequest = new XMLHttpRequest();
	provinceRequest.onreadystatechange = (event) => {locationResponseHandler(event.target, province_select, 'provinceCode', 'name')};
	provinceRequest.open("POST", province_select.attributes['data-source'].value, true);
	provinceRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	provinceRequest.send(params);
}

/**
 * When user click on a element of the province's select, this handler manage the DOM.
 * It enable city component, disable address component and make a request to get the city of the selected province.
 */
let onProvinceChangeHandler = () => {
	city_select.disabled = false;
	city_select.innerHTML = option_value;
	
	address_input.disabled = true;
	address_input.value = "";
	result_ul.innerHTML = "";
	
	const params = "province=" + province_select.options[province_select.selectedIndex].value;
	let cityRequest = new XMLHttpRequest();
	cityRequest.onreadystatechange = (event) => {locationResponseHandler(event.target, city_select, 'istat', 'name')};
	cityRequest.open("POST",city_select.attributes['data-source'].value, true);
	cityRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	cityRequest.send(params);
	
}

/**
 * When user click on a element of the city's select, this handler manage the DOM.
 * It disable address component and make a request to get the city of the selected province.
 */
let onCityChangeHandler = () => {
	address_input.disabled = false;
	address_input.value = "";
	result_ul.innerHTML = "";
}

/**
 * When user type an address, this handler is called to retrieve the address of the city selected.
 */
let onKeyUpAddressHandler = (event) => {
 
	event.target.setAttribute('value',	event.target.value.trim());
	
	clearTimeout(typingTimer);
	let searchText = address_input.value.trim();
	
	const INPUT_DELAY = 500;
	
	let c = String.fromCharCode(event.keyCode);
    let isWordCharacter = c.match(/[\w\s]/);
    let isBackspaceOrDelete = (event.keyCode == 8 || event.keyCode == 46);

    // trigger only on word characters, backspace or delete and an entry size of at least 3 characters
    if((isWordCharacter || isBackspaceOrDelete) && searchText.length > 2)
    	if(searchText)
    		typingTimer = setTimeout(()=>{addressRequestHandler(address_input.value.trim() ,city_select.options[city_select.selectedIndex].value)}, INPUT_DELAY);
}


/*
 * Manage the request for the address
 */
function addressRequestHandler(address, istat) {
	let request = new XMLHttpRequest();
	let params = "address=" + address + "&istat=" + istat; 
	request.onreadystatechange = function() {
		if(this.readyState == 4)
			if(this.status == 200) {
				addressResponseManager(this.responseText);
			}
			else
				console.log("Impossibile ricevere la risposta");
	}
	
	request.open("POST", "RequestHandler/address_service", true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.send(params);
}

/*
 * Map every object retrieved by the server and show it to the user
 */
function responseManager (response, element, key, value) {
    let responseJSON = JSON.parse(response);
    if(responseJSON.JsonResponseStatus === 1)
	    responseJSON.JsonResponseContent.map((option) => {
	    	let option_element = document.querySelector("option").cloneNode();
	    	
	    	option_element.value = option[key];
	    	option_element.text = option[value];
	    	option_element.disabled = false;
	    	option_element.selected = false;
	    	
	    	element.append(option_element);
	    });
    
    element.disabled = false;
}

/*
 * Map every address object retrieved by the server and show it to the user.
 */
function addressResponseManager(response) {
	let responseJSON = JSON.parse(response);
		
	result_ul.innerHTML = "";
	
	if(responseJSON.JsonResponseStatus === 1)
	    responseJSON.JsonResponseContent.forEach((option) => {
	    	let node = document.createElement("li");
	    	let span = document.createElement("span");
	    	
	    	node.setAttribute('value', option['id']);
	    	node.addEventListener('click', (event) => {onLiClickHandler(event.target)})
	    	span.innerHTML = option['name'];
	    	
	    	node.append(span);
	    	result_ul.append(node);
	    });
	else
		result_ul.innerHTML = "";
}

/*
 * Called when a li element is clicked
 */
function onLiClickHandler(target) {
	let span = target.closest('span');
	let li = target.closest('li');
	
	address_input.value = span.innerText;
	address_input.setAttribute('value', li.getAttribute('value'));
	result_ul.innerHTML = "";
}

/**
 * Called when user submit the form.
 */
function onFormSubmitHandler(event, email) {
	event.preventDefault();
	switchButtonStatus();
	
	//If the user doesn't select an address
	if((isNaN(address_input.getAttribute('value')))) {
		errorHandler("Seleziona un indirizzo dall'elenco.\nIndirizzi personalizzati non sono ammessi", address_input, document.querySelector(".inputBox#address label"));
		switchButtonStatus();
		return;
	}
		
	
	let request = new XMLHttpRequest();
	let params = "email=" + email; 
	
	request.onreadystatechange = function() {
		if(this.readyState == 4)
			if(this.status == 200) {
				let responseJSON = JSON.parse(request.responseText);
				//If the email is not stored
				if(responseJSON.JsonResponseContent === false) {
					formSubmit();
				}
				else {
					errorHandler("Email gi&agrave; in uso", document.getElementsByName('email')[0], document.querySelector(".inputBox#email label"));
					switchButtonStatus();
					return;
				}
			}
			else {
				console.log("Impossibile ricevere la risposta");
				return;
			}
	}
	
	request.open("POST", "RequestHandler/mail_check_service", true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.send(params);
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

/*
 * Called to do signup
 */
function formSubmit() {
	let request = new XMLHttpRequest();
	const piva = document.querySelector(".inputBox#piva input").value;
	const email = document.querySelector(".inputBox#email input").value;
	const owner = document.querySelector(".inputBox#owner input").value;
	const businessName = document.querySelector(".inputBox#businessName input").value;
	const telephone = document.querySelector(".inputBox#telephone input").value;
	const address = address_input.getAttribute('value');
	const description = document.querySelector(".inputBox#description textarea").value;
	
	let params = "email=" + email 
					+ "&iva=" + piva 
					+ "&businessName=" + businessName
					+ "&telephone=" + telephone
					+ "&owner=" + owner
					+ "&address=" + address
					+ "&description=" + description; 
	
	request.onreadystatechange = function() {
		if(this.readyState == 4) {
			switchButtonStatus();
			
			if(this.status == 200) {
				let responseJSON = JSON.parse(request.responseText);
				swal("Congratulazioni!", responseJSON.JsonResponseMessage, "success").then(() => document.location.href = "login.jsp");
			}
			else {
				swal("OPS!", "Qualcosa e' andato storto. Riprova piu' tardi", "error");
				return;
			}
		}
	}
	
	request.open("POST", "RequestHandler/signup", true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.send(params);
}

/*Toggle the loading animation*/
function switchButtonStatus() {
	if(button.innerHTML === loading_spinner) {
		button.classList.remove("loading");
		button.innerHTML = "REGISTRATI";
		button.disabled = false;
	}
	else {
		button.classList.add("loading");
		button.innerHTML = loading_spinner;
		button.disabled = true;
	}
}

let region_select;
let province_select;
let city_select;
let result_ul;
let address_input;
let form_element;
let button;

let typingTimer;

let option_value = "<option disabled selected></option>";
let loading_spinner = "<i class=\"fa fa-spinner fa-spin\"></i>";