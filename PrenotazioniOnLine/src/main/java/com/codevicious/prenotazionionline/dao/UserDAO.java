package com.codevicious.prenotazionionline.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import com.codevicious.prenotazionionline.dao.mappers.RoleMapper;
import com.codevicious.prenotazionionline.dao.mappers.SectorMapper;
import com.codevicious.prenotazionionline.dao.mappers.UserMapper;
import com.codevicious.prenotazionionline.representations.Role;
import com.codevicious.prenotazionionline.representations.Sector;
import com.codevicious.prenotazionionline.representations.User;


public interface UserDAO {

	@SqlQuery("select count(*) from users where username = :username and password = :password")
	int checkUser(@Bind("username") String username, @Bind("password") String password);

	@SqlQuery("select ID from users where username = :username")
	long getUserID(@Bind("username") String username);

	@Mapper(UserMapper.class)
	@SqlQuery("select * from users where ID = :user_id")	
	User getUserByID(@Bind("user_id") long user_id);

	@Mapper(UserMapper.class)
	@SqlQuery("SELECT * FROM users ORDER BY :columnName :direction LIMIT :initial , :recordSize")
	List<User> getUsers(@Bind("columnName") String columnName, @Bind("direction") String direction,
			@Bind("initial") long initial, @Bind("recordSize") long recordSize);

	@Mapper(RoleMapper.class)
	@SqlQuery("select R.ID as ID, R.role as role, R.sigla as sigla, R.description as description from (roles as R inner join users_roles_rel as UR on R.ID = UR.role_id)  where UR.user_id = :user_id")
	List<Role> getRolesByUserID(@Bind("user_id") long user_id);

	@Mapper(SectorMapper.class)
	@SqlQuery("select S.ID as ID, S.code as code, S.sector as sector, S.description as description from (hierarchical_sectors as S inner join users_sectors_rel as US on S.ID = US.hierarchical_sector_id)  where US.user_id = :user_id")
	List<Sector> getSectorsByUserID(@Bind("user_id") long user_id);

}
