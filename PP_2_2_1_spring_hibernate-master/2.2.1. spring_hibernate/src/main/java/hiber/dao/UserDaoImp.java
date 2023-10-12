package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }


   @Override

   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(
              "from User"); //"from User u inner join u.car"
      return query.getResultList();
   }
   //select new list(id, name, last_name, email)
   //from DomesticCat as mother
   //    inner join mother.mate as mate
   //    left outer join mother.kittens as offspr

   @Override
   public User ownerCar(String model, int series) {

         TypedQuery<User> typedQuery= sessionFactory.getCurrentSession().createQuery(
                 //"select u from User u join fetch u.car where u.id = :id");
                 //"from User as u  join fetch u.car as um where um.model like '%"+model+"%' and um.series = "+series+"");

                 //"FROM User u LEFT OUTER JOIN FETCH u.car WHERE u.id = :id", User.class).setParameter("id",2L).uniqueResult();
                  "FROM User u LEFT OUTER JOIN FETCH u.car uc WHERE uc.model = :model and uc.series = :series")
                 .setParameter("series", series).setParameter("model", model);
      //System.out.println(user);
      //System.out.println(user.getCar());
         return typedQuery.getSingleResult();

      //query.setParameter("name", "Howard Phillips Lovecraft");
      //Author author = query.getSingleResult();
      //TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
      //"from User where User.car.model = 'users'");
       //"select User from User as user left join user.car.model as model");

   }
}
