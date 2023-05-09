package hu.akosbalogh.tvshowlist.service.impl;

import hu.akosbalogh.tvshowlist.data.model.User;
import hu.akosbalogh.tvshowlist.data.repository.UserRepositoryInterface;
import hu.akosbalogh.tvshowlist.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A Service for manipulating the stored User data.
 */
@Service
public class UserService implements UserServiceInterface {

    private final UserRepositoryInterface<User, Long> userRepository;

    @Autowired
    public UserService(UserRepositoryInterface<User, Long> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> retrieveUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Optional<User> retrieveUserByUserName(String userName) {
        return userRepository.getByUserName(userName);
    }

    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public User updateUser(User show) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
