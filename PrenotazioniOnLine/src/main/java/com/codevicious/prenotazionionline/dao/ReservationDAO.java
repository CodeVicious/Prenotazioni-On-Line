package com.codevicious.prenotazionionline.dao;

import java.util.List;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.codevicious.prenotazionionline.dao.mappers.PlacesMapper;
import com.codevicious.prenotazionionline.dao.mappers.ReservationMapper;
import com.codevicious.prenotazionionline.representations.Availability;
import com.codevicious.prenotazionionline.representations.Places;
import com.codevicious.prenotazionionline.representations.Reservation;

public interface ReservationDAO {

	@Mapper(ReservationMapper.class)
	@SqlQuery("select * from availability where ID = :id")
	Availability getAvailabilityById(@Bind("id") int id);

	@Mapper(ReservationMapper.class)
	@SqlQuery("select * from availability where 1")
	List<Availability> getAllAvailability();

	@Mapper(PlacesMapper.class)
	@SqlQuery("SELECT DISTINCT places.ID, places.name, places.address, places.lat, places.lon, places.type, places.color  "
			+ "FROM places INNER JOIN availability on places.ID = availability.FKplaces ORDER BY name")
	List<Places> getPlaces();

	@Mapper(ReservationMapper.class)
	@SqlQuery("SELECT availability.*, places.name as name, places.color as color FROM availability INNER JOIN places on places.ID = availability.FKplaces "
			+ "WHERE (availability.Data > :start AND availability.Data < :end AND availability.FKplaces = :id)"
			+ "			ORDER BY availability.Data")
	List<Availability> getAvailabilityByMonthYearPlace(@Bind("start") String start, @Bind("end") String end,
			@Bind("id") String id);

	@GetGeneratedKeys
	@SqlUpdate("INSERT INTO availability (ID, Data, FK_places) VALUES (NULL,:Data, :FKplaces)")
	int createAvailability(@Bind("Data") DateTime Data,  @Bind("FK_places") int FK_places);



	@SqlUpdate("DELETE FROM availability WHERE ID = :id")
	void deleteContact(@Bind("id") int id);

	int createReservation();

	List<Reservation> getReservations();

	void deleteReservation(int id);

	

}
