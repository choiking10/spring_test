package lifeignite.user;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserDaoTest {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private UserDao dao;
	private User[] users;

	@Before
	public void setUp(){
		users = new User[3];
		users[0] = new User("mama","moo","myname");
		users[1] = new User("girl","friend","is");
		users[2] = new User("A","pink","lifeignite");

	}
	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException{
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		for(User u : users){
			dao.add(u);
		}

		assertThat(dao.getCount(), is(users.length));

		for(int i = 0; i < users.length; i++){
			User userget = dao.get(users[i].getId());

			assertThat(userget.getName(),is(users[i].getName()));
			assertThat(userget.getPassword(),is(users[i].getPassword()));

		}
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException{

		dao.deleteAll();
		assertThat(dao.getCount(),is(0));

		dao.get("unknown_id");
	}
}
