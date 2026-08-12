package group.cards.service.impl;

import group.cards.domain.model.CardEntity;
import group.cards.domain.model.TransactionEntity;
import group.cards.repos.TransactionRepo;
import group.cards.service.CardService;
import group.cards.service.TransactionService;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

  private CardService cardService;
  private TransactionRepo transactionRepo;
  private SessionFactory sessionFactory;

  public TransactionServiceImpl(CardService cardService, TransactionRepo transactionRepo, SessionFactory sessionFactory) {
    this.cardService = cardService;
    this.transactionRepo = transactionRepo;
    this.sessionFactory = sessionFactory;
  }

  public List<TransactionEntity> findTransactions(Long userId) {
    String hql = "select t from TransactionEntity t join CardEntity c "
        + "on t.cardTo = c or t.cardFrom = c "
        + "where t.cardFrom.user.id = :userId or t.cardTo.user.id = :userId";
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery(hql, TransactionEntity.class);
    query.setParameter("userId", userId);

    List<TransactionEntity> resultList = query.getResultList();
    return resultList;
  }

  @Override
  public TransactionEntity transferMoney(Long fromId, Long toId, BigDecimal amount) {

    CardEntity cardFrom = loadCardById(fromId);
    CardEntity cardTo =  loadCardById(toId);

//    //Session session =
//        //HibernateUtil..getSessionFactory().openSession();
//    EntityManagerFactory emf =
//      Persistence.createEntityManagerFactory("some-unit");
//    EntityManager em = emf.createEntityManager();
//    EntityTransaction tx = em.getTransaction();
//
////    SessionFactory factory = new Configuration().configure().buildSessionFactory();
////    Session session = factory.openSession();
////    Transaction tx = null;
    TransactionEntity transaction = null;

    try {
//      tx.begin();

      if (cardFrom != null) {
        cardFrom.withdraw(amount);
      }
      if (cardTo != null) {
        cardTo.deposit(amount);
      }

      transaction = transactionRepo.save(
          new TransactionEntity(cardFrom, cardTo, amount)
      );
//      tx.commit();
      return transaction;
    } catch (Exception e) {
//      if(tx != null && tx.isActive()) {
//        tx.rollback();
//      }
      log.error("Failed to transact {}", e);
    } finally {
//      em.close();
//      emf.close();
    }
    return null;
  }

  private CardEntity loadCardById(Long Id) {
    if(Id == null) { return null; }
    CardEntity card = cardService.findById(Id);
    if(card == null) {
      log.error("Card not found {}", Id);
      throw new EntityNotFoundException("Card not found: " + Id);
    }
    return card;
  }
}
