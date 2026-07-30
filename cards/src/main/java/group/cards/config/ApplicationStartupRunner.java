package group.cards.config;

import group.cards.domain.model.UserEntity;
import group.cards.domain.model.UserStatus;
import group.cards.repos.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

    private final UserRepo userRepo;

    public ApplicationStartupRunner(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public void run(String... args) throws Exception {
//        UserEntity user = new UserEntity();
//        cardsRepo.createCard(user);
//        Card card = cardsRepo.createCard(user);
//        cardsRepo.createCard(user);
//        cardsRepo.createCard(user);
//        cardsRepo.deleteCard(card);
//        UserEntity admin = new UserEntity().builder()
//                            .id(1L).name("Admin").userStatus(UserStatus.ADMIN).build();
//
//        if(!userRepo.existsById(admin.getId()))
//            userRepo.save(admin);
    }
}
