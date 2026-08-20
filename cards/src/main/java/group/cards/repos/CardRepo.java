package group.cards.repos;

import group.cards.domain.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CardRepo extends CrudRepository<CardEntity, Long> {
//public interface CardRepo extends JpaRepository<CardEntity, Long> {
  Iterable<CardEntity> findByUserId(Long userId);
//  CardEntity createNewCard(UserEntity user);
}
