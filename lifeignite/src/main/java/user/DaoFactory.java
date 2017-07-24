package user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yunho on 2017. 7. 24..
 */

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao(){
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }
    @Bean
    public ConnectionMaker connectionMaker(){
        return new NConnectionMaker();
    }
}
