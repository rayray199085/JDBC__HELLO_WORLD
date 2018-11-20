package jdbc_for_connecting_database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    public static void releaseResource(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet!=null) {
                resultSet.close(); // catch separately avoid the rest cannot be executed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement!=null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection!=null) {
                connection.close(); // release resource
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Properties prepareProperties() {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "YOUR PASSWORD");
        properties.setProperty("useSSL", "false");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("serverTimezone","GMT");
        return properties;
    }
}
