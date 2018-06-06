package com.codevicious.prenotazionionline.dao;


import java.util.Optional;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;



public interface AuthTokenDAO {

	@SqlUpdate("INSERT INTO auth_tokens (user_id, auth_token, expires) VALUES (:userId, :token, :expires)")
	int insertToken(@Bind("userId") long userID, @Bind("token") String token, @Bind("expires") int seconds);
	
	
	@SqlQuery("select user_id from auth_tokens where auth_token = :authtoken")
	@SingleValueResult
	Optional<Long> getAtuhToken(@Bind("authtoken") String username);
}
