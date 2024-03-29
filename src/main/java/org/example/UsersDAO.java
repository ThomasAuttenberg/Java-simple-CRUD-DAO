package org.example;

import java.sql.SQLException;
import java.util.LinkedList;

public class UsersDAO extends DAO<User>{

    UsersDAO() {
        super(User.class);
    }

    @Override
    public void delete(User entity) {
        try {
            super.delete(entity);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public User getById(long id) {
        try {
            LinkedList<User> users = super.getByField(User.class.getDeclaredField("user_id"), id);
            return users.isEmpty() ? null : users.getFirst();
        } catch (IllegalAccessException | SQLException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    public User getByName(String name) {
        try {
            LinkedList<User> users = super.getByField(User.class.getDeclaredField("username"),name);
            return users.isEmpty() ? null : users.getFirst();
        } catch (IllegalAccessException | SQLException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(User entity) {
        try {
            super.create(entity);
        } catch (IllegalAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User entity){
        try {
            super.update(entity);
        } catch (IllegalAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
