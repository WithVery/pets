package group.shawarma.presentation.service;

import group.shawarma.domain.model.User;

public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public User createUser(User user) {
        return service.createUser(user);
    }

    public void deleteUser(User user) {
        service.deleteUser(user);
    }
}
