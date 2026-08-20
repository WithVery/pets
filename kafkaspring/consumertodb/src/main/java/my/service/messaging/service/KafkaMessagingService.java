package my.service.messaging.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.service.OrderService;
import my.service.dto.OrderDto;
import my.service.messaging.event.OrderEvent;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaMessagingService {
  private static final String topicCreateOrder = "${topic.orders}";
  private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";
  private final OrderService orderService;
  private final ModelMapper modelMapper;

  @Transactional
  @KafkaListener(topics = topicCreateOrder, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=my.service.messaging.event.OrderEvent"})
  public OrderEvent createOrder(OrderEvent orderEvent) {
    log.info("Message processed: {}", orderEvent);
    orderService.save(modelMapper.map(orderEvent, OrderDto.class));
    return orderEvent;
  }

}
