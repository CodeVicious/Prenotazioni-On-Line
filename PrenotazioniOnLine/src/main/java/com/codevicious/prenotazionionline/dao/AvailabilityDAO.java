package com.codevicious.prenotazionionline.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.codevicious.prenotazionionline.dao.mappers.AvailabilityMapper;
import com.codevicious.prenotazionionline.representations.Availability;



public interface AvailabilityDAO {

	@Mapper(AvailabilityMapper.class)
	@SqlQuery("select * from availability where ID = :id")
	Availability getAvailabilityById(@Bind("id") int id);
	
	@Mapper(AvailabilityMapper.class)
	@SqlQuery("select * from availability where 1")
	List<Availability> getAvailability();
	
	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO availability (ID, Data, Ora, FK_places) VALUES (NULL,:Data,:Ora,:FK_places)")
	int createAvailability(@Bind("Data") Date Data, @Bind("Ora") Time Ora, @Bind("FK_places") int FK_places);
	
	@SqlUpdate("UPDATE availability SET Data=:Data, Ora=:Ora,FK_places=:FK_places WHERE ID = :id")
	void updateAvailability(@Bind("id") int id, @Bind("Data") Date Data, @Bind("Ora") Time Ora, @Bind("FK_places") int FK_places);
	
	@SqlUpdate("DELETE FROM availability WHERE ID = :id")
	void deleteContact(@Bind("id") int id);
	
	}
