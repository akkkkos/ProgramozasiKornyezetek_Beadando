package hu.akosbalogh.tvshowlist.data.repository.impl;

import hu.akosbalogh.tvshowlist.data.model.User;
import hu.akosbalogh.tvshowlist.data.repository.UserRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An in memory repository for storing User data.
 */
@Repository
public class UserRepository implements UserRepositoryInterface<User, Long> {

    private static final Map<Long, User> STORAGE = new HashMap<>();

    private int idCounter = 0;

    @Override
    public User save(User user) {
        Long id = idCounter +  1L;
        idCounter++;
        user.setId(id);
        STORAGE.put(id, user);
        return STORAGE.get(id);
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(STORAGE.get(id));
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        return STORAGE.values()
                .stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return STORAGE.values().stream().toList();
    }

    @Override
    public User update(User user) {
        Long id = user.getId();
        STORAGE.put(id, user);
        return STORAGE.get(id);
    }

    @Override
    public void deleteById(Long id) {
        STORAGE.remove(id);
    }
}
