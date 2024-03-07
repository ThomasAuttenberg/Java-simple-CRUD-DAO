package org.example;

import java.sql.SQLException;

public class UsersDAO extends DAO<User>{

    UsersDAO() {
        super(User.class);
    }

    public User getById(long id) {
        try {
            return super.getByField(User.class.getDeclaredField("user_id"), id);
        } catch (IllegalAccessException | SQLException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    public User getByName(String name) {
        try {
            return super.getByField(User.class.getDeclaredField("username"),name);
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
