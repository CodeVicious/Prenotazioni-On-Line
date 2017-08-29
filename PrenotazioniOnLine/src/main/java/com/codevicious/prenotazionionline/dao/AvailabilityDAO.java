package com.codevicious.prenotazionionline.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.codevicious.prenotazionionline.dao.mappers.AvailabilityMapper;
import com.codevicious.prenotazionionline.dao.mappers.PlacesMapper;
import com.codevicious.prenotazionionline.representations.Availability;
import com.codevicious.prenotazionionline.representations.Places;



public interface AvailabilityDAO {

	@Mapper(AvailabilityMapper.class)
	@SqlQuery("select * from availability where ID = :id")
	Availability getAvailabilityById(@Bind("id") int id);
	
	@Mapper(AvailabilityMapper.class)
	@SqlQuery("select * from availability where 1")
	List<Availability> getAllAvailability();
	
	@Mapper(PlacesMapper.class)
	@SqlQuery("SELECT DISTINCT places.ID as ID, name as place FROM places INNER JOIN availability on places.ID = availability.FKplaces ORDER BY place")
	List<Places> getPlaces();
	
	
	@Mapper(AvailabilityMapper.class)
	@SqlQuery("SELECT availability.* FROM availability INNER JOIN places on places.ID = availability.FKplaces " + 
			"			WHERE (MONTH(availability.Data) = :month AND availability.FKplaces = :id AND YEAR(availability.Data) = :year" + 
			"			ORDER BY availability.Data\r\n")
	
	List<Availability> getAvailabilityByMonthYearPlace();
	
	
	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO availability (ID, Data, Ora, FK_places) VALUES (NULL,:Data,:Ora,:FKplaces)")
	int createAvailability(@Bind("Data") Date Data, @Bind("Ora") Time Ora, @Bind("FK_places") int FK_places);
	
	@SqlUpdate("UPDATE availability SET Data=:Data, Ora=:Ora,FKplaces=:FKplaces WHERE ID = :id")
	void updateAvailability(@Bind("id") int id, @Bind("Data") Date Data, @Bind("Ora") Time Ora, @Bind("FKplaces") int FKplaces);
	
	@SqlUpdate("DELETE FROM availability WHERE ID = :id")
	void deleteContact(@Bind("id") int id);

	
	
	}