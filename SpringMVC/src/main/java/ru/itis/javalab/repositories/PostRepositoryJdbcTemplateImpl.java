package ru.itis.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.dto.PostDto;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PostRepositoryJdbcTemplateImpl implements PostRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    private DataSource dataSource;
    @Autowired
    public PostRepositoryJdbcTemplateImpl(DataSource dataSource){
        this.dataSource = dataSource;
        jdbcTemplate= new JdbcTemplate(dataSource);
        namedJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert=new SimpleJdbcInsert(dataSource);
    }
    //TODO:дописать SQL запросы, методы и дописать тесты.
    //language=SQL
    public static final String SQL_FIND_ALL_BY_ID="SELECT * FROM user_content WHERE user_id=? order by id DESC";

    //language=SQL
    private static final String SQL_FIND_ALL="select * from user_content";

    //language=SQL
    private static final String SQL_DELETE_USER_CONTENT="DELETE from user_content where id=?";

    //language=SQL
    private static final String SQL_SELECT_WITH_PAGES="SELECT * FROM user_content order by id limit :limit offset :offset";

    //language=SQL
    private static final String SQL_UPDATE_POST="update user_content set content=? where id=?";

    //language=SQL
    private static final String SQL_FIND_POST_BY_POST_ID="select * from user_content where id=?";

    //language=SQL
    //TODO:дописать SQL
    private static final String SQL_FIND_FIRST_POST_BY_USER_ID="select MIN(id), content,user_id from user_content where user_id=?;

    private RowMapper<PostDto> rowMapper=(row,i)-> PostDto.builder()
            .postId(row.getLong("id"))
            .text(row.getString("content"))
            .userId(row.getLong("user_id"))
            .build();


    @Override
    public void save(PostDto entity) {
            Long postId=savePostDto(entity);
            entity.setPostId(postId);
    }
    private Long savePostDto(PostDto entity){
        if(!simpleJdbcInsert.isCompiled()){
            simpleJdbcInsert.withTableName("user_content").usingGeneratedKeyColumns("id")
                    .usingColumns("user_id", "content");;
        }
        Map<String, Object>map=new HashMap<>();
        map.put("user_id",entity.getUserId());
        map.put("content",entity.getText());
        return Long.parseLong(simpleJdbcInsert.executeAndReturnKey(map).toString());
    }

    @Override
    public Optional<PostDto> findPostByPostId(Long id) {
        PostDto postDto;
        try {
            postDto=jdbcTemplate.queryForObject(SQL_FIND_POST_BY_POST_ID, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            postDto = null;
        }
        return Optional.ofNullable(postDto);
    }

    @Override
    public void update(PostDto entity) {
            try {
                jdbcTemplate.update(SQL_UPDATE_POST,entity.getText(), entity.getPostId());
            } catch (EmptyResultDataAccessException e){
                throw new IllegalArgumentException(e);
            }
    }

    @Override
    public Optional<PostDto> findFirstPostByUserId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PostDto> findAll() {
        List<PostDto> postDtos=jdbcTemplate.query(SQL_FIND_ALL,rowMapper);
        return postDtos;
    }

    @Override
    public List<PostDto> getAllById(Long userId) {
        List<PostDto>posts=jdbcTemplate.query(SQL_FIND_ALL_BY_ID,rowMapper,userId);
        return posts;
    }

    @Override
    public void delete(PostDto entity) {
            try {
                jdbcTemplate.update(SQL_DELETE_USER_CONTENT,entity.getPostId());
            } catch (EmptyResultDataAccessException e){
                throw new IllegalArgumentException(e);
            }
    }

    @Override
    public void delete(Long postId) {
        try {
            jdbcTemplate.update(SQL_DELETE_USER_CONTENT,postId);
        }catch (EmptyResultDataAccessException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<PostDto> findAll(int page, int size) {
        Map<String, Object> params=new HashMap<>();
        params.put("limit",size);
        params.put("offset",page*size);
        return namedJdbcTemplate.query(SQL_SELECT_WITH_PAGES,params,rowMapper);
    }
}
