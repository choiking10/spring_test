package lifeignite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.DaoFactory;
import user.User;
import user.UserDao;

import java.sql.SQLException;

@SpringBootApplication
public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("lifeignite");
		user.setName("광전사");
		user.setPassword("for");

		dao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId()+" 조회 성공");

	}
}
