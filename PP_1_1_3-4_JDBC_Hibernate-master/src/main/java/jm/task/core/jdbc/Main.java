package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        User[] user = new User[] {new User("Alex", "Alexandrov", (byte) 11),
                new User("Alexei", "Alexeev", (byte) 22),
                new User("Nikola", "Tesla", (byte) 33),
                new User("Pavel", "Lisicin", (byte) 44)
        };
        for (User user1 : user) {
            try {
                userService.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
                System.out.println("User с именем – " + user1.getName() + " добавлен в базу данных");
            } catch (Exception e) {

            }
        }
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
