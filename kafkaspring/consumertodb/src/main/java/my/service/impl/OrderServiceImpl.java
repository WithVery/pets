package my.service.impl;

import lombok.extern.slf4j.Slf4j;
import my.domain.Order;
import my.domain.repository.OrdersRepository;
import my.service.OrderService;
import my.service.dto.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = false)
public class OrderServiceImpl implements OrderService {

  private final OrdersRepository ordersRepository;

  public OrderServiceImpl(OrdersRepository ordersRepository) {
    this.ordersRepository = ordersRepository;
  }

  @Override
  @Transactional
  public Order save(OrderDto orderDto) {
    Order order = Order.builder()
                  .productName(orderDto.getProductName())
                  .barCode(orderDto.getBarCode())
                  .orderDate(LocalDateTime.now())
                  .build();
    ordersRepository.save(order);

    log.info("Order saved.");
    return order;
  }
}
