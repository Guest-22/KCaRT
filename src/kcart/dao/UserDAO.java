package kcart.dao;

import java.util.List;
import kcart.model.User;

public interface UserDAO {
    
    User findByUsername(String username);
    boolean addUser(User user);
    List<User> getAllUsers();
    boolean editUser(User user);
}