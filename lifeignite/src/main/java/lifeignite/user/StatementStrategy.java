package lifeignite.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by yunho on 2017. 7. 26..
 */
public interface StatementStrategy {
    PreparedStatement makePreParedStatement(Connection c) throws SQLException;
}
