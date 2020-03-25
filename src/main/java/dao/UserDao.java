package dao;

import entities.User;
import java.sql.*;

public class UserDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/server";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "lehf12";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    private static void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByName(String login) {
        User user = null;
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from usr where login=?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                System.out.println(user.getLogin());
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return user;
    }
}
