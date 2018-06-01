package com.codevicious.prenotazionionline.dao;


import java.sql.Timestamp;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface AuthTokenDAO {

	@SqlUpdate("INSERT INTO auth_tokens (user_id, token, expires) VALUES (:userId, :token, TIMESTAMPADD(SECOND, :expires, NOW()))")
	int insertToken(@Bind("userId") long userID, @Bind("token") String token, @Bind("expires") Timestamp timestamp);

}
