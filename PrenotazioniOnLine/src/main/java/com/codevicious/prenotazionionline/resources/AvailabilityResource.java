package com.codevicious.prenotazionionline.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.dao.AvailabilityDAO;
import com.codevicious.prenotazionionline.representations.Availability;
import com.codevicious.prenotazionionline.representations.Places;

@Path("/disponibilita")
@Produces(MediaType.APPLICATION_JSON)

public class AvailabilityResource {

	private final AvailabilityDAO availabilityDAO;
	private static final Logger LOGGER = LoggerFactory.getLogger(AvailabilityResource.class);

	public AvailabilityResource(DBI jdbi) {
		availabilityDAO = jdbi.onDemand(AvailabilityDAO.class);
	}

	@GET
	public Response getAvailabilityYMP(@QueryParam("place") String place, @QueryParam("start") String start, @QueryParam("end") String end) {
		// retrieve information about all the availabilities
		// ...
				
		List<Availability> availabiliy = availabilityDAO.getAvailabilityByMonthYearPlace(start, end, place);
		return Response.ok(availabiliy).build();
	}

	@GET
	@Path("/places")
	public Response getAllPlaces() {
		// retrieve information about all the availabilities
		// ...

		List<Places> places = availabilityDAO.getPlaces();
		LOGGER.info("Logger Places " + places.toString());
		return Response.ok(places).build();
	}

	@GET
	@Path("/all")
	public Response getAllAvailability() {
		// retrieve information about all the availabilities
		// ...

		List<Availability> availabiliy = availabilityDAO.getAllAvailability();
		return Response.ok(availabiliy).build();
	}

	@GET
	@Path("/{id}")
	public Response getAvailability(@PathParam("id") int id) {
		// retrieve information about the avilability with the provided id
		// ...

		Availability availabiliy = availabilityDAO.getAvailabilityById(id);
		return Response.ok(availabiliy).build();
	}

	@POST
	public Response createAvailability(Availability availability) throws URISyntaxException {
		// store the new availability
		// ...

		int newAvailabilityID = availabilityDAO.createAvailability(availability.getData(), availability.getOra(),
				availability.getfKplaces());

		return Response.created(new URI(String.valueOf(newAvailabilityID))).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteAvailability(@PathParam("id") int id) {
		// delete the contact with the provided id
		// ...
		availabilityDAO.deleteContact(id);
		return Response.noContent().build();
	}

	@PUT
	@Path("/{id}")
	public Response updateContact(@PathParam("id") int id, Availability availability) {
		// update the contact with the provided ID
		// ...
		availabilityDAO.updateAvailability(id, availability.getData(), availability.getOra(),
				availability.getfKplaces());
		return Response
				.ok(new Availability(id, availability.getData(), availability.getOra(), availability.getfKplaces(),availability.getName(),availability.getColor()))
				.build();

	}

}
