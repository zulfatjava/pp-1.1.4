package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT , " +
                "name VARCHAR (40) NOT NULL ," +
                "lastName VARCHAR (40) NOT NULL ," +
                "age TINYINT(3))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);

            System.out.println("Database has been created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }

    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void removeUserById(long id) throws SQLException {
        String sqlCommand = "DELETE FROM users WHERE id VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlCommand = "SELECT * FROM users;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sqlCommand = "DELETE FROM users";

        try (Statement statement = connection.prepareStatement(sqlCommand)) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
}
