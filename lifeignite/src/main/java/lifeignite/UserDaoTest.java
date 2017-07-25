package lifeignite;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import user.User;
import user.UserDao;

import java.sql.SQLException;

@SpringBootApplication
public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("lifeignite");
		user.setName("광전사");
		user.setPassword("for");

		dao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = dao.get(user.getId());

		if(!user.getName().equals(user2.getName())){
			System.out.println("테스트 실패 (name)");
		}
		if(!user.getPassword().equals(user2.getPassword())){
			System.out.println("테스트 실패 (password)");
		}else {
			System.out.println("조회 테스트 성공");
		}

	}
}
