package group.shawarma.conf;

import group.shawarma.data.repoImpls.collectionFrw.UserRepoImpl;
import group.shawarma.data.repoImpls.collectionFrw.UserRepoImplWithLinkedList;
import group.shawarma.domain.repo.UserRepo;
import group.shawarma.presentation.service.UserController;
import group.shawarma.presentation.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.metrics.ApplicationStartup;

@Configuration
public class ShawarmaConfig {

    @Bean
    public UserRepo userRepo() {
        return new UserRepoImpl();
    }

    @Bean
    @Primary
    public UserRepo userRepowLL() {
        return new UserRepoImplWithLinkedList();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public UserController userController() {
        return new UserController(userService());
    }

    @Bean
    public CommandLineRunner commandlineRunner() {
        return new ApplicationStartupRunner(userController());
    }
}
