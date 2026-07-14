package group.shawarma.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Long id;
    private LocalDateTime dateTime;
    private OrderStatus status;
    private User user;
    private List<MenuItem> itemList;
    private Integer totalPrice;
}
