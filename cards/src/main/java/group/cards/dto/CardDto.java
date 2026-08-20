package group.cards.dto;

import group.cards.domain.model.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
  private Long id;
  private String number;
  private BigDecimal balance;
  private CardStatus status;
  private Long userId;
  private LocalDate validBy;
}
