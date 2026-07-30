package group.cards.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Card {
    private Long id;
    private String number;
    private CardStatus status;
    private BigDecimal balance;
    private UserEntity user;
}
