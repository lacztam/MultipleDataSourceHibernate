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
@EnableJpaRepositories(basePackages = "hu.multipledatasource.repository.DataPart2",
        entityManagerFactoryRef = "data2EntityManagerFactory",
        transactionManagerRef= "data2TransactionManagerFactory"
)
public class Data2DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.data2")
    public DataSourceProperties data2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "data2DataSource")
    @ConfigurationProperties("spring.datasource.data2.configuration")
    public DataSource data2DataSource() {


        return data2DataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "data2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean data2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(data2DataSource())
                .packages("hu.multipledatasource.model.Data2")
                .build();
    }

    @Bean
    public PlatformTransactionManager data2TransactionManagerFactory(
            final @Qualifier("data2EntityManagerFactory") LocalContainerEntityManagerFactoryBean data2EntityManagerFactor) {
        return new JpaTransactionManager(data2EntityManagerFactor.getObject());
    }
}
