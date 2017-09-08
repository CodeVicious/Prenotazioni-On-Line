package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.representations.Places;

public class PlacesMapper implements ResultSetMapper<Places> {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlacesMapper.class);

	public Places map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		
		return new Places(r.getString("ID"), r.getString("name"),r.getString("address"),r.getFloat("lat"),r.getFloat("lon"),r.getString("color") );
	

	}

}