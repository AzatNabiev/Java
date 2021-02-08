package ru.itis.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.Message;
import ru.itis.javalab.models.Role;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.*;

@Repository
@Qualifier(value = "userRepository")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private DataSource dataSource;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource){
        this.dataSource = dataSource;
        jdbcTemplate= new JdbcTemplate(dataSource);
        namedJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert=new SimpleJdbcInsert(dataSource);
    }


    void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
    }

    //language=SQL
    public static final String SQL_FIND_ALL_BY_ID="SELECT * FROM users WHERE id=?";
    //language=SQL
    public static final String SQL_FIND_ALL="SELECT * FROM users";
    //language=SQL
    public static final String SQL_SELECT_WITH_PAGES="SELECT * FROM users order by id limit :limit offset :offset;";
    //language=SQL
    public static final String SQL_FIND_ALL_BY_AGE="SELECT * FROM USERS WHERE age=?";
    //language=SQL
    private static final String SQL_INSERT_ENTITY="insert into users(first_name, last_name, age) " +
            "VALUES (?,?,?)";
    //language=SQL
    private static final String SQL_SELECT_BY_LOG_PASS="select * from users where email=? and password=?";
    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL="select * from users where email=?";
    //language=SQL
    private static final String SQL_UPDATE_USER="update users " +
            "set first_name=?, last_name=?, email=?, password=?, age=? where id=?";
    //language=SQL
    private static final String SQL_DELETE_USER="DELETE from users where id=?";
    //language=SQL
    private static final String SQL_SELECT_ALL_BY_SHABL="select  *, position(? in email)\n" +
            "from users where position(? in email)!=0 and id!=?";
    //language=SQL
    private static final String SQL_SELECT_ALL_WITHOUT_ID="select * from users where id!=?";

    private RowMapper<User> rowMapper= (row, i) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .age(row.getLong("age"))
            .role(Role.builder().id(row.getLong("role")).build() )
            .build();

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public List<User> findAll(Long userId){
        return jdbcTemplate.query(SQL_SELECT_ALL_WITHOUT_ID,rowMapper,userId);
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_AGE,rowMapper,age);
    }
    @Override
    public List<User> findAll(int page, int size) {
        Map<String , Object> params=new HashMap<>();
        params.put("limit",size);
        params.put("offset",size*page);
        return namedJdbcTemplate.query(SQL_SELECT_WITH_PAGES,params,rowMapper);
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        User user;
        try {
            user=jdbcTemplate.queryForObject(SQL_FIND_ALL_BY_ID,rowMapper,id);
        }catch (EmptyResultDataAccessException e){
            user=null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findFirstByEmailAndPassword(String email, String pass) {
        User user;
        try {
            user=jdbcTemplate.queryForObject(SQL_SELECT_BY_LOG_PASS,rowMapper,email,pass);
        }catch (EmptyResultDataAccessException e){
            user=null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAllUsersByShablon(String shablon, Long id) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_SHABL,rowMapper,shablon,shablon,id);
    }
    @Override
    public Optional<User> findUserByEmail(String email) {
        User user;
        try {
            user=jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL,rowMapper,email);
        } catch (EmptyResultDataAccessException e){
            user=null;
        }
        return Optional.ofNullable(user);
    }
    @Override
    public void update(User entity) {
        try {
            jdbcTemplate.update(SQL_UPDATE_USER,entity.getFirstName(),entity.getLastName(),
                    entity.getEmail(),entity.getPassword(),entity.getAge(),entity.getId());
        } catch (EmptyResultDataAccessException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(User entity) {
        try {
            jdbcTemplate.update(SQL_DELETE_USER, entity.getId());
        }catch (EmptyResultDataAccessException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        try {

            jdbcTemplate.update(SQL_DELETE_USER, userId);
        }catch (EmptyResultDataAccessException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User entity) {
        Long userId=saveUserInfo(entity);
        entity.setId(userId);
    }
    private Long saveUserInfo(User entity){
        if(!simpleJdbcInsert.isCompiled()){
        simpleJdbcInsert.withTableName("users").usingGeneratedKeyColumns("id")
                .usingColumns("first_name","last_name","age","email","password","role");
        }
        Map<String, Object>map=new HashMap<>();
        map.put("first_name",entity.getFirstName());
        map.put("last_name", entity.getLastName());
        map.put("age", entity.getAge());
        map.put("email", entity.getEmail());
        map.put("password", entity.getPassword());
        map.put("role",2);
        return Long.parseLong(simpleJdbcInsert.executeAndReturnKey(map).toString());
    }
}
