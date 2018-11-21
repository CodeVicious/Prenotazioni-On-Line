package com.codevicious.prenotazionionline.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import com.codevicious.prenotazionionline.dao.mappers.RoleMapper;
import com.codevicious.prenotazionionline.dao.mappers.SectorMapper;
import com.codevicious.prenotazionionline.dao.mappers.UserMapper;
import com.codevicious.prenotazionionline.representations.Role;
import com.codevicious.prenotazionionline.representations.Sector;
import com.codevicious.prenotazionionline.representations.User;

@UseStringTemplate3StatementLocator
public interface UserDAO {

	@SqlQuery("select count(*) from users where username = :username and password = :password")
	int checkUser(@Bind("username") String username, @Bind("password") String password);

	@SqlQuery("select ID from users where username = :username")
	long getUserID(@Bind("username") String username);

	@Mapper(UserMapper.class)
	@SqlQuery("select * from users where ID = :user_id")
	User getUserByID(@Bind("user_id") long user_id);

	@Mapper(UserMapper.class)
	@SqlQuery("SELECT * FROM users ORDER BY <columnName> <direction> LIMIT :recordSize OFFSET :initial")
	List<User> getUsers(@Define("columnName") String columnName, @Define("direction") String direction,
			@Bind("initial") long initial, @Bind("recordSize") long recordSize);

	@Mapper(RoleMapper.class)
	@SqlQuery("select R.ID as ID, R.role as role, R.sigla as sigla, R.description as description from (roles as R inner join users_roles_rel as UR on R.ID = UR.role_id)  where UR.user_id = :user_id")
	List<Role> getRolesByUserID(@Bind("user_id") long user_id);

	@Mapper(SectorMapper.class)
	@SqlQuery("select S.ID as ID, S.code as code, S.sector as sector, S.description as description from (hierarchical_sectors as S inner join users_sectors_rel as US on S.ID = US.hierarchical_sector_id)  where US.user_id = :user_id")
	List<Sector> getSectorsByUserID(@Bind("user_id") long user_id);

	@SqlUpdate("DELETE from users where ID = :user_id")
	long deleteUser(@Bind("user_id") long user_id);

	@SqlUpdate("UPDATE users SET name = :name, surname = :surname, password = :password, telephone = :telephone, mobile = :mobile where ID = :id")
	long updateUser(@BindBean User user);

	@SqlUpdate("INSERT INTO users(name, surname, username, email, password, telephone, mobile) VALUES (:name, :surname, :username, :email, :password, :telephone, mobile) ")
	long insertUser(@BindBean User user);

}
