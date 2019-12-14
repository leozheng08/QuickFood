import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @program: fastfood
 * @package: utils
 * @author: Luping
 * @create: 10/29/19 1:29 PM
 */
public class DataBaseUtil {
    private static String url = "jdbc:mysql://127.0.0.1:3306/csp584final?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    // TODO: 10/29/19  change user & password with your own database info.
    private static String user = "root";
    private static String password = "12344321";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (ClassNotFoundException nfe) {
            nfe.printStackTrace();
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
}
