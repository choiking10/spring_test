package user;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yunho on 2017. 7. 24..
 */
public interface ConnectionMaker {
    Connection makeConnection() throws ClassNotFoundException, SQLException;
}
