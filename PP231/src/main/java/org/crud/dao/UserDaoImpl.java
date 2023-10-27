package org.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.crud.model.User;

import javax.persistence.*;
import java.util.List;


@Component
@Transactional
public class UserDaoImpl implements UserDAO {
    @PersistenceContext
    private EntityManager em;
    /*private final EntityManagerFactory entityManagerFactory;

    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
*/
    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }



    @Override
    public void removeUser(long id) {
        em.remove(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }
    @Override
    public List<User> allUser() {
        //List<User> user= em.createQuery("select count(*) from User", User.class).getResultList();
        //em.getTransaction().begin();
        //return null;
        //return em.createQuery("select count(*) from User", User.class).getResultList();
        //System.out.println(user);
        return em.createQuery("select u from User u", User.class).getResultList();
    }
}
