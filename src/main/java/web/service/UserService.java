package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User showById(int id);

    void updateUser(int id, User user);

    void removeUser(int id);

    List<User> getAllUsers();

}
