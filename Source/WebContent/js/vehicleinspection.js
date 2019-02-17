document.addEventListener('DOMContentLoaded', function() {
	
	search_input = document.querySelector(".search input");
	tableBody = document.querySelector("table tbody");
	
	search_input.addEventListener('keyup', (event) => onKeyUpHandler(event));
	
	originalContent = tableBody.innerHTML;
}, false);

let onKeyUpHandler = (event) => {
	
	clearTimeout(typingTimer);
	let searchText = search_input.value.trim();
	
	const INPUT_DELAY = 500;
	
    let isBackspaceOrDelete = (event.keyCode == 8 || event.keyCode == 46);

    if(searchText.length == 0)
    	tableBody.innerHTML = originalContent;
    else if(searchText.length > 1)
    	if(searchText)
    		typingTimer = setTimeout(()=>{addressRequestHandler(search_input.value.trim())}, INPUT_DELAY);
}

function addressRequestHandler(licensePlate) {
	let request = new XMLHttpRequest();
	let params = "licensePlate=" + licensePlate; 
	request.onreadystatechange = function() {
		if(this.readyState == 4)
			if(this.status == 200) {
				responseManager(this.responseText);
			}
			else
				console.log("Impossibile ricevere la risposta");
	}
	
	request.open("POST", "/CarCheck/RequestHandler/find_vehicle_service", true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.send(params);
}

function responseManager(response) {
	let jsonResponse = JSON.parse(response);
	
	if(jsonResponse.JsonResponseStatus != 1)
		return;
	
	let results = jsonResponse.JsonResponseContent;
	tableBody.innerHTML = tableHeader;
	
	results.map((result) => {
		let string = "";
		string += "<tr>";
		string += "<td class='mainCell'>" + result.vehicle + "</td>";
		string += "<td>" + result.inspectionDate + "</td>";
		string += "<td>" + result.km + "</td>";
		string += "<td>" + result.expirationDate + "</td>";
    	
    	if(result.result == true)
    		string += "<td><div class='statusCell positive'>Approvata</div></td>";
    	else
    		string += "<td><div class='statusCell negative'>Respinta</div></td>";
    	
    	string += "<td><div class='action'>" +
		"<a href='../RequestHandler/inspectionview?inspectionKey=" + result.id + "'><i class='fas fa-eye' id='eye'></i></a>" + 
		"<a href='../RequestHandler/inspectionedit?inspectionKey=" + result.id + "'><i class='fas fa-pencil-alt' id='pencil'></i></a>" + 
		"</div></td>";
    	
    	string += "</tr>";
    	
    	tableBody.innerHTML += string;
    });
}

let search_input;
let tableBody;
let typingTimer;

let originalContent;

const tableHeader = "<tr>" + 
	                "<th>Targa</th>" +
					"<th>Data Revisione</th>" +
					"<th>Chilometri</th>" +
					"<th>Data Scadenza</th>" +
					"<th>Esito</th>" +
	                "<th class='lastCell'>Azioni</th>" +
                "</tr>";