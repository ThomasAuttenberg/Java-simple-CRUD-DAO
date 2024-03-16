package org.example;


public class Main {
    public static void main(String[] args) {
        //Edit database parameters in DataBaseConnectionClass
        UsersDAO usersDAO = new UsersDAO(); // Users fabric. Builds a user
        User user;
        User user2;
        user = usersDAO.getById(1); // Get user with id = 1 from database
        user2 = usersDAO.getByName("User");  // Get user with name User from database
        user.setUsername("Meow"); // Edit user
        usersDAO.update(user); // Update user in database. Now user with id = 1 has name "Meow"
        usersDAO.delete(user2); // Deleting user with name User from database

    }
}