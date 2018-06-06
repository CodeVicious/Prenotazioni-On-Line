	package com.codevicious.prenotazionionline.resources;

import javax.ws.rs.Consumes;
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
import com.codevicious.prenotazionionline.representations.AuthTokenAnswer;

@Path("/authorize")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

	private UserDAO userDAO;
	private AuthTokenDAO authTokenDAO;

	public AuthResource(DBI jdbi) {
		this.userDAO = jdbi.onDemand(UserDAO.class);
		this.authTokenDAO = jdbi.onDemand(AuthTokenDAO.class);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public Response login(@NotEmpty @FormParam("username") String username,
			@NotEmpty @FormParam("password") String password) {

		boolean isValid = (userDAO.checkUser(username, password) == 1);

		if (isValid) {

			long userID = userDAO.getUserID(username);
			int expires = 3600; // 5 minutes ahead
			
			String Token = TokenCredentials.generateRandomToken(username,expires);			

			long tokenID = authTokenDAO.insertToken(userID, Token, expires);

			return Response.ok(new AuthTokenAnswer(Token,"bearer", expires)).build();
		}

		return Response.status(Response.Status.UNAUTHORIZED).build();

	}

}
