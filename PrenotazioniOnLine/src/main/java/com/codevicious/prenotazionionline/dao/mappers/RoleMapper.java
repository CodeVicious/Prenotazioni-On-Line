package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.codevicious.prenotazionionline.representations.Role;

public class RoleMapper implements ResultSetMapper<Role> {

	public Role map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		return new Role(r.getInt("id"), r.getString("role"), r.getString("description"), r.getString("sigla"));

	}

}
