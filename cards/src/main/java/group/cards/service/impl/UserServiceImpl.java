package group.cards.service.impl;

import group.cards.domain.model.UserEntity;
import group.cards.repos.UserRepo;
import group.cards.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return userRepo.findAll();
    }
}
