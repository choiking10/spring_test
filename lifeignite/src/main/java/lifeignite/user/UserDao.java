package lifeignite.user;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user= new User();
            user.setId(resultSet.getString("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
    };

    public UserDao(){
    }
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(final User user) throws SQLException{
        jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
                user.getId(), user.getName(), user.getPassword());
    }
    public User get(String id) throws SQLException{
        return this.jdbcTemplate.queryForObject(
                "select * from users where id = ?", new Object[]{id}, userRowMapper);
    }
    public List<User> getAll() throws SQLException {
        return jdbcTemplate.query("select * from users order by id",
                userRowMapper);
    }

    public void deleteAll() throws SQLException{
        jdbcTemplate.update("delete from users");

    }

    public int getCount() throws SQLException{
        return this.jdbcTemplate.queryForObject("select count(*) from users",Integer.class);
    }

}
