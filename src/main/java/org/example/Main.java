package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UsersDAO usersDAO = new UsersDAO();
        User user = null;
        User user2;
        user = usersDAO.getById(1);
        user2 = usersDAO.getByName("User");
        user2 = usersDAO.getByName("Use");
        System.out.println(user2 == null);
        usersDAO.update(user2);
        user.setPassword("asdasdsffd132123");
        usersDAO.update(user);
        User newUser = new User();
        newUser.setUsername("Hello");
        newUser.setPassword("Meow");
        usersDAO.create(newUser);
        System.out.println(user.getUserName() + " " + user.getPassword());
    }
}