package jdbc_for_connecting_database;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

public class JDBCHello {
    private ResultSet resultSet = null;
    private Statement statement = null;
    private Connection connection = null;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/xiaohema_database"; // database name
    private static final String DRIVER_MANAGER = "com.mysql.cj.jdbc.Driver";

    public void jdbcQuery() {

        try {
            // register a driver for mysql database
            //avoid calling DriverManager twice
//            DriverManager.registerDriver(new Driver());

            Class.forName(DRIVER_MANAGER);
            // database url, username, password
            //it represents mysql server connects to java
            Properties properties = JDBCUtils.prepareProperties();

            connection = DriverManager.getConnection(DATABASE_URL, properties);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from info;");
            while (resultSet.next()) {
                int id = resultSet.getInt(1); // column starts from 1
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                System.out.println("id: " + id + " name: " + name + " age: " + age);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResource(resultSet, statement, connection);


        }
    }

    public void jdbcInsert() {
        try {
            Class.forName(DRIVER_MANAGER);
//            DriverManager.registerDriver(new Driver());
            Properties properties = JDBCUtils.prepareProperties();
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            statement = connection.createStatement();
            statement.executeUpdate("insert into info (name,age) values('mandy',28);");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResource(resultSet, statement, connection);
        }

    }

    public void jdbcDelete() {
        try {
            Class.forName(DRIVER_MANAGER);
//            DriverManager.registerDriver(new Driver());
            Properties properties = JDBCUtils.prepareProperties();
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            statement = connection.createStatement();
            statement.executeUpdate("delete from info where name='mandy';");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResource(resultSet, statement, connection);
        }

    }

    public void jdbcUpdate() {
        try {
            Class.forName(DRIVER_MANAGER);
//            DriverManager.registerDriver(new Driver());
            Properties properties = JDBCUtils.prepareProperties();
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            statement = connection.createStatement();
            statement.executeUpdate("update info set name='tiny_hippo' where _id=1;");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseResource(resultSet, statement, connection);
        }
    }
}
