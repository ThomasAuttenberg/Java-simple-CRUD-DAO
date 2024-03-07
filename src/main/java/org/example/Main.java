package org.example;


public class Main {
    public static void main(String[] args) {
        UsersDAO usersDAO = new UsersDAO();
        User user = null;
        User user2;
        user = usersDAO.getById(1);
        user2 = usersDAO.getByName("User");
        System.out.println(user2.getUserName());
        usersDAO.delete(user);
    }
}