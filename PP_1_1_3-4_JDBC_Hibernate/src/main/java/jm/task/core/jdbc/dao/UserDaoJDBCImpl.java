package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(20), Age INT)";
        try (Connection conn = Util.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("Database has been created!");
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS users";
        try (Connection conn = Util.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("Database has been deleted!");
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = String.format("INSERT INTO users (Name, LastName, Age) VALUES ('%s', '%s', %d)", name, lastName, age);
        try (Connection conn = Util.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("User has been saved!");
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sqlCommand = String.format("DELETE FROM users WHERE Id = %d", id);
        try (Connection conn = Util.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("User has been removed!");
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sqlCommand = "SELECT * FROM users";
        try (Connection conn = Util.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                long id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String LastName = resultSet.getString("LastName");
                int age = resultSet.getInt("Age");
                User user = new User(name, LastName, (byte) age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String sqlCommand = "DELETE FROM users";
        try (Connection conn = Util.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("Users has been cleaned");
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            throw new RuntimeException(e);
        }
    }
}
