package group.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDto {
  private Long id;
  private Long cardFromId;
  private Long cardToId;
  private BigDecimal amount;
}


