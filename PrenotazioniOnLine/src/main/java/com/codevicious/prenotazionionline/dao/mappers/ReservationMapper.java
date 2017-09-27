package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.codevicious.prenotazionionline.representations.Reservation;

public class ReservationMapper implements ResultSetMapper<Reservation> {

	public Reservation map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter fmt2 = DateTimeFormat.forPattern("yyyy-MM-dd");
		
		
		return new Reservation(r.getLong("id"), fmt.parseDateTime(r.getString("reservationdate")), r.getString("name"),
				r.getString("surname"), r.getString("address"), r.getString("email"), fmt2.parseDateTime(r.getString("borndate")), r.getString("phone"),
				r.getString("notes"), r.getLong("fkavailability"));

		// Availability(r.getInt("ID"), fmt.parseDateTime(r.getString("Data")),
		// r.getInt("FKplaces"), r.getBoolean("reserved"),
		// r.getString("name"),r.getString("color"));
	}

}