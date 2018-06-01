package com.codevicious.prenotazionionline.auth;

import org.skife.jdbi.v2.DBI;

import com.codevicious.prenotazionionline.dao.UserDAO;
import com.codevicious.prenotazionionline.representations.User;

import io.dropwizard.auth.Authorizer;

public class IntranetAuthorizer implements Authorizer<User> {
	private final UserDAO userDAO;

	public IntranetAuthorizer(DBI jdbi) {
		userDAO = jdbi.onDemand(UserDAO.class);
	}

	@Override
	public boolean authorize(User arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
