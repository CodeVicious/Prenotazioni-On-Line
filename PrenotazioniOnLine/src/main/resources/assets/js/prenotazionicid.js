$(document).ready(function() {

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

	$('#schedaprenotazione').click(function(e) {
		e.preventDefault();
		alert(e)
	});

	$('#calendar').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
		editable : true,
		eventLimit : true, // allow "more" link when too many events
		events : [],
		eventClick : function(calEvent, jsEvent, view) {

			// $(this).css('border-color', 'red');

			$("#prenotazioneModale").modal('show');

		},
		dayClick : function(date, jsEvent, view) {

			alert('Clicked on: ' + date.format());

			alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);

			alert('Current view: ' + view.name);

			// change the day's background color just for fun
			$(this).css('background-color', 'red');

		}
	});

});

function submitForm() {
	$("#prenotazioneModale").modal('hide');

	var email = $("#email").val();
	var message = $("#message").val();

	$.ajax({
		type : "POST",
		url : "php/form-process.php",
		data : "name=" + name + "&email=" + email + "&message=" + message,
		success : function(text) {
			if (text == "success") {
				formSuccess();
			} else {
				formError();
				submitMSG(false, text);
			}
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
	
