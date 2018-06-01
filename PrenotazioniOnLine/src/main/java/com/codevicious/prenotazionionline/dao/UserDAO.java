package com.codevicious.prenotazionionline.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface UserDAO {

	@SqlQuery("select count(*) from users where username = :username and password = :password")
	int checkUser(@Bind("username") String username, @Bind("password") String password);

	@SqlQuery("select ID from users where username = :username")
	long getUserID(@Bind("username") String username);

}
