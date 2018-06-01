package com.codevicious.prenotazionionline.helper;

import java.util.Locale;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class PrenotazioniOnlineStatics {

	// ReservationsDAO

	public static final String RESERVATION_BY_ID = "select * from reservations where ID = :id";

	public static final String ALL_RESERVATIONS_LIMIT_1000 = "select * from resrvations where limit 1000";

	public static final String ALL_RESERVATIONS_TABLEFILTER_PAGINATED = "select r.ID, r.name, r.surname, r.email, r.address, r.borndate, r.phone, r.reservationdate, r.notes ,r.fkavailability, DATE(avail.Data) as availabilitydatereserved, TIME(avail.Data) as availabilitytimereserved "			
			+ "from reservations as r INNER JOIN ( select a.*,p.name,p.color from availability as a INNER JOIN places as p on a.FKplaces = p.ID) as avail "
			+ "on r.fkavailability = avail.ID where r.ID like  concat('%',:GlobalSearch,'%') OR r.name like concat('%',:GlobalSearch,'%') "
			+ "OR r.surname like concat('%',:GlobalSearch,'%') OR r.email like concat('%',:GlobalSearch,'%')  "
			+ "ORDER BY <columnName> <direction> LIMIT :initial , :recordSize";

	public static final String ALL_RESERVATIONS_AVAILABILITY_TABLEFILTER_PAGINATED = "select r.ID, r.name, r.surname, r.email, r.address, r.borndate, r.phone, r.reservationdate, r.notes ,r.fkavailability,"
			+ "avail.ID, avail.Data, avail.FKplaces, avail.reserved, avail.name, avail.color "
			+ "from reservations as r INNER JOIN ( select a.*,p.name,p.color from availability as a INNER JOIN places as p on a.FKplaces = p.ID) as avail "
			+ "on r.fkavailability = avail.ID where r.ID like  concat('%',:GlobalSearch,'%') OR name like concat('%',:GlobalSearch,'%') "
			+ "OR surname like concat('%',:GlobalSearch,'%') OR email like concat('%',:GlobalSearch,'%')  "
			+ "ORDER BY <columnName> <direction> LIMIT :initial , :recordSize";

	public static final String COUNT_RESERVATIONS_FILTERED = "select COUNT(*) as count from reservations where (ID like concat('%',:GlobalSearch,'%') OR name like concat('%',:GlobalSearch,'%') "
			+ "OR surname like concat('%',:GlobalSearch,'%') OR email like concat('%',:GlobalSearch,'%') )  ";

	public static final String COUNT_ALL_RESERVATIONS = "SELECT COUNT(*) as count FROM reservations ";

	public static final String INSERT_INTO_RESERVATION = " INSERT INTO `reservations`(`ID`, `name`, `surname`, `email`, `address`, `borndate`, `phone`, `fkavailability`, `reservationdate`, `notes`) "
			+ "VALUES (NULL,:name,:surname,:email,:address,:borndate,:tel,:fkAvailability,:reservationdate,:notes)";

	public static final String DELETE_FROM_RESERVATION_BY_ID = "DELETE FROM reservation WHERE ID = :id";

	// DateTime Joda Formatters

	public static final DateTimeFormatter fullItalianDateFormat = DateTimeFormat.fullDateTime()
			.withLocale(Locale.ITALIAN);

	public static final DateTimeFormatter shortDateFormatddMMyyyy = DateTimeFormat.forPattern("dd/MM/yyyy");

	public static final DateTimeFormatter shortDateFormatyyyyMMdd = DateTimeFormat.forPattern("yyyy-MM-dd");

	public static final DateTimeFormatter longDateFormatTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	public static final DateTimeFormatter fullTimeFormat = DateTimeFormat.forPattern("HH:mm:ss");
}
