package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import com.codevicious.prenotazionionline.representations.PerformanceUser;

public class PerformanceMapper implements ResultSetMapper<PerformanceUser> {

	public PerformanceUser map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		return new PerformanceUser(r.getLong("id"), r.getInt("anno"), r.getDate("inizio_incarico"),
				r.getDate("fine_incarico"), r.getInt("giorni_lavorati"), r.getBoolean("CP"),
				r.getString("responsabilita_speciali"), r.getString("nome"), r.getString("cognome"), r.getString("DO"),
				r.getString("note_Informative_1"), r.getString("note_Informative_2"),
				r.getDouble("percentuale_comando_effettivo"), r.getDouble("percentuale_do"),
				r.getDouble("presenza_giuridica"), r.getString("capitolo_standard"),
				r.getString("capitolo_oneri_standard"), r.getString("capitolo_irap_standard"), r.getLong("fk_sectors"),
				r.getLong("fk_profilo_professionale"), r.getLong("fk_categoria_giuridica"), r.getLong("fk_user"));

	}

}