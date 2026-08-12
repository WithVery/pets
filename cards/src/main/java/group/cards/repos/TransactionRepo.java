package group.cards.repos;

import group.cards.domain.model.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<TransactionEntity, Long> {

}
