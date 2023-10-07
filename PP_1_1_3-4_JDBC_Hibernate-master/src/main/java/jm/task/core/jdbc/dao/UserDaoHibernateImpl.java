package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    //private Transaction transaction;
    private static String SAVE_USERS = "INSERT INTO users (name, lastname, age) values (?, ?, ?)";
    //private static String CLEAN_USERS = "delete from %s";
    private static String ALL_USERS = "SELECT * FROM users";
    private static String REMOVE_USERS = "DELETE FROM users WHERE id = ";
    private static String DROP_USERS_TABLE = "DROP TABLE IF EXISTS users";
    private static String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users (id  BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            " name VARCHAR(40), lastname VARCHAR(40), age INT)";

    public UserDaoHibernateImpl() {

    }

    /*private void getTransaction() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    } */

    @Override
    public void createUsersTable() {
        new UserDaoJDBCImpl().createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        new UserDaoJDBCImpl().dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = Util.getSessionFactory().openSession();
        try {

            transaction = session.beginTransaction();
            session.createQuery(REMOVE_USERS + id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> lisr = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            lisr =  session.createQuery("SELECT a from User a", User.class).list();
            transaction.commit();
            return  lisr;
            //transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return lisr;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
