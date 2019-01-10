package com.codevicious.prenotazionionline.dao;

import java.util.List;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import com.codevicious.prenotazionionline.dao.mappers.PerformanceMapper;
import com.codevicious.prenotazionionline.dao.mappers.PlacesMapper;
import com.codevicious.prenotazionionline.dao.mappers.RoleMapper;
import com.codevicious.prenotazionionline.dao.mappers.SectorMapper;

import com.codevicious.prenotazionionline.representations.Availability;
import com.codevicious.prenotazionionline.representations.PerformanceUser;
import com.codevicious.prenotazionionline.representations.Places;
import com.codevicious.prenotazionionline.representations.Role;
import com.codevicious.prenotazionionline.representations.Sector;
import com.codevicious.prenotazionionline.representations.User;
@UseStringTemplate3StatementLocator
public interface PerformanceDAO {
	
	@Mapper(PerformanceMapper.class)
	@SqlQuery("select * from profili_performance where id = :perf_user_id")
	PerformanceUser getPerfUserByID(@Bind("perf_user_id") long perf_user_id);

	@Mapper(PerformanceMapper.class)
	@SqlQuery("SELECT id, Anno, inizio_incarico, fine_incarico, giorni_lavorati, CP, Responsabilita_speciali, "
			+ "cognome, nome, DO, Note_Informative_1, Note_Informative_2, percentuale_comando_effettivo, percentuale_do, "
			+ "presenza_giuridica, capitolo_standard, capitolo_oneri_standard, capitolo_irap_standard, "
			+ "fk_sectors, fk_profilo_professionale, fk_categoria_giuridica, "
			+ "fk_user FROM profili_performance ORDER BY <columnName> <direction> LIMIT :recordSize OFFSET :initial")
	List<PerformanceUser> getPerfUsers(@Define("columnName") String columnName, @Define("direction") String direction,
			@Bind("initial") long initial, @Bind("recordSize") long recordSize);


	@SqlUpdate("DELETE from profili_performance where id = :perf_user_id")
	long deletePerfUser(@Bind("perf_user_id") long perf_user_id);

	@SqlUpdate("UPDATE profili_performance SET"			
			+ " Anno=:Anno,inizio_incarico=:inizio_incarico,fine_incarico=:fine_incarico,giorni_lavorati=:giorni_lavorati,"
			+ "CP=:CP,Responsabilita_speciali=:Responsabilita_speciali,nome=:nome,cognome=:cognome,DO=:DO,Note_Informative_1=:Note_Informative_1,"
			+ "Note_Informative_2=:Note_Informative_2,percentuale_comando_effettivo=:percentuale_comando_effettivo,percentuale_do=:percentuale_do,"
			+ "presenza_giuridica=:presenza_giuridica,capitolo_standard=:capitolo_standard,capitolo_oneri_standard=:capitolo_oneri_standard,"
			+ "capitolo_irap_standard=:capitolo_irap_standard,"
			+ "fk_sectors=:fk_sectors,fk_profilo_professionale=:fk_profilo_professionale,"
			+ "fk_categoria_giuridica=:fk_categoria_giuridica,fk_user=:fk_user"
			+ "where id = :id")	
	long updatePerfUser(@BindBean PerformanceUser perfuser);

	@SqlUpdate("INSERT INTO " + 
			"profili_performance(id, Anno, inizio_incarico, fine_incarico, giorni_lavorati, CP, Responsabilita_speciali, "
			+ "nome, cognome, DO, Note_Informative_1, Note_Informative_2, percentuale_comando_effettivo, percentuale_do, "
			+ "presenza_giuridica, capitolo_standard, capitolo_oneri_standard, capitolo_irap_standard, fk_sectors, "
			+ "fk_profilo_professionale, fk_categoria_giuridica, fk_user) " + 
			"VALUES (:Anno, :inizio_incarico, :fine_incarico, :giorni_lavorati, :CP, :Responsabilita_speciali, "
			+ ":nome, :cognome, :DO, :Note_Informative_1, :Note_Informative_2, :percentuale_comando_effettivo, "
			+ ":percentuale_do, :presenza_giuridica, :capitolo_standard, :capitolo_oneri_standard, "
			+ ":capitolo_irap_standard, :fk_sectors, :fk_profilo_professionale, :fk_categoria_giuridica, :fk_user)")
	long insertUser(@BindBean PerformanceUser perfuser);
	
}
