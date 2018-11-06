package com.codevicious.prenotazionionline.resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.PrenotazioniOnLineConfiguration;
import com.codevicious.prenotazionionline.dao.AuthTokenDAO;
import com.codevicious.prenotazionionline.dao.UserDAO;
import com.codevicious.prenotazionionline.helper.PrenotazioniOnlineStatics;

import com.codevicious.prenotazionionline.representations.User;

import io.dropwizard.auth.Auth;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)

public class UserProfileResource {

	private final UserDAO userDAO;
	private final AuthTokenDAO authTokenDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileResource.class);

	public UserProfileResource(DBI jdbi, PrenotazioniOnLineConfiguration configuration) {
		authTokenDAO = jdbi.onDemand(AuthTokenDAO.class);
		userDAO = jdbi.onDemand(UserDAO.class);
	}

	@GET
	public Response getReservations(@Auth User user) {

		return Response.ok(user).build();

	}

	@GET
	@Path("/All") // retrieve all users paginated
	public Response getReservations(@Context UriInfo ui) {
		// retrieve All the users
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

		String pageNo = "0";
		String pageSize = "10";
		String column = "surname";
		String sortDirection = "asc";

		
		if (!queryParams.isEmpty()) {
			pageNo = queryParams.get("start").get(0);
			pageSize = queryParams.get("length").get(0);
			column = queryParams.get("column").get(0);
			sortDirection = "asc";
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
			if (listDisplayAmount < 10 || listDisplayAmount > 50) {
				listDisplayAmount = 10;
			}
		}

		Optional<List<User>> Users = Optional.ofNullable(userDAO.getUsers(column, sortDirection, start, listDisplayAmount));

		return Response.ok(Users.get()).build();

	}

}
