package my.service.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.service.messaging.event.OrderSendEvent;
import my.service.messaging.service.KafkaMessagingService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {
  private final KafkaMessagingService kafkaMessagingService;

  public OrderSendEvent sendOrderEvent(OrderSendEvent order) {
    kafkaMessagingService.sendOrder(order);
    log.info("Sent order from producer {}", order);
    return order;
  }
}
