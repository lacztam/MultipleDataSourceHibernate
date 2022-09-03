package hu.multipledatasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "hu.multipledatasource.repository.Lob",
        entityManagerFactoryRef = "lobEntityManagerFactory",
        transactionManagerRef= "lobTransactionManagerFactory")
public class LobDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.lob")
    public DataSourceProperties lobDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "lobDataSource")
    @ConfigurationProperties("spring.datasource.lob.configuration")
    public DataSource lobDataSource() {
        return lobDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "lobEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean lobEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(lobDataSource())
                .packages("hu.multipledatasource.model.LobModel")
                .build();
    }

    @Bean
    public PlatformTransactionManager lobTransactionManagerFactory(
            final @Qualifier("lobEntityManagerFactory") LocalContainerEntityManagerFactoryBean lobEntityManagerFactor) {
        return new JpaTransactionManager(lobEntityManagerFactor.getObject());
    }
}
