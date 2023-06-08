package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServ = new UserServiceImpl();
        userServ.createUsersTable();
        userServ.saveUser("Petr", "Petrov", (byte) 41);
        userServ.saveUser("Fedor", "Emelyanenko", (byte) 33);
        userServ.saveUser("Afdosya", "Prokopyeva", (byte) 78);
        userServ.saveUser("Ivan", "Choloba", (byte) 55);
        List<User> users = userServ.getAllUsers();
        for (User i : users) {
            System.out.println(i.toString());
        }
        userServ.cleanUsersTable();
        userServ.dropUsersTable();


    }

}

