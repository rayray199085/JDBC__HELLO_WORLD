package jdbc_for_connecting_database;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

public class JDBCHello {
    public static void main(String[] args) {
        JDBCHello jdbcHello = new JDBCHello();
        jdbcHello.jdbcHello();
    }


    public void jdbcHello() {
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            // register a driver for mysql database
            DriverManager.registerDriver(new Driver());
            // database url, username, password
            //it represents mysql server connects to java
            Properties properties = JDBCUtils.prepareProperties();

            String url = "jdbc:mysql://localhost:3306/DATABASE_NAME";
            connection = DriverManager.getConnection(url, properties);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from info;");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                System.out.println("id: " + id + " name: " + name + " age: " + age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.releaseResource(resultSet,statement,connection);


        }
    }
}
