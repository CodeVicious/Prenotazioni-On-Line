package com.codevicious.prenotazionionline.dao;

import java.util.List;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.codevicious.prenotazionionline.dao.mappers.ReservationMapper;
import com.codevicious.prenotazionionline.helper.PrenotazioniOnlineStatics;
import com.codevicious.prenotazionionline.representations.Reservation;


public interface ReservationDAO {

	@Mapper(ReservationMapper.class)
	@SqlQuery(PrenotazioniOnlineStatics.RESERVATION_BY_ID)
	Reservation getReservationById(@Bind("id") long id);

	@Mapper(ReservationMapper.class)
	@SqlQuery("select * from resrvations where limit 100")
	List<Reservation> getAllReservations();

	@Mapper(ReservationMapper.class)
	@SqlQuery(PrenotazioniOnlineStatics.ALL_RESERVATIONS_TABLEFILTER_PAGINATED)
	List<Reservation> getAllReservedPaginated(@Bind("GlobalSearch") String GlobalSearch,
			@Bind("columnName") String columnName, @Bind("direction") String direction, @Bind("initial") long initial,
			@Bind("recordSize") long recordSize);

	@SqlQuery(PrenotazioniOnlineStatics.COUNT_RESERVATIONS_FILTERED)
	long getReservedFilteredNumber(@Bind("GlobalSearch") String GlobalSearch, @Bind("columnName") String columnName,
			@Bind("direction") String direction);

	@SqlQuery(PrenotazioniOnlineStatics.COUNT_ALL_RESERVATIONS)
	long getReservationsNumber();

	@GetGeneratedKeys
	@SqlUpdate(PrenotazioniOnlineStatics.INSERT_INTO_RESERVATION)
	int createReservation(@Bind("name") String name, @Bind("surname") String surname, @Bind("email") String email,
			@Bind("address") String address, @Bind("borndate") DateTime borndate, @Bind("tel") String tel,
			@Bind("fkAvailability") long fkAvailability, @Bind("reservationdate") DateTime reservationDate,
			@Bind("notes") String notes);

	@SqlUpdate(PrenotazioniOnlineStatics.DELETE_FROM_RESERVATION_BY_ID)
	void deleteReservation(@Bind("id") long id);

}
