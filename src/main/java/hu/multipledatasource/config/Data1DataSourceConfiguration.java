package hu.multipledatasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "hu.multipledatasource.repository.DataPart1",
        entityManagerFactoryRef = "data1EntityManagerFactory",
        transactionManagerRef= "data1TransactionManagerFactory")
public class Data1DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.data1")
    public DataSourceProperties data1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "data1DataSource")
    @ConfigurationProperties("spring.datasource.data1.configuration")
    public DataSource data1DataSource() {
        return data1DataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "data1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean data1EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(data1DataSource())
                .packages("hu.multipledatasource.model.Data1")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager data1TransactionManagerFactory(
            final @Qualifier("data1EntityManagerFactory") LocalContainerEntityManagerFactoryBean data1EntityManagerFactor) {
        return new JpaTransactionManager(data1EntityManagerFactor.getObject());
    }

}
