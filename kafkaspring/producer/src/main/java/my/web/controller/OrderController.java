package my.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.service.messaging.event.OrderSendEvent;
import my.service.messaging.producer.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
  private final Producer producer;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public OrderSendEvent sendOrder(@RequestBody OrderSendEvent order) {
    log.info("Order accepted {}", order);
    producer.sendOrderEvent(order);
    return order;
  }

}
