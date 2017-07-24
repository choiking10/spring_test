package lifeignite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import user.DaoFactory;
import user.User;
import user.UserDao;

import java.sql.SQLException;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		UserDao dao = new DaoFactory().userDao();

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
