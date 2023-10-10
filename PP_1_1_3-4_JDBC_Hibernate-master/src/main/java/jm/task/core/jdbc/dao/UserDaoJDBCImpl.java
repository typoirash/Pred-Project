package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static String SAVE_USERS = "INSERT INTO users (name, lastname, age) values (?, ?, ?)";
    private static String CLEAN_USERS = "TRUNCATE TABLE users";
    private static String ALL_USERS = "SELECT * FROM users";
    private static String REMOVE_USERS = "DELETE FROM users WHERE id =";
    private static String DROP_USERS_TABLE = "DROP TABLE IF EXISTS users";
    private static String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users (id  BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            " name VARCHAR(40), lastname VARCHAR(40), age INT)";
    //private Connection connection = Util.getConnection();
    private Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_USERS_TABLE);
        } catch (SQLException se) {
            System.out.printf("Ошибка при создании таблицы");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_USERS_TABLE);
        } catch (SQLException se) {
            System.out.printf("Ошибка при удалении таблицы");
        }
        /*try (PreparedStatement preparedStatement = connection.prepareStatement(DROP_USERS_TABLE);) {
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.printf("Ошибка при удалении таблицы");
        }*/
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USERS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.printf("Ошибка при сохранении пользователя");
        }
    }

    public void removeUserById(long id) {
        /*try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USERS)) {
            preparedStatement.
        } catch (SQLException e) {
            System.out.printf("Ошибка при удалении пользователя");
        }*/
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(REMOVE_USERS + id);
        } catch (SQLException se) {
            System.out.printf("Ошибка при удалении пользователя");
        }
    }

    public List<User> getAllUsers() {
        List<User> user = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");
                user.add(new User(name, lastname, age));
            }
        } catch (SQLException e) {
            System.out.printf("Ошибка получения users");
        }
        return user;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            //PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_USERS);
            statement.executeUpdate(CLEAN_USERS);
        } catch (SQLException se) {
            System.out.printf("Ошибка при удалении таблицы");
        }
    }
}
