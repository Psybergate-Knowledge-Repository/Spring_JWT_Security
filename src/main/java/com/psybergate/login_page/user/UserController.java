package com.psybergate.login_page.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else
            throw new UserNotFoundException("The user you have requested does not exist");
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            this.userRepository.delete(user.get());
            return ResponseEntity.ok(user.get());
        } else
            throw new UserNotFoundException("The user you trying to delete does not exist.");
    }

    @GetMapping("/byUsername")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        User user = this.userRepository.findByUserName(username);
        if (!user.equals(null)) {
            return ResponseEntity.ok(user);
        } else
            throw new UserNotFoundException("The user you trying to get does not exist.");
    }
}
