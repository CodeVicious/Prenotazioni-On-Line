package com.codevicious.prenotazionionline.auth;

import java.util.Optional;

import org.skife.jdbi.v2.DBI;

import com.codevicious.prenotazionionline.dao.UserDAO;
import com.codevicious.prenotazionionline.representations.User;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

public class IntranetOnLineAuthenticator implements Authenticator<String, User> {
	
	private final UserDAO userDAO;

	public IntranetOnLineAuthenticator(DBI jdbi) {
		userDAO = jdbi.onDemand(UserDAO.class);
	}

	@Override
	public Optional<User> authenticate(String arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}
