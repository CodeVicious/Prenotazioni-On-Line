package com.codevicious.prenotazionionline.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.PrenotazioniOnLineConfiguration;
import com.codevicious.prenotazionionline.dao.AuthTokenDAO;
import com.codevicious.prenotazionionline.dao.UserDAO;
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
	public Response getReservations(@Auth User user ) {
		// retrieve information about the user
		// ...
		
		return Response.ok(user).build();

	}



}
