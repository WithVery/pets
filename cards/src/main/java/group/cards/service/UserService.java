package group.cards.service;

import group.cards.domain.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserEntity save(UserEntity user);
    Iterable<UserEntity> findAll();
}
