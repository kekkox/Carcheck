async function setAppointment() {
	
	const id = document.querySelector(".header span").innerText.substring(11);
	
	
	const {value: date} = await Swal.fire({
	  title: 'Data appuntamento',
	  html: "<input id='swal-date' type='date' name='date'></input>",
	  confirmButtonText: 'Seleziona ora',
	  preConfirm: () => {return document.getElementById('swal-date').value}
	})

	if (date) {
		Swal.fire({
			  title: 'Ora appuntamento',
			  input: 'select',
			  inputOptions: {
				    '09:00': '09:00',
				    '09:15': '09:15',
				    '09:30': '09:30',
				    '09:45': '09:45',
				    '10:00': '10:00',
				    '10:15': '10:15',
				    '10:30': '10:30',
				    '10:45': '10:45',
				    '11:00': '11:00',
				    '11:15': '11:15',
				    '11:30': '11:30',
				    '11:45': '11:45',
				    '12:00': '12:00',
				    '15:30': '15:30',
				    '15:45': '15:45',
				    '16:00': '16:00',
				    '16:15': '16:15',
				    '16:30': '16:30',
				    '16:45': '16:45',
				    '17:00': '17:00'
				  },
			  confirmButtonText: 'Conferma',
			  showLoaderOnConfirm: true,
			  preConfirm: (hour) => {
			    return fetch(`requests_service?id=${id}&operation=2&date=${date}&time=${hour}`)
			      .then(response => {
			        if (!response.ok) {
			          throw new Error(response.statusText)
			        }
			        return response.json()
			      })
			      .catch(error => {
			        Swal.showValidationMessage(
			          `Request failed: ${error}`
			        )
			      })
			  },
			  allowOutsideClick: () => !Swal.isLoading()
			}).then((result) => {
			  if (result.value) {
			    Swal.fire({
			    	title: 'Congratulazioni',
					text: 'Appuntamento fissato con successo',
					type: 'success'
					}).then(() => {document.location.href = "/CarCheck/admin/internal/adhesionRequests.jsp";})
			  }
			})
	}
}


function approveRequest() {
	const id = document.querySelector(".header span").innerText.substring(11);
	
	Swal.fire({
		  title: 'Vuoi approvare la richiesta?',
		  confirmButtonText: 'Conferma',
		  showCancelButton: 'true',
		  cancelButtonText: 'Annulla',
		  showLoaderOnConfirm: true,
		  preConfirm: (text) => {
			    return fetch(`requests_service?id=${id}&operation=3`)
			      .then(response => {
			    	  console.log(response);
			        if (!response.ok) {
			          throw new Error(response.statusText)
			        }
			        return response.json()
			      })
			      .catch(error => {
			        Swal.showValidationMessage(
			          `Request failed: ${error}`
			        )
			      });
		  },
		  allowOutsideClick: () => !Swal.isLoading()
		}).then((result) => {
		  if (result.value) {
		    Swal.fire('Congratulazioni',
				'Richiesta confermata con successo',
				'success').then(() => {document.location.href = "/CarCheck/admin/internal/adhesionRequests.jsp";})
		  }
		})
	
}

function rejectRequest() {
	
	const id = document.querySelector(".header span").innerText.substring(11);
	
	Swal.fire({
		  title: 'Motivo del rifiuto',
		  input: 'text',
		  confirmButtonText: 'Rifiuta',
		  confirmButtonColor: '#e74c3c',
		  showLoaderOnConfirm: true,
		  preConfirm: (text) => {
			  if(text.length > 0)
			    return fetch(`requests_service?id=${id}&operation=1&cause=${text}`)
			      .then(response => {
			        if (!response.ok) {
			          throw new Error(response.statusText)
			        }
			        return response.json()
			      })
			      .catch(error => {
			        Swal.showValidationMessage(
			          `Request failed: ${error}`
			        )
			      })
			  else
				  return false;
		  },
		  allowOutsideClick: () => !Swal.isLoading()
		}).then((result) => {
		  if (result.value) {
		    Swal.fire('Congratulazioni',
				'Richiesta rifiutata con successo',
				'success').then(() => {document.location.href = "/CarCheck/admin/internal/adhesionRequests.jsp";})
		  }
		})
}