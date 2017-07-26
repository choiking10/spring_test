package lifeignite.user;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
		users[0] = new User("aaa","moo","myname");
		users[1] = new User("ccc","friend","is");
		users[2] = new User("bbb","pink","lifeignite");

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

	@Test
	public void getAll() throws SQLException, ClassNotFoundException{
		dao.deleteAll();

		// 아무것도 없을 때 체크
		List<User> expectUsers = new ArrayList<>();
		checkSameUserList(expectUsers, dao.getAll());

		for(int i = 0; i < this.users.length; i++){
			dao.add(users[i]);
			expectUsers.add(users[i]);

			Collections.sort(expectUsers, new Comparator<User>() {
				@Override
				public int compare(User o1, User o2) {
					return o1.id.compareTo(o2.id);
				}
			});

			checkSameUserList(expectUsers,dao.getAll());
		}
	}
	private void checkSameUserList(List<User> ul1, List<User> ul2){
		assertThat(ul1.size(), is(ul2.size()));

		for(int i =0; i < ul1.size(); i++){
			checkSameUser(ul1.get(i),ul2.get(i));
		}
	}
	private void checkSameUser(User user1, User user2){
		assertThat(user1.getId(),is(user2.getId()));
		assertThat(user1.getName(),is(user2.getName()));
		assertThat(user1.getPassword(),is(user2.getPassword()));
	}
}
