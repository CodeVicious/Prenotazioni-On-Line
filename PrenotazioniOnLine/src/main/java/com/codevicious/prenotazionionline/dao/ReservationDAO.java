package com.codevicious.prenotazionionline.dao;

import java.util.List;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.codevicious.prenotazionionline.dao.mappers.ReservationMapper;
import com.codevicious.prenotazionionline.representations.Reservation;

public interface ReservationDAO {

	@Mapper(ReservationMapper.class)
	@SqlQuery("select * from reservations where ID = :id")
	Reservation getReservationById(@Bind("id") long id);

	@Mapper(ReservationMapper.class)
	@SqlQuery("select * from resrvations where limit 100")
	List<Reservation> getAllReservations();
	
	@Mapper(ReservationMapper.class)
	@SqlQuery("select * from reservations where id like concat('%',:GlobalSearch,'%') OR name like concat('%',:GlobalSearch,'%') "
			+ "or surname like concat('%',:GlobalSearch,'%') or email like concat('%',:GlobalSearch,'%') "
			+ "ORDER BY :columnName :direction" + " LIMIT :initial , :recordSize")
	List<Reservation> getAllReserved(@Bind("GlobalSearch") String GlobalSearch,
			@Bind("columnName") String columnName,
			@Bind("direction") String direction,
			@Bind("initial") long initial,
			@Bind("recordSize") long recordSize);
	

	@GetGeneratedKeys
	@SqlUpdate(" INSERT INTO `reservations`(`ID`, `name`, `surname`, `email`, `address`, `borndate`, `tel`, `FK_availability`, `reservationdate`, `notes`) "
			+ "VALUES (NULL,:name,:surname,:email,:address,:borndate,:tel,:fkAvailability,:reservationdate,:notes)")
	int createReservation(@Bind("name") String name, @Bind("surname") String surname, @Bind("email") String email,
			@Bind("address") String address, @Bind("borndate") DateTime borndate, @Bind("tel") String tel,
			@Bind("fkAvailability") long fkAvailability, @Bind("reservationdate") DateTime reservationDate,
			@Bind("notes") String notes);

	@SqlUpdate("DELETE FROM reservation WHERE ID = :id")
	void deleteReservation(@Bind("id") long id);

	@SqlQuery("SELECT COUNT(*) as count FROM reservations INNER JOIN availability on reservations.FK_availability = availability.ID")
	long getReservationsNumber();

}
