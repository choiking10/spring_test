package lifeignite;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import user.User;
import user.UserDao;

import java.sql.SQLException;

public class UserDaoTest {
	public static void main(String[] args) {
		JUnitCore.main("lifeignite.UserDaoTest");
	}

	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException{
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("lifeignite");
		user.setName("광전사");
		user.setPassword("for");

		dao.add(user);

		User user2 = dao.get(user.getId());

		assertThat(user2.getName(),is(user.getName()));
		assertThat(user2.getPassword(),is(user.getPassword()));
	}
}
