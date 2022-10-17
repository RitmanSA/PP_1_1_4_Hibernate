package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT primary key auto_increment, " +
                    "name VARCHAR(15), " +
                    "lastName VARCHAR(15)," +
                    " age TINYINT);");
        } catch (SQLException e) {
            System.out.println("exception while creating");
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            System.out.println("exception while dropping");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("exception while saving");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                     "DELETE FROM users WHERE (id = ?);")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("exception while removing");
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("exception while getting");
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE users;");
        } catch (SQLException e) {
            System.out.println("exception while cleaning");
            e.printStackTrace();
        }
    }
}
