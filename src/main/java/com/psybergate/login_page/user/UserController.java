package com.psybergate.login_page.user;

import com.psybergate.login_page.role.Role;
import com.psybergate.login_page.role.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	public UserController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
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
		List<Role> roles = this.roleRepository
				.findAllById(user.getRoles().stream().map(usr -> usr.getId()).collect(Collectors.toList()));

		user.setRoles(new HashSet<>(roles != null ? roles : this.roleRepository.saveAll(user.getRoles())));

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
