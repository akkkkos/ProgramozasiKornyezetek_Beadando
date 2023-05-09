package hu.akosbalogh.tvshowlist.service;

import hu.akosbalogh.tvshowlist.data.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface for UserService.
 */
public interface UserServiceInterface {
    User createUser(User user);

    Optional<User> retrieveUserById(Long id);

    Optional<User> retrieveUserByUserName(String userName);

    List<User> retrieveAllUsers();

    User updateUser(User show);

    void deleteUserById(Long id);
}
