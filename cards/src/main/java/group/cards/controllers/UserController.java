package group.cards.controllers;

import group.cards.domain.model.UserEntity;
import group.cards.domain.model.UserRole;
import group.cards.repos.UserRepo;
import group.cards.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public UserController(UserService userService, PasswordEncoder passwordEncoder,
                          UserRepo userRepo) {
      this.userService = userService;
      this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

//    @PostMapping(path="/users")
//    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
//        user.setId(null);
//        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
//    }

    @GetMapping(path = "/users")
    public Iterable<UserEntity> listUSers() {
        return userService.findAll();
    }

    @PostMapping(path = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity createUser(@RequestBody UserEntity user) {
        user.setId(null);
        if(user.getRole() == null) {user.setRole(UserRole.USER);}
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userService.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> fullUpdateUser(
        @PathVariable("id") Long id,
        @RequestBody UserEntity user
        ) {
        if(!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>( userService.save(user), HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserEntity> partialUpdate(
        @PathVariable("id") Long id,
        @RequestBody UserEntity user) {
        if(!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
            userService.partialUpdate(id, user),
            HttpStatus.OK
        );
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
