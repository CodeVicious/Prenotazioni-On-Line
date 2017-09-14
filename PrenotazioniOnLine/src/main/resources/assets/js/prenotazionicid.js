$(document).ready(
		function() {

			$("#prenotazioneModale").validator().on("submit", function(event) {
				if (event.isDefaultPrevented()) {
					// handle the invalid form...
					formError();
					submitMSG(false, "Did you fill in the form properly?");
				} else {
					// everything looks good!
					event.preventDefault();
					submitForm();
				}
			});

			$("#SelezioneUfficio").on('change', function() {
				var selected = []
				selected = $('#SelezioneUfficio').val()
				var moment = $('#calendar').fullCalendar('getDate');
				$('#calendar').fullCalendar('removeEventSources');
				$('#calendar').fullCalendar('removeEvents');
				$('#calendar').fullCalendar('addEventSource', {
					url : '/disponibilita?place=' + selected,
					type : 'GET', // Send post data
					error : function() {
						alert('There was an error while fetching events.');
					}
				});

				$('#calendar').fullCalendar('rerenderEvents');

			});

			$('#calendar').fullCalendar(
					{
						header : {
							left : 'prev,next today',
							center : 'title',
							right : 'month,agendaWeek,agendaDay'
						},
						editable : true,
						eventLimit : true, // allow "more" link when too many
											// events
						events : [],
						eventClick : function(calEvent, jsEvent, view) {

							// $(this).css('border-color', 'red');
							$("#prenotazioneModale.modal-body #availability")
									.val(calEvent.FKplaces);

							$("#prenotazioneModale").modal('show');

						},

					});

		});

function submitForm() {
	$("#prenotazioneModale").modal('hide');

	// Get form
	var form = $('#schedaprenotazione')[0];

	// Create an FormData object
	var data = new FormData(form);

	data.append("reservationdate", moment().format() );

	$.ajax({
		type : "POST",
		enctype : 'multipart/form-data',
		url : "/reservation/store",
		data : data,
		processData : false,
		contentType : false,
		cache : false,
		timeout : 600000,
		success : function(text) {
			alert(text);
			if (text == "success") {
				formSuccess();
			} else {
				formError();
				submitMSG(false, text);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Status: " + textStatus);
			alert("Error: " + errorThrown);
		}
	});
}

function formSuccess() {

	$('#schedaprenotazione')[0].reset();
	$(this).find('#prenotazioneModale')[0].reset();

	submitMSG(true, "Prenotazione Confermata!");
}

function formError(text) {

	submitMSG(false, text);
}

function submitMSG(valid, msg) {
	if (valid) {
		var msgClasses = "h3 text-center tada animated text-success";
	} else {
		var msgClasses = "h3 text-center text-danger";
	}

	$('#msgSubmit').removeClass().addClass(msgClasses).text(msg);
	$('#esitoModal').modal('show');
}
