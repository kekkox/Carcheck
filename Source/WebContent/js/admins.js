async function removeAdmin() {
	
	let email = event.target.getAttribute('id');
	
	const {value: formValues} = await Swal.fire({
	  title: "Vuoi rimuovere l'amministratore?",
	  confirmButtonText: 'Rimuovi',
	  confirmButtonColor: '#e74c3c',
	  showCancelButton: 'true',
	  cancelButtonText: 'Annulla',
	  showLoaderOnConfirm: true,
	  focusConfirm: false,
	  preConfirm: () => {
		    return fetch(`admin_service?email=${email}&operation=2`, 
			    		{method: "POST"}).then(response => {
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
	    Swal.fire('Congratulazioni',
			'Admin rimosso con successo',
			'success').then(() => {document.location.href = "/CarCheck/RequestHandler/admins";})
	  }
	})
	
	if (formValues) {
	  Swal.fire(json.stringify(formValues))
	}
}