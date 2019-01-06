package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.codevicious.prenotazionionline.representations.Sector;

public class SectorMapper implements ResultSetMapper<Sector> {

	public Sector map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		return new Sector(r.getInt("id"), r.getInt("parentid"), r.getString("code"),r.getString("sector"), r.getString("description"));

	}

}
