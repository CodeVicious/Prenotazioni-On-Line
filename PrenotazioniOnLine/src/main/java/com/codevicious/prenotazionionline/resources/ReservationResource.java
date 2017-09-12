package com.codevicious.prenotazionionline.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.dao.ReservationDAO;
import com.codevicious.prenotazionionline.representations.Reservation;

@Path("/prenotazioni")
@Produces(MediaType.APPLICATION_JSON)

public class ReservationResource {

	private final ReservationDAO reservationDAO;
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationResource.class);

	public ReservationResource(DBI jdbi) {
		reservationDAO = jdbi.onDemand(ReservationDAO.class);
	}

	@GET
	public Response getReservations(@QueryParam("place") String place, @QueryParam("start") String start, @QueryParam("end") String end) {
		// retrieve information about all the availabilities
		// ...
				
		List<Reservation> reservation = reservationDAO.getReservations();
		return Response.noContent().build();
	}


	@POST
	public Response createReservation(Reservation reservation) throws URISyntaxException {
		// store the new reservation
		// ...

		int newReservationID = reservationDAO.createReservation();

		return Response.created(new URI(String.valueOf(newReservationID))).build();
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
