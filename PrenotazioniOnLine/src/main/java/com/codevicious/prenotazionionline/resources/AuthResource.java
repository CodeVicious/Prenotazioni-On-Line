	package com.codevicious.prenotazionionline.resources;

import java.sql.Timestamp;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.validator.constraints.NotEmpty;
import org.skife.jdbi.v2.DBI;

import com.codevicious.prenotazionionline.dao.AuthTokenDAO;
import com.codevicious.prenotazionionline.dao.UserDAO;
import com.codevicious.prenotazionionline.helper.TokenCredentials;
import com.codevicious.prenotazionionline.representations.AuthToken;

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
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response login(@NotEmpty @FormDataParam("username") String username,
			@NotEmpty @FormDataParam("password") String password) {

		boolean isValid = (userDAO.checkUser(username, password) == 1);

		if (isValid) {

			long userID = userDAO.getUserID(username);
			Timestamp expires = new Timestamp(System.currentTimeMillis()+5*60*1000); // 5 minutes ahead
			
			String Token = TokenCredentials.generateRandomToken(username,expires);
			

			long tokenID = authTokenDAO.insertToken(userID, Token, expires);

			return Response.ok(new AuthToken(tokenID, Token, userID, expires)).build();
		}

		return Response.status(Response.Status.UNAUTHORIZED).build();

	}

}
