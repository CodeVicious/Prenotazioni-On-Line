package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.codevicious.prenotazionionline.helper.PrenotazioniOnlineStatics;
import com.codevicious.prenotazionionline.representations.Availability;

public class AvailabilityMapper implements ResultSetMapper<Availability> {

	public Availability map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		return new Availability(r.getInt("ID"),
				PrenotazioniOnlineStatics.longDateFormatTime.parseDateTime(r.getString("Data")), r.getInt("FKplaces"),
				r.getBoolean("reserved"), r.getString("name"), r.getString("color"));
	}

}