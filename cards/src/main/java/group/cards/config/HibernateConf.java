package group.cards.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.hibernate.HibernateTransactionManager;
import org.springframework.orm.jpa.hibernate.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConf {

  @Bean(name="entityManagerFactory")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("group.cards");
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
  }

  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUrl("jdbc:postgresql://localhost:5432/cards");
    dataSource.setUsername("postgres");
    dataSource.setPassword("111");

    return dataSource;
  }

  @Bean
  public PlatformTransactionManager hibernateTransactionManager() {
    HibernateTransactionManager transactionManager
        = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }

  private final Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty(
        "hibernate.ddl-auto", "update");
    hibernateProperties.setProperty(
        "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

    return hibernateProperties;
  }
}
