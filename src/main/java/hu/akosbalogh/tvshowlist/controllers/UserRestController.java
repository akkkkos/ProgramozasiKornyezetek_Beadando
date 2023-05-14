package hu.akosbalogh.tvshowlist.controllers;

import hu.akosbalogh.tvshowlist.data.model.User;
import hu.akosbalogh.tvshowlist.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



import java.util.List;
import java.util.Optional;

/**
 * Web Controller for taking care of User REST actions.
 */

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get user by Id.
     *
     * @param id Id.
     * @return Returns the user with the given Id if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.retrieveUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get user by username.
     *
     * @param user User name in post.
     * @return Returns the user with the given username if found.
     */
    @PostMapping("/byusername/")
    public ResponseEntity<User> getUserByUserName(@RequestBody User user) {
        String userName = user.getUserName();
        Optional<User> foundUser = userService.retrieveUserByUserName(userName);
        return foundUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.retrieveAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
