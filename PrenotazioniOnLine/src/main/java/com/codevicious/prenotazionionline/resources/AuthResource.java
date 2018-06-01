package com.codevicious.prenotazionionline.resources;

import java.sql.Timestamp;
import java.util.Optional;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;
import org.skife.jdbi.v2.DBI;

import com.codevicious.prenotazionionline.dao.AuthTokenDAO;
import com.codevicious.prenotazionionline.dao.UserDAO;
import com.codevicious.prenotazionionline.helper.TokenCredentials;
import com.codevicious.prenotazionionline.representations.AuthToken;

@Produces(MediaType.APPLICATION_JSON)
@Path("/authorize")
public class AuthResource {

	private UserDAO userDAO;
	private AuthTokenDAO authTokenDAO;

	public AuthResource(DBI jdbi) {
		this.userDAO = jdbi.onDemand(UserDAO.class);
		this.authTokenDAO = jdbi.onDemand(AuthTokenDAO.class);
	}

	@POST
	public Response login(@NotEmpty @FormParam("username") String username,
			@NotEmpty @FormParam("password") String password) {

		boolean isValid = (userDAO.checkUser(username, password) == 1);

		if (isValid) {

			long userID = userDAO.getUserID(username);
			String Token = TokenCredentials.generateRandomToken();
			Timestamp expires = new Timestamp(System.currentTimeMillis());

			long tokenID = authTokenDAO.insertToken(userID, Token, expires);

			Response.ok(new AuthToken(tokenID, Token, userID, expires)).build();
		}

		return Response.status(Response.Status.UNAUTHORIZED).build();

	}

}
