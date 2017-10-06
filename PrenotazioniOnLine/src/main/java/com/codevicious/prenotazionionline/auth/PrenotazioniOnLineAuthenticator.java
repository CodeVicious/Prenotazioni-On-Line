package com.codevicious.prenotazionionline.auth;



import java.util.Optional;

import org.skife.jdbi.v2.DBI;

import com.codevicious.prenotazionionline.dao.UserDAO;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;


public class PrenotazioniOnLineAuthenticator implements Authenticator<BasicCredentials, User> {
	
	private final UserDAO userDAO;

	public PrenotazioniOnLineAuthenticator(DBI jdbi) {
		userDAO = jdbi.onDemand(UserDAO.class);
	}

	public Optional<User> authenticate(BasicCredentials c) throws AuthenticationException {
		boolean isValid = (userDAO.getUser(c.getUsername(), PasswordMD5Converter.getMD5(c.getPassword())) == 1);
		if (isValid) {
			return Optional.of(new User(c.getUsername())); 
		}
		return Optional.empty();
	}

}
