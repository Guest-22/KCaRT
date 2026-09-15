package kcart.dao;

import kcart.model.User;

public interface UserDAO {
    User findByUsername(String username);
    boolean addUser(User user);
}