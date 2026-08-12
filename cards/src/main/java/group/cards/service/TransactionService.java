package group.cards.service;

import group.cards.domain.model.TransactionEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
  TransactionEntity transferMoney(Long fromId, Long toId, BigDecimal amount);
  List<TransactionEntity> findTransactions(Long userId);
}
