package com.psybergate.spring_jwt_security.role;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        Optional<Role> role = this.roleRepository.findById(id);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else
            throw new RoleNotFoundException("The role you have requested does not exist");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(this.roleRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody @Valid Role role) {
        return ResponseEntity.ok(this.roleRepository.save(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable Long id) {
        Optional<Role> role = this.roleRepository.findById(id);
        if (role.isPresent()) {
            this.roleRepository.delete(role.get());
            return ResponseEntity.ok(role.get());
        } else
            throw new RoleNotFoundException("The role you trying to delete does not exist.");
    }
    
}
