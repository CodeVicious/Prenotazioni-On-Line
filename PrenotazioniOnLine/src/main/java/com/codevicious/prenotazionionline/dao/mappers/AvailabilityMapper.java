package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.codevicious.prenotazionionline.representations.Availability;

public class AvailabilityMapper implements ResultSetMapper<Availability>{

	public Availability map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
				
		return new Availability(r.getInt("ID"), fmt.parseDateTime(r.getString("Data")), r.getInt("FKplaces"), r.getBoolean("reserved"), r.getString("name"),r.getString("color"));
	}

}