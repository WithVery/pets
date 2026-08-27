package my.service.messaging;


import my.service.messaging.event.OrderSendEvent;
import my.service.messaging.service.KafkaProducerMessagingService;
import my.service.messaging.utils.TestOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

@Testcontainers
@SpringBootTest
public class KafkaMessageProducerTest {
  public static final String TOPIC_NAME = "orders";

  @Container
  //static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:8.0.7")); // Используем актуальный образ
  static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("apache/kafka-native:3.8.0")); // Используем актуальный образ

//  @Autowired
  private KafkaProducerMessagingService kafkaProducerMessagingService;

  @Test
  public void OrderSendEventSent() {
    OrderSendEvent order = TestOrder.getOrderSendEvent();
    kafkaProducerMessagingService = kafkaProducerMessagingServiceFactory();

    kafkaProducerMessagingService.sendOrder(order);
//
//    Properties properties = new Properties();
//    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////    properties.put(ConsumerConfig.SECURITY_PROVIDERS_CONFIG
////    KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: INTERNAL:PLAINTEXT,OUTSIDE:PLAINTEXT
//    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
//    properties.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "*");
//    properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-test");
//    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//    properties.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, OrderSendEvent.class);
//
//    KafkaConsumer<String, OrderSendEvent> consumer = new KafkaConsumer<>(properties);
//    consumer.subscribe(Arrays.asList(TOPIC_NAME));
//
//    ConsumerRecords<String, OrderSendEvent> records = consumer.poll(Duration.ofMillis(10000L));
//
//    consumer.close();
//
//    assertEquals(1, records.count());
//
  }

  private KafkaProducerMessagingService kafkaProducerMessagingServiceFactory() {
    return new KafkaProducerMessagingService(KafkaTestContainersConfiguration.kafkaTemplate());
  }

  // 5. Внутренний класс для конфигурации Kafka с использованием динамических свойств контейнера
  @Configuration
  static class KafkaTestContainersConfiguration {

    // Конфигурация ProducerFactory, использующая bootstrap-серверы из контейнера Kafka
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
      Map<String, Object> configProps = new HashMap<>();
      configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
      configProps.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
      configProps.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
      return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate kafkaTemplate() {
      return new KafkaTemplate(producerFactory());
    }

//    @Bean
//    public KafkaProducerMessagingService kafkaProducerMessagingServiceFactory() {
//      return new KafkaProducerMessagingService(kafkaTemplate());
//    }

    // Конфигурация ConsumerFactory, использующая bootstrap-серверы из контейнера Kafka
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
      Map<String, Object> props = new HashMap<>();
      props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
      props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "test_group");
      props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
      props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
      props.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Важно для тестов: начинать чтение с самого начала
      return new DefaultKafkaConsumerFactory<>(props);
    }
  }
}
