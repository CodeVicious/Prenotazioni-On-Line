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
import com.codevicious.prenotazionionline.representations.Role;
import com.codevicious.prenotazionionline.representations.Sector;

@Path("/role")
@Produces(MediaType.APPLICATION_JSON)

public class RolesResource {
	
	private final UserDAO userDAO;
	private final AuthTokenDAO authTokenDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(RolesResource.class);

	public RolesResource(DBI jdbi, PrenotazioniOnLineConfiguration configuration) {
		authTokenDAO = jdbi.onDemand(AuthTokenDAO.class);
		userDAO = jdbi.onDemand(UserDAO.class);
	}
	

	@GET
	@Path("/All") // retrieve all sectors in JSON Tree Format
	public Response getRoles(@Context UriInfo ui) {
		// retrieve All the users
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

		Optional<List<Role>> Roles= Optional
				.ofNullable(userDAO.getRoles());

		return Response.ok(Roles.get()).build();
	}

	
	

}