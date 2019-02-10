document.addEventListener('DOMContentLoaded', function() {
    
	//Initializing DOM element
    region_select = document.querySelector("select#region");
    province_select = document.querySelector("select#province");
    city_select = document.querySelector("select#city");
    address_input = document.getElementsByName("address")[0];
    result_ul = document.querySelector(".resultSet");
    
    //Adding event listener
    region_select.addEventListener('change', onRegionChangeHandler);
    province_select.addEventListener('change',onProvinceChangeHandler);
    address_input.addEventListener('keyup', (event) => onKeyUpAddressHandler(event));
    
    //Send request to load region
    let regionRequest = new XMLHttpRequest();
	regionRequest.onreadystatechange = (event) => {locationResponseHandler(event.target, region_select, 'id', 'name');};
	regionRequest.open("POST", region_select.attributes['data-source'].value, true);
	regionRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    regionRequest.send();
    
}, false);

let locationResponseHandler = (response, element, key, value) => {
    if(response.readyState == 4)
        if(response.status == 200) {
        	responseManager(response.responseText, element, key, value);
        }
        else
            console.log("Impossibile ricevere la risposta");
}

let onRegionChangeHandler = () => {
	province_select.disabled = false;
	province_select.innerHTML = option_value;
	
	city_select.disabled = true;
	city_select.innerHTML = option_value;
	
	const params = "region=" + region_select.options[region_select.selectedIndex].value;
	let provinceRequest = new XMLHttpRequest();
	provinceRequest.onreadystatechange = (event) => {locationResponseHandler(event.target, province_select, 'provinceCode', 'name')};
	provinceRequest.open("POST", province_select.attributes['data-source'].value, true);
	provinceRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	provinceRequest.send(params);
}

let onProvinceChangeHandler = () => {
	city_select.disabled = false;
	city_select.innerHTML = option_value;
	
	const params = "province=" + province_select.options[province_select.selectedIndex].value;
	console.log(params);
	let cityRequest = new XMLHttpRequest();
	cityRequest.onreadystatechange = (event) => {locationResponseHandler(event.target, city_select, 'istat', 'name')};
	cityRequest.open("POST",city_select.attributes['data-source'].value, true);
	cityRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	cityRequest.send(params);
	
}

let onKeyUpAddressHandler = (event) => {
 
	event.target.setAttribute('value',	event.target.value.trim());
	
	let searchText = address_input.value.trim();
	
	let c = String.fromCharCode(event.keyCode);
    let isWordCharacter = c.match(/\w/);
    let isBackspaceOrDelete = (event.keyCode == 8 || event.keyCode == 46);

    // trigger only on word characters, backspace or delete and an entry size of at least 3 characters
    if((isWordCharacter || isBackspaceOrDelete) && searchText.length > 2)
    	console.log("Sto facendo a richiesta aho!");
}

function responseManager (response, element, key, value) {
    let responseJSON = JSON.parse(response);
    console.log(responseJSON);
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



let region_select;
let province_select;
let city_select;
let result_ul;

let address_input;

let option_value = "<option disabled selected></option>"