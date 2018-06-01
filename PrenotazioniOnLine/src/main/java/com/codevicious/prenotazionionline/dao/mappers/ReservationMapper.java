package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.codevicious.prenotazionionline.helper.PrenotazioniOnlineStatics;
import com.codevicious.prenotazionionline.representations.Reservation;

public class ReservationMapper implements ResultSetMapper<Reservation> {

	public Reservation map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		return new Reservation(r.getLong("id"),
				PrenotazioniOnlineStatics.longDateFormatTime.parseDateTime(r.getString("reservationdate")),
				r.getString("name"), r.getString("surname"), r.getString("address"), r.getString("email"),
				PrenotazioniOnlineStatics.shortDateFormatyyyyMMdd.parseDateTime(r.getString("borndate")),
				r.getString("phone"), r.getString("notes"), r.getLong("fkavailability"),
				PrenotazioniOnlineStatics.shortDateFormatyyyyMMdd.parseDateTime(r.getString("availabilitydatereserved")),
				PrenotazioniOnlineStatics.fullTimeFormat.parseDateTime(r.getString("availabilitytimereserved"))
				);

	}

}