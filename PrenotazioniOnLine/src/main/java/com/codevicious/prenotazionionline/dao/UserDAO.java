package com.codevicious.prenotazionionline.dao;

import java.util.List;
import java.util.Optional;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

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
	@SingleValueResult
	Optional<User> getUserByID(@Bind("user_id") long user_id);

	@Mapper(RoleMapper.class)
	@SqlQuery("select R.ID as ID, R.role as role, R.sigla as sigla, R.description as description from (roles as R inner join users_roles_rel as UR on R.ID = UR.role_id)  where UR.user_id = :user_id")
	@SingleValueResult
	List<Role> getRolesByUserID(@Bind("user_id") long user_id);
	
	@Mapper(SectorMapper.class)
	@SqlQuery("select S.ID as ID, S.sector as sector, S.description as description from (sectors as S inner join users_sectors_rel as US on S.ID = US.sector_id)  where US.user_id = :user_id")
	@SingleValueResult
	List<Sector> getSectorsByUserID(@Bind("user_id") long user_id);

}
