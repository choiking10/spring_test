package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yunho on 2017. 7. 24..
 */
public class NConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost/spring_test", "lifeignite", "My_life_for_aiur!123"
            );
            return c;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
