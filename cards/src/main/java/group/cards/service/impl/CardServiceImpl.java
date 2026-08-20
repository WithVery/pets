package group.cards.service.impl;

import group.cards.domain.model.CardEntity;
import group.cards.domain.model.UserEntity;
import group.cards.repos.CardRepo;
import group.cards.repos.UserRepo;
import group.cards.service.CardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements CardService {

  public CardServiceImpl(CardRepo cardRepo, UserRepo userRepo) {
    this.cardRepo = cardRepo;
    this.userRepo = userRepo;
  }

  private CardRepo cardRepo;
  private final UserRepo userRepo;

  @Override
  //@Transactional("transactionManager")
  public CardEntity save(CardEntity card) {
    return cardRepo.save(card);
  }

  @Override
  public Iterable<CardEntity> findAll() {
    return cardRepo.findAll();
  }

  @Override
  public Iterable<CardEntity> findByUserId(Long userId) {
    return cardRepo.findByUserId(userId);
  }

  @Override
  public CardEntity findById(Long Id) {
    return cardRepo.findById(Id).orElse(null);
  }

  @Override
  //@Transactional("hibernateTransactionManager")
//  @Transactional("transactionManager")
  public CardEntity createNewCard(UserEntity user) {
    CardEntity card = CardEntity.createNewCard();
    card.setUser(user);
    return cardRepo.save(card);
  }
}
