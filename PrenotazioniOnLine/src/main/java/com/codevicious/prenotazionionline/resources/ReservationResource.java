package com.codevicious.prenotazionionline.resources;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.PrenotazioniOnLineConfiguration;
import com.codevicious.prenotazionionline.dao.AvailabilityDAO;
import com.codevicious.prenotazionionline.dao.ReservationDAO;
import com.codevicious.prenotazionionline.factories.EmailServiceFactory;
import com.codevicious.prenotazionionline.representations.Availability;
import com.codevicious.prenotazionionline.representations.DataTableResult;
import com.codevicious.prenotazionionline.representations.Reservation;

@Path("/reservation")
@Produces(MediaType.APPLICATION_JSON)

public class ReservationResource {

	private final ReservationDAO reservationDAO;
	private final AvailabilityDAO availabilityDAO;
	private final EmailServiceFactory smtpParams;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationResource.class);

	public ReservationResource(DBI jdbi, PrenotazioniOnLineConfiguration configuration) {
		reservationDAO = jdbi.onDemand(ReservationDAO.class);
		availabilityDAO = jdbi.onDemand(AvailabilityDAO.class);
		this.smtpParams = configuration.getSmtpparams();
	}

	@GET
	@Path("/{id}")
	public Response getReservations(@PathParam("id") long id) {
		// retrieve information about all the availabilities
		// ...

		if (!Optional.ofNullable(id).isPresent()) {
			List<Reservation> reservation = reservationDAO.getAllReservations();
			return Response.ok(reservation).build();

		} else {
			Reservation reservation = reservationDAO.getReservationById(id);
			return Response.ok(reservation).build();

		}

	}

	@POST
	@Path("/store")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response createReservation(@FormDataParam("name") String name, @FormDataParam("surname") String surname,
			@FormDataParam("email") String email, @FormDataParam("address") String address,
			@FormDataParam("borndate") String borndate, @FormDataParam("phone") String phone,
			@FormDataParam("note") String note, @FormDataParam("fkavailability") long fkavailability,
			@FormDataParam("reservationdate") String reservationdate) throws URISyntaxException {
		// store the new reservation
		// ...

		DateTimeFormatter fmt = ISODateTimeFormat.dateTimeNoMillis();
		DateTimeFormatter fmt2 = DateTimeFormat.forPattern("yyyy-MM-dd");

		long newReservationID = reservationDAO.createReservation(name, surname, email, address,
				fmt2.parseDateTime(borndate), phone, fkavailability, fmt.parseDateTime(reservationdate), note);

		availabilityDAO.updateAvailabilityReserved(fkavailability, true);
		Availability reservat = availabilityDAO.getAvailabilityById(fkavailability);

		final HtmlEmail htmlEmail = smtpParams.build();

		try {
			htmlEmail.setSSLOnConnect(true);
			htmlEmail.setFrom("ced@comune.san-miniato.pi.it");
			htmlEmail.addTo(email);
			htmlEmail.addTo("gcodevico@comune.san-miniato.pi.it");
			htmlEmail.setSubject(
					"Notifica di Avvenuta prenotazione Carta d'identit‡ digitale del Comune di San miniato");
			htmlEmail.setMsg("   "
					+ "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>"
					+ "<html lang='en'>" + "<head>"
					+ "  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
					+ "  <meta name='viewport' content='width=device-width, initial-scale=1'>"
					+ "  <meta http-equiv='X-UA-Compatible' content='IE=edge'>" + ""
					+ "  <title>Prenotazione Carta d'Idetnit‡† digitale del Comune di San Miniato</title>" + ""
					+ "  <style type='text/css'>" + "  </style>    " + "</head>"
					+ "<body style='margin:0; padding:0; background-color:#F2F2F2;'>" + "  <center>"
					+ "<h3>Prenotazione Carta d'Idetnit√† digitale del Comune di San Miniato</h3>"
					+ "<table width='640' cellpadding='0' cellspacing='0' border='0' class='wrapper' bgcolor='#FFFFFF'>"
					+ "  <tr>" + "    <td height='10' style='font-size:10px; line-height:10px;'>&nbsp;</td>" + "  </tr>"
					+ "  <tr>" + "    <td align='center' valign='top'>" + ""
					+ "      <table width='600' cellpadding='0' cellspacing='0' border='0' class='container'>"
					+ "        <tr>" + "          <td align='center' valign='top'>" + "            Nome"
					+ "          </td>" + "<td align='center' valign='top'>" + name + "          </td>"
					+ "        </tr>" + "        <tr>" + "          <td align='center' valign='top'>"
					+ "            Cognome" + "          </td>" + "<td align='center' valign='top'>" + surname
					+ "          </td>" + "        </tr>" + "        <tr>"
					+ "          <td align='center' valign='top'>" + "            Indirizzo" + "          </td>"
					+ "<td align='center' valign='top'>" + address + "          </td>" + "        </tr>"
					+ "        <tr>" + "          <td align='center' valign='top'>" + "            Data di Nascita"
					+ "          </td>" + "<td align='center' valign='top'>" + borndate + "          </td>"
					+ "        </tr>" + "        <tr>" + "          <td align='center' valign='top'>"
					+ "            Telefono" + "          </td>" + "<td align='center' valign='top'>" + phone
					+ "          </td>" + "        </tr>" + "        <tr>"
					+ "          <td align='center' valign='top'>" + "            Prenotazione del" + "          </td>"
					+ "<td align='center' valign='top'>" + reservat.getName() + " " + reservat.getStart()
					+ "          </td>" + "        </tr>" + "      </table>" + "" + "    </td>" + "  </tr>" + "  <tr>"
					+ "    <td height='10' style='font-size:10px; line-height:10px;'>&nbsp;</td>" + "  </tr>"
					+ "</table>  " + "  </center>" + "</body>" + "</html>");

		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());

		// inside your getSalesUserData() method
		executor.execute(new Runnable() {
			public void run() {
				try {
					htmlEmail.send();
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		return Response.ok(String.valueOf(newReservationID)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteAvailability(@PathParam("id") int id) {
		// delete the contact with the provided id
		// ...
		reservationDAO.deleteReservation(id);
		return Response.noContent().build();
	}

	@GET
	@Path("/showReservations")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getAllReserved(@QueryParam("iDisplayStart") String pageNo,
			@QueryParam("iDisplayLength") String pageSize, @QueryParam("iSortCol_0") String colIndex,
			@QueryParam("sSortDir_0") String sortDirection, @QueryParam("sSearch") String GLOBAL_SEARCH_TERM) {

		// retrieve All the reserved places
		// ...

		String[] columnNames = { "id", "name", "surname", "email", "address", "borndate", "tel", "reservationdate",
				"notes" };

		int listDisplayAmount = 10;
		long start = 0;
		int column = 0;
		String dir = "asc";

		if (pageNo != null) {
			start = Integer.parseInt(pageNo);
			if (start < 0) {
				start = 0;
			}
		}
		if (pageSize != null) {
			listDisplayAmount = Integer.parseInt(pageSize);
			if (listDisplayAmount < 10 || listDisplayAmount > 50) {
				listDisplayAmount = 10;
			}
		}
		if (colIndex != null) {
			column = Integer.parseInt(colIndex);
			if (column < 0 || column > 8)
				column = 0;
		}
		if (sortDirection != null) {
			if (!sortDirection.equals("asc"))
				dir = "desc";
		}

		long totalRecords = reservationDAO.getReservationsNumber();
		
		

		long RECORD_SIZE = listDisplayAmount;

		String colName = columnNames[column];		
		long INITIAL = start;

		DataTableResult dataResult = new DataTableResult();
		List<Reservation> reservations = reservationDAO.getAllReserved(GLOBAL_SEARCH_TERM, colName, dir, INITIAL,
				RECORD_SIZE);
		dataResult.setAaData(reservations);
		dataResult.setiTotalRecords(totalRecords);
		dataResult.setiTotalDisplayRecords(reservations.size());
		
		LOGGER.info(String.valueOf(totalRecords));
		LOGGER.info(reservations.toString());

		return Response.ok(reservations).build();
	}

}
