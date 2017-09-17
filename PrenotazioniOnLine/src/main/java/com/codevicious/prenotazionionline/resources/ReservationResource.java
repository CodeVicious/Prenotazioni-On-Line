package com.codevicious.prenotazionionline.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.dao.AvailabilityDAO;
import com.codevicious.prenotazionionline.dao.ReservationDAO;
import com.codevicious.prenotazionionline.representations.Reservation;

@Path("/reservation")
@Produces(MediaType.APPLICATION_JSON)

public class ReservationResource {

	private final ReservationDAO reservationDAO;
	private final AvailabilityDAO availabilityDAO;
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationResource.class);

	public ReservationResource(DBI jdbi) {
		reservationDAO = jdbi.onDemand(ReservationDAO.class);
		availabilityDAO = jdbi.onDemand(AvailabilityDAO.class);
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

}
