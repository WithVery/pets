package group.cards.service;

import group.cards.domain.model.UserEntity;

public interface UserService {
    UserEntity save(UserEntity user);
    void delete(Long id);
    boolean isExists(Long id);
    UserEntity findById(Long id);
    UserEntity partialUpdate(Long id, UserEntity user);
    Iterable<UserEntity> findAll();
    UserEntity findByUsername(String username);
}
