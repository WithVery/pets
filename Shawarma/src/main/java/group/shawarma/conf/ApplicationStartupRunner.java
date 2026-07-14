package group.shawarma.conf;

import group.shawarma.domain.model.User;
import group.shawarma.presentation.service.UserController;
import org.springframework.boot.CommandLineRunner;

public class ApplicationStartupRunner implements CommandLineRunner {

    private final UserController userController;

    public ApplicationStartupRunner(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void run(String... args) throws Exception {
        userController.createUser(new User());
        userController.deleteUser(new User());
    }
}
