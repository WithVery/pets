package group.shawarma.data.repoImpls.collectionFrw;

import group.shawarma.domain.model.User;
import group.shawarma.domain.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepo {

    private List<User> users = new ArrayList<>();

    @Override
    public User saveUser(User user) {
        users.add(user);
        System.out.println("User created AL");
        return user;
    }

    @Override
    public void deleteUser(User user) {
        System.out.println("User deleted AL");
        users.remove(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public User updateUser(User user) {
        int index = users.indexOf(user);
        if(index != -1) {
            users.set(index, user);
        }
        return user;
    }
}
