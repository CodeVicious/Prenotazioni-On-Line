package com.codevicious.prenotazionionline.resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.PrenotazioniOnLineConfiguration;
import com.codevicious.prenotazionionline.dao.PerformanceDAO;
import com.codevicious.prenotazionionline.helper.PrenotazioniOnlineStatics;
import com.codevicious.prenotazionionline.representations.PerformanceUser;

@Path("/performance")
@Produces(MediaType.APPLICATION_JSON)

public class PerformanceUserResource {

	private final PerformanceDAO performanceDAO;
	private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceUserResource.class);

	public PerformanceUserResource(DBI jdbi, PrenotazioniOnLineConfiguration configuration) {
		performanceDAO = jdbi.onDemand(PerformanceDAO.class);
	}

	@GET
	@Path("/{id}") // retrieve user id
	public Response getUser(@PathParam("id") int id) {

		Optional<PerformanceUser> user = Optional.ofNullable(performanceDAO.getPerfUserByID(id));
		if (user.isPresent())
			return Response.ok(user.get()).build();
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/All") // retrieve all users paginated
	public Response getUsers(@Context UriInfo ui) {
		// retrieve All the users
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

		String pageNo = "0";
		String pageSize = "10";
		String column = "cognome";
		String sortDirection = "asc";

		if (!queryParams.isEmpty()) {
			if (queryParams.containsKey("start"))
				pageNo = queryParams.get("start").get(0);
			if (queryParams.containsKey("length"))
				pageSize = queryParams.get("length").get(0);
			if (queryParams.containsKey("column"))
				column = queryParams.get("column").get(0);
			if (queryParams.containsKey("sort"))
				sortDirection = queryParams.get("sort").get(0);
		}

		int listDisplayAmount = PrenotazioniOnlineStatics.DEFAULT_PAGE_DIMENSION;
		long start = 0;

		if (pageNo != null) {
			start = Integer.parseInt(pageNo);
			if (start < 0) {
				start = 0;
			}
		}
		if (pageSize != null) {
			listDisplayAmount = Integer.parseInt(pageSize);
			if (listDisplayAmount < 10) {
				listDisplayAmount = 10;
			}
		}

		Optional<List<PerformanceUser>> Users = Optional
				.ofNullable(performanceDAO.getPerfUsers(column, sortDirection, start, listDisplayAmount));

		return Response.ok(Users.get()).build();
	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteAvailability(@PathParam("id") long id) {
		// delete the user with the provided id
		// ...
		performanceDAO.deletePerfUser(id);
		return Response.ok().build();
	}

	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") long id, PerformanceUser updateUser) {

		long updatedUserID = performanceDAO.updatePerfUser(updateUser);
		return Response.ok(String.valueOf(updatedUserID)).build();
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(PerformanceUser newUser) {

		LOGGER.debug(newUser.toString());

		long newUserID = performanceDAO.insertUser(newUser);

		return Response.ok(String.valueOf(newUserID)).build();
	}

}
