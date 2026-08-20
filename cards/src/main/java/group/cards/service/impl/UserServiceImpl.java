package group.cards.service.impl;

import group.cards.domain.model.UserEntity;
import group.cards.repos.UserRepo;
import group.cards.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
      this.userRepo = userRepo;
      this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return userRepo.findAll();
    }

  @Override
  public UserEntity findByUsername(String username) {
    return userRepo.findByUsername(username);
  }

  @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {return userRepo.existsById(id);}

  @Override
  public UserEntity findById(Long id) {
    return userRepo.findById(id).orElse(null);
  }

  @Override
    public UserEntity partialUpdate(Long id, UserEntity user) {
        user.setId(id);

        return userRepo.findById(id).map(
            existingUser -> {
                Optional.ofNullable(user.getUsername()).ifPresent(existingUser::setUsername);
                Optional.ofNullable(user.getEmail()).ifPresent(existingUser::setEmail);
                Optional.ofNullable(user.getPassword()).ifPresent(password -> existingUser.setPassword(passwordEncoder.encode(password)));
                return userRepo.save(existingUser);
            }).orElseThrow(() -> new RuntimeException("USer does not exist"));

    }

}
