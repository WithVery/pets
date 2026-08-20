package group.cards.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cards")
public class CardEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_id_seg")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "card_id_seg")
    private Long id;
    private String number;

    @Enumerated(EnumType.STRING)
    private CardStatus status;
    private BigDecimal balance;

    //@ManyToOne(cascade = CascadeType.MERGE)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name="valid_by")
    private LocalDate validBy;

    public static CardEntity createNewCard() {
        CardEntity card = new CardEntity();
        card.setStatus(CardStatus.INACTIVE);
        card.setValidBy(LocalDate.now().plusYears(2));
        card.setBalance(BigDecimal.valueOf(0));
        return card;
    }

    public boolean isActive() {
        return status == CardStatus.ACTIVE;
    }

    public void withdraw(BigDecimal amount) {
//        if (!isActive()) {
//            throw new IllegalStateException("Card isn't active!");
//        }

        if(balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("The transfer is too big.");
        }
        balance = balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
//        if (!isActive()) {
//            throw new IllegalStateException("Card isn't active!");
//        }
        balance = balance.add(amount);
    }
}
