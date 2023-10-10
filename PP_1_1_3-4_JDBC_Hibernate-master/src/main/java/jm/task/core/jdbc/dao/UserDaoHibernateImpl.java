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
    private static String REMOVE_USERS = "DELETE FROM User WHERE id = :ID" ;
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
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREATE_USERS_TABLE).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(DROP_USERS_TABLE).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery(REMOVE_USERS).setParameter("ID",id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> lisr = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            lisr =  session.createQuery("SELECT a from User a", User.class).list();
            session.getTransaction().commit();
            return  lisr;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
