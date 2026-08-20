package my.service.messaging.service;

import lombok.RequiredArgsConstructor;
import my.service.messaging.event.OrderSendEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessagingService {

  @Value("${topic.orders}")
  private String sendTopic;

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public void sendOrder(OrderSendEvent orderSendEvent) {
    kafkaTemplate.send(sendTopic, orderSendEvent.getBarCode(), orderSendEvent);
  }

}
