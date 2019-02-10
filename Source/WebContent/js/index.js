document.addEventListener('DOMContentLoaded', function() {
    
    form = document.querySelector(".search_bar");
    form.addEventListener('submit', (event) => {onSearchClick(event)});
    
    document.addEventListener('keydown', (e) => {onEnterPressed(e)});

    ERROR_MESSAGE_ELEMENT = document.querySelector("div#error_message");
    
}, false);

function onSearchClick(event) {
	event.preventDefault();
    var licensePlateElement = document.getElementsByName("licenseplate")[0];
    var categoryElement = document.getElementsByName("category")[0];
    if(checkVehicleCategory(categoryElement))
    	if(checkLicensePlate(licensePlateElement, categoryElement.value))
    		sendRequest(licensePlateElement.value);
}

function sendRequest(param) {
	let xhttp = new XMLHttpRequest();
	const params = "licenseplate=" + param;
	xhttp.onreadystatechange = (event) => {responseHandler(event.target)};
	xhttp.open("POST", "RequestHandler/find", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(params);
}

function responseHandler(request) {
	if(request.readyState == 4) {
		if(request.status == 200) {
			let jsonResponse = JSON.parse(request.responseText);
			
			if(jsonResponse.JsonResponseStatus === 0)
				toggleLicensePlateErrorStatus(true, jsonResponse.JsonResponseMessage); 
			else {
				form.removeEventListener('submit', (event) => {onSearchClick(event)});
	    		form.submit();
			}
		}
		else if(request.status == 500)
			console.log("ERRORE SERVER");
	}
}

function onEnterPressed(e) {
	const ENTER_KEY_CODE = 13;
	
	if(e.keyCode === ENTER_KEY_CODE)
		onSearchClick();
}

function checkVehicleCategory(element) {
    let WRONG_VALUE = -1;

    var value = element.value;

    if(value == WRONG_VALUE) {
        element.classList.add(WRONG_CLASS);
        ERROR_MESSAGE_ELEMENT.innerHTML = "Seleziona una categoria di veicolo";
        ERROR_MESSAGE_ELEMENT.style.display = "block";
        return false;
    }
    else {
        element.classList.remove(WRONG_CLASS);
        ERROR_MESSAGE_ELEMENT.style.display = "none";
        return true;
    }
}

function checkLicensePlate(element, vehicleID) {

    var value = element.value.replace(" ", "");
    
    let CAR_ID = 1;
    let BIKE_ID = 2;
    let MOPED_ID = 3;
    
    let car_validator_1948 = /^(([a-z]){2}([0-9]){5})$/gi;
    let car_validator_1976 = /^(([0-9]){6}([a-z]){2})$/gi;
    let car_validator_1999 = /^(([a-z]){3}([0-9]){5})$/gi;
    let car_validator_today = /^(([a-z]){2}([0-9]){3}([a-z]){2})$/gi;
    
    let bike_validator_1927 = /^(([0-9]){5})$/gi;
    let bike_validator_1932 = /^(([0-9]){4}([a-z]){2})/gi;
    let bike_validator_1994 = /^(([a-z]){2}([0-9]){6})/gi;
    let bike_validator_today = /^(([a-z]){2}([0-9]){5})/gi;
    
    let moped_validator_2006 = /^([a-z0-9]){5}$/gi;
    let moped_validator_today = /^(X([b-z2-9]){5})$/gi;
    let moped_denied_char = /[01aeioqu]+/gi;
    
    //Validating Car 
    
    if(value.length === 0) {
        element.classList.add(WRONG_CLASS);
        ERROR_MESSAGE_ELEMENT.style.display = "block";
        ERROR_MESSAGE_ELEMENT.innerHTML = 'Il campo "Targa" &egrave; obbligatorio';
        return false;
    }
    
    if(vehicleID == CAR_ID)
	    if(!car_validator_today.test(value) &&
    		!car_validator_1999.test(value) &&
    		!car_validator_1976.test(value) &&
    		!car_validator_1948.test(value)) {
	    	
    		toggleLicensePlateErrorStatus(true, 'Inserire una targa valida');
	        return false;
	    }
	    else {
	    	toggleLicensePlateErrorStatus(false, '');
	        return true;
	    }
    
    //Validating Bike
    
    else if(vehicleID == BIKE_ID) {
    	if(!bike_validator_1927.test(value) && 
			!bike_validator_1932.test(value) &&
			!bike_validator_1994.test(value) &&
			!bike_validator_today.test(value)) {
    		
    		toggleLicensePlateErrorStatus(true, 'Inserire una targa valida');
    		return false;
    	}
    	else {
    		toggleLicensePlateErrorStatus(false, '');
    		return true;
    	}
    }
    
    //Validating Moped
    
    else if(vehicleID == MOPED_ID) {
    	
    	if(!moped_validator_2006.test(value) && (!moped_validator_today.test(value) && moped_denied_char.test(value))) {
    		
    		toggleLicensePlateErrorStatus(true, 'Inserire una targa valida');
    		return false;
    	}
    	else {
    		toggleLicensePlateErrorStatus(false, '');
    		return true;
    	}
    		
    }
}

/*if error status == true the error will be displayed*/
function toggleLicensePlateErrorStatus(errorStatus, message) {
	
	var element =  document.getElementsByName("licenseplate")[0];
	
	if(errorStatus) {
		element.classList.add(WRONG_CLASS);
        ERROR_MESSAGE_ELEMENT.style.display = "block";
        ERROR_MESSAGE_ELEMENT.innerHTML = message;
	}
	else {
		element.classList.remove(WRONG_CLASS);
        ERROR_MESSAGE_ELEMENT.style.display = "none";
	}
}

let WRONG_CLASS = "wrong";
let ERROR_MESSAGE_ELEMENT;
let form;