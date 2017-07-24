package user;

/**
 * Created by yunho on 2017. 7. 24..
 */
public class DaoFactory {
    public UserDao userDao(){
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }
    public ConnectionMaker connectionMaker(){
        return new NConnectionMaker();
    }
}
