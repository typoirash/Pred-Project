package hiber;

import hiber.config.AppConfig;


import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);
      UserService userService2 = context.getBean(UserService.class);

// Добаление User и Car
      /*userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));*/
      /*
      Car car1 = new Car("BMW",4);
      Car car2 = new Car("Mersedes",9);
      Car car3 = new Car("Lada",6);
      Car car4 = new Car("Maz",23);
      User user1 = new User("User5", "Lastname5", "user5@mail.ru");
      User user2 = new User("User6", "Lastname6", "user6@mail.ru");
      User user3 = new User("User7", "Lastname7", "user7@mail.ru");
      User user4 = new User("User8", "Lastname8", "user8@mail.ru");

      car1.setUser(user1);
      user1.setCar(car1);

      car2.setUser(user2);
      user2.setCar(car2);

      car3.setUser(user3);
      user3.setCar(car3);

      car4.setUser(user4);
      user4.setCar(car4);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(user1);
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(user2);
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(user3);
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(user4);*/

// Получение User по model и series
      User usOwner = userService.ownerCar("Maz",23);
      System.out.println(usOwner);

 // Получение User и его Сar
      /*List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println();
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(user.getCar());
         System.out.println();
      }*/
      context.close();
   }
}
