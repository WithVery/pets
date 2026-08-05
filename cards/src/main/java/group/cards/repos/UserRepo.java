package group.cards.repos;

import group.cards.domain.model.UserEntity;
import group.cards.domain.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    @Query("SELECT a FROM UserEntity a WHERE a.role=?1")
    Iterable<UserEntity> findUsersWithRole(UserRole role);

}
