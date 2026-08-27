package my.service.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.service.messaging.event.OrderSendEvent;
import my.service.messaging.service.KafkaProducerMessagingService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {
  private final KafkaProducerMessagingService kafkaProducerMessagingService;

  public OrderSendEvent sendOrderEvent(OrderSendEvent order) {
    kafkaProducerMessagingService.sendOrder(order);
    log.info("Sent order from producer {}", order);
    return order;
  }
}
