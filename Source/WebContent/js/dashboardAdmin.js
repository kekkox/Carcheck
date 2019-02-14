/*
 *	Called when the document is loaded 
 */
document.addEventListener('DOMContentLoaded', function() {
	dataContainer = document.querySelectorAll(".card .info h2");
	console.log(dataContainer);
	retrieveData();
}, false);

function retrieveData() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4) {
			if(this.status == 200)
				mapStatisticResult(JSON.parse(this.responseText));
		}
	};
	xhttp.open("POST", "/CarCheck/RequestHandler/admin_statistics_service", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send();
}

function mapStatisticResult(response) {
	let data = response.JsonResponseContent;
	
	dataContainer[0].innerHTML = data.vehicles;
	dataContainer[1].innerHTML = data.requests;
	dataContainer[2].innerHTML = data.workshops;
} 

let dataContainer;