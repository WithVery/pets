package my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("my.domain.**")
public class ConsumerToDBApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConsumerToDBApplication.class, args);
  }
}
