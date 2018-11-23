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

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.PrenotazioniOnLineConfiguration;
import com.codevicious.prenotazionionline.dao.AuthTokenDAO;
import com.codevicious.prenotazionionline.dao.UserDAO;
import com.codevicious.prenotazionionline.helper.PasswordMD5Converter;
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

	// @GET
	// public Response getReservations(@Auth User user) {
	//
	// return Response.ok(user).build();
	//
	// }

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

		Optional<List<User>> Users = Optional
				.ofNullable(userDAO.getUsers(column, sortDirection, start, listDisplayAmount));

		return Response.ok(Users.get()).build();
	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteAvailability(@PathParam("id") int id) {
		// delete the user with the provided id
		// ...
		userDAO.deleteUser(id);
		return Response.ok().build();
	}


	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") long id,User updateUser) {		
		
		User user = userDAO.getUserByID(id);
		user.setName(updateUser.getName());
		user.setSurname(updateUser.getSurname());
		user.setTelephone(updateUser.getTelephone());
		user.setMobile(updateUser.getMobile());
		user.setPassword(PasswordMD5Converter.getMD5(updateUser.getPassword()));
		
		long updatedUserID  = userDAO.updateUser(user);
		
		return Response.ok(String.valueOf(updatedUserID)).build();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User newUser) {		
		
		LOGGER.debug(newUser.toString());
		
		newUser.setPassword(PasswordMD5Converter.getMD5(newUser.getPassword()));
		
		long newUserID  = userDAO.insertUser(newUser);
		
		return Response.ok(String.valueOf(newUserID)).build();
	}
}

	// var user = _context.Users.Find(userParam.Id);
	//
	// if (user == null)
	// throw new AppException("User not found");
	//
	// if (userParam.Username != user.Username)
	// {
	// // username has changed so check if the new username is already taken
	// if (_context.Users.Any(x => x.Username == userParam.Username))
	// throw new AppException("Username " + userParam.Username + " is already
	// taken");
	// }
	//
	// // update user properties
	// user.FirstName = userParam.FirstName;
	// user.LastName = userParam.LastName;
	// user.Username = userParam.Username;
	//
	// // update password if it was entered
	// if (!string.IsNullOrWhiteSpace(password))
	// {
	// byte[] passwordHash, passwordSalt;
	// CreatePasswordHash(password, out passwordHash, out passwordSalt);
	//
	// user.PasswordHash = passwordHash;
	// user.PasswordSalt = passwordSalt;
	// }
	//
	// _context.Users.Update(user);
	// _context.SaveChanges();

