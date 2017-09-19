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
							console.log(calEvent.id);
							$("#availability")
									.val(calEvent.id);

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
	// Display the key/value pairs


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
			
				formSuccess(text);
			
			}
		,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Status: " + textStatus);
			alert("Error: " + errorThrown);
		}
	});
}

function formSuccess(text) {

	$('#schedaprenotazione')[0].reset();
	

	submitMSG(true, "Prenotazione Confermata! <br/> N.Prenotazione "+text);
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

	$('#msgSubmit').removeClass().addClass(msgClasses).html(msg);
	$('#esitoModal').modal('show');
}
