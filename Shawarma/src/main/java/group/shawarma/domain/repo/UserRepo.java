package group.shawarma.domain.repo;

import group.shawarma.domain.model.User;

public interface UserRepo {
    User saveUser(User user);
    void deleteUser(User user);
    User getUserByEmail(String email);
    User updateUser(User user);
}
