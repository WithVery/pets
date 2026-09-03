package my.service.messaging;

import my.ProducerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.Network;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.ConfluentKafkaContainer;

import java.util.List;

@Testcontainers
@SpringBootTest
public class KafkaMessageProducerTest {

  public static void main(String[] args) {
    SpringApplication.from(ProducerApplication::main)
        .with(ContainerConfiguration.class)
        .run(args);
  }

  @TestConfiguration(proxyBeanMethods = false)
  static class ContainerConfiguration {
    private static final String KAFKA_NETWORK = "kafka-network";

    Network network = getNetwork();

    static Network getNetwork() {
      Network defaultDaprNetwork = new Network() {
        @Override
        public String getId() {
          return KAFKA_NETWORK;
        }

        @Override
        public void close() {

        }

      };

      List<com.github.dockerjava.api.model.Network> networks = DockerClientFactory.instance().client().listNetworksCmd().withNameFilter(KAFKA_NETWORK).exec();
      if (networks.isEmpty()) {
        Network.builder()
            .createNetworkCmdModifier(cmd -> cmd.withName(KAFKA_NETWORK))
            .build().getId();
        return defaultDaprNetwork;
      } else {
        return defaultDaprNetwork;
      }
    }

    @Bean
    @ServiceConnection
    ConfluentKafkaContainer kafkaContainer() {
      return new ConfluentKafkaContainer("confluentinc/cp-kafka:7.4.0")
          .withListener("kafka:19092")
          .withNetwork(network)
          .withReuse(true);
    }
  }
}
