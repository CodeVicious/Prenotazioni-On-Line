package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.codevicious.prenotazionionline.representations.SchedaPerformance;

public class SchedaPerformanceMapper implements ResultSetMapper<SchedaPerformance> {

	public SchedaPerformance map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		return new SchedaPerformance(r.getLong("id"), r.getString("descrizione_obiettivo_1"),
				r.getString("descrizione_obiettivo_2"), r.getString("descrizione_obiettivo_3"),
				r.getString("indicatore_desc_1"), r.getString("indicatore_desc_2"), r.getString("indicatore_desc_3"),
				r.getInt("peso_1"), r.getInt("peso_2"), r.getInt("peso_3"), r.getFloat("raggiungimento_1"),
				r.getFloat("raggiungimento_2"), r.getFloat("raggiungimento_3"), r.getInt("comp_1"), r.getInt("comp_2"),
				r.getInt("comp_3"), r.getInt("comp_4"), r.getInt("relaz_1"), r.getInt("relaz_2"), r.getInt("relaz_3"),
				r.getInt("relaz_4"), r.getInt("del_saper_1"), r.getInt("del_saper_2"), r.getInt("del_saper_3"),
				r.getInt("del_saper_4"));
	}

}