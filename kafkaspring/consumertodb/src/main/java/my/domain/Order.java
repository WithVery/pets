package my.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long Id;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "bar_code", nullable = false)
  private String barCode;

  @Column(name="order_date", nullable = false, updatable = false)
  private LocalDateTime orderDate;

}
