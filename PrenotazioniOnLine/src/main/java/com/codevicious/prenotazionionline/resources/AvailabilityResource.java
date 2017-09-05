package com.codevicious.prenotazionionline.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.codevicious.prenotazionionline.dao.AvailabilityDAO;
import com.codevicious.prenotazionionline.representations.Availability;
import com.codevicious.prenotazionionline.representations.Place;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/disponibilita")
@Produces(MediaType.APPLICATION_JSON)

public class AvailabilityResource {

	private final AvailabilityDAO availabilityDAO;
	private static final Logger LOGGER = LoggerFactory.getLogger(AvailabilityResource.class);

	public AvailabilityResource(AvailabilityDAO availabilityDAO) {
		this.availabilityDAO = availabilityDAO;
	}

	@GET
	@Timed
	@UnitOfWork
	public Response getAvailabilityByMonthYearPlace(@QueryParam("year") String year, @QueryParam("month") String month,
			@DefaultValue("0") @QueryParam("place") String place) {

		// retrieve information about all the availabilities for a certain
		// year/month/place
		// ...

		if (year == null)
			year = String.valueOf(Year.now().getValue());
		if (month == null)
			month = String.valueOf(YearMonth.now().getMonthValue());

		List<Availability> availabiliy = availabilityDAO.getAvailabilityByMonthYearPlace(year, month, place);
		return Response.ok(availabiliy).build();
	}

	@GET
	@Path("/places")
	@Timed
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPlaces() {
		// retrieve information about all the availabilities
		// ...

		List<Place> places = availabilityDAO.getPlaces();
		for (Place p : places){
			System.out.println(p.toString());			
		}
			
		
		return Response.ok(places).build();
	}

	@GET
	@Path("/{id}")
	@Timed
	@UnitOfWork
	public Response getAvailability(@PathParam("id") long id) {
		// retrieve information about the avilability with the provided id
		// ...

		Availability availabiliy = availabilityDAO.getAvailabilityById(id);
		return Response.ok(availabiliy).build();
	}

	@POST
	@Timed
	@UnitOfWork
	public Response createAvailability(Availability availability) throws URISyntaxException {
		// store the new availability
		// ...

		int newAvailabilityID = availabilityDAO.createAvailability(availability.getData(), availability.getPlace());

		return Response.created(new URI(String.valueOf(newAvailabilityID))).build();
	}

	@DELETE
	@Path("/{id}")
	@Timed
	@UnitOfWork
	public Response deleteAvailability(@PathParam("id") int id) {
		// delete the contact with the provided id
		// ...
		availabilityDAO.deleteContact(id);
		return Response.noContent().build();
	}

	@PUT
	@Path("/{id}")
	@Timed
	@UnitOfWork
	public Response updateContact(@PathParam("id") int id, Availability availability) {
		// update the contact with the provided ID
		// ...
		availabilityDAO.updateAvailability(id, availability.getData(), availability.getPlace());
		return Response
				.ok(new Availability(availability.getData(),availability.getPlace()))
				.build();

	}

}
