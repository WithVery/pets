package group.cards.domain.model;

import jakarta.persistence.*;
import org.hibernate.query.Query;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class TransactionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_sequence")
  private Long Id;

  @ManyToOne(optional = true)
  @JoinColumn(name = "from_card_id", referencedColumnName = "id")
  private CardEntity cardFrom;

  @ManyToOne(optional = true)
  @JoinColumn(name = "to_card_id", referencedColumnName = "id")
  private CardEntity cardTo;

  private BigDecimal amount;

  @Column(name = "local_date")
  @CreationTimestamp
  private LocalDateTime localDate;

  public TransactionEntity(CardEntity cardFrom, CardEntity cardTo, BigDecimal amount) {
    this.cardFrom = cardFrom;
    this.cardTo = cardTo;
    this.amount = amount;
  }

  public boolean isValid() {
    return cardFrom == null || (cardFrom.isActive() && (amount.compareTo(cardFrom.getBalance()) <= 0));
  }

  //@Override
  public List<TransactionEntity> findTransactionsByUser(Long userId) {
//    String hql = " select t from Transactions t inner join t.cardFrom.user.id = ?user";
    //String hql = " select t from Transactions t inner join t.cardFrom.user.id = ?user";
    String hql = " select t from TransactionEntity t";
    SessionFactory sessionFactory = new Configuration().buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery(hql, TransactionEntity.class);

    List<TransactionEntity> resultList = query.getResultList();
    return resultList;
  }
}
