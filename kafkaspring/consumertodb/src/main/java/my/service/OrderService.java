package my.service;

import my.domain.Order;
import my.service.dto.OrderDto;

public interface OrderService {
  Order save(OrderDto orderDto);
}
