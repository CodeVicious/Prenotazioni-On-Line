package com.codevicious.prenotazionionline.helper;

public class PrenotazioniOnlineStatics {

	//ReservationsDAO
	
	public static final String RESERVATION_BY_ID = "select * from reservations where ID = :id";

	public static final String ALL_RESERVATIONS_LIMIT_1000 = "select * from resrvations where limit 1000";

	public static final String ALL_RESERVATIONS_TABLEFILTER_PAGINATED = "select * from reservations where (id like concat('%',:GlobalSearch,'%') OR name like concat('%',:GlobalSearch,'%') "
			+ "OR surname like concat('%',:GlobalSearch,'%') OR email like concat('%',:GlobalSearch,'%') ) " // 
			+ "ORDER BY :columnName :direction" + " LIMIT :initial , :recordSize";

	public static final String COUNT_RESERVATIONS_FILTERED = "select COUNT(*) as count from reservations where (ID like concat('%',:GlobalSearch,'%') OR name like concat('%',:GlobalSearch,'%') "
			+ "OR surname like concat('%',:GlobalSearch,'%') OR email like concat('%',:GlobalSearch,'%') )  ";

	public static final String COUNT_ALL_RESERVATIONS = "SELECT COUNT(*) as count FROM reservations ";

	public static final String INSERT_INTO_RESERVATION = " INSERT INTO `reservations`(`ID`, `name`, `surname`, `email`, `address`, `borndate`, `phone`, `fkavailability`, `reservationdate`, `notes`) "
			+ "VALUES (NULL,:name,:surname,:email,:address,:borndate,:tel,:fkAvailability,:reservationdate,:notes)";

	public static final String DELETE_FROM_RESERVATION_BY_ID = "DELETE FROM reservation WHERE ID = :id";

}
