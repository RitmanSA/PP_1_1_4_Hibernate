package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Stepan", "Ritman", (byte) 21);
        userService.saveUser("Ivan", "Petrov", (byte) 25);
        userService.saveUser("Petr", "Ivanov", (byte) 23);
        userService.saveUser("Evgeny", "Evgenev", (byte) 17);
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
