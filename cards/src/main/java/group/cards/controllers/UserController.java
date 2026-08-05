package group.cards.controllers;

import group.cards.domain.model.UserEntity;
import group.cards.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping(path = "/register")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String registerUser() {
//        return "Ok";
//    }

//    @PostMapping(path="/users")
//    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
//        user.setId(null);
//        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
//    }

//    @PostMapping(path="/users")
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserEntity createUser(@RequestBody UserEntity user) {
//        user.setId(null);
//        return userService.save(user);
//    }

    @GetMapping(path = "/users")
    public Iterable<UserEntity> listUSers() {
        return userService.findAll();
    }
}
