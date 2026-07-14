package group.shawarma.domain.interactor;

import group.shawarma.domain.model.User;
import group.shawarma.domain.repo.UserRepo;
import lombok.Setter;

@Setter
public class UserInteractor {
    private UserRepo userRepo;
    public UserInteractor(UserRepo userRepo) { this.userRepo = userRepo; }
    public User createUser(User user){ return userRepo.saveUser(user); }
    public User saveUser(User user) {
        return userRepo.saveUser(user);
    }
    public void deleteUser(User user) {
        userRepo.deleteUser(user);
    }

    public User getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    public User updateUser(User user) {
        return userRepo.updateUser(user);
    }
}
