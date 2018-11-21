package com.codevicious.prenotazionionline.auth;



import java.util.Optional;

import org.skife.jdbi.v2.DBI;

import com.codevicious.prenotazionionline.dao.AuthTokenDAO;
import com.codevicious.prenotazionionline.dao.UserDAO;
import com.codevicious.prenotazionionline.representations.User;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

public class IntranetOnLineAuthenticator implements Authenticator<String, User> {

	private final AuthTokenDAO authTokenDAO;
	private final UserDAO userDAO;

	public IntranetOnLineAuthenticator(DBI jdbi) {
		authTokenDAO = jdbi.onDemand(AuthTokenDAO.class);
		userDAO = jdbi.onDemand(UserDAO.class);
	}

	public Optional<User> authenticate(String auth_token) throws AuthenticationException {

		Optional<Long> authTokenUserId = authTokenDAO.getAtuhToken(auth_token);

		if (authTokenUserId.isPresent()) {
			Optional<User> user = Optional.ofNullable(userDAO.getUserByID(authTokenUserId.get()));
			if (user.isPresent()) {
				///user.get().setRoles(userDAO.getRolesByUserID(authTokenUserId.get()));
				///user.get().setSectors(userDAO.getSectorsByUserID(authTokenUserId.get()));
				return user;
			}

		}

		return Optional.empty();

	}

}
