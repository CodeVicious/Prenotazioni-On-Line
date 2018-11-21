package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.codevicious.prenotazionionline.representations.User;

public class UserMapper implements ResultSetMapper<User> {

	public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		return new User(r.getLong("ID"), r.getString("name"), r.getString("surname"), r.getString("username"),
				r.getString("email"),r.getString("telephone"), r.getString("mobile"),r.getString("password"));

	}

}