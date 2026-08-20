package my.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class KafkaMessagingService {
  private static final String topicCreateOrder = "${topic.orders}";
  private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";

  @Transactional
  @KafkaListener(topics = topicCreateOrder, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=my.service.OrderEvent"})
  public OrderEvent logOrder(OrderEvent orderEvent) {
    log.info("The product consumed: {}", orderEvent);
    return orderEvent;
  }
}
