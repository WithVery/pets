package group.cards.service;

import group.cards.domain.model.CardEntity;
import group.cards.domain.model.UserEntity;

public interface CardService {
  CardEntity createNewCard(UserEntity user);
  CardEntity save(CardEntity card);
  Iterable<CardEntity> findAll();
  Iterable<CardEntity> findByUserId(Long userId);
  CardEntity findById(Long Id);
}
