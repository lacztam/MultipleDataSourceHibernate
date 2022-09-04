package hu.multipledatasource.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "hu.multipledatasource.repository.H2Database",
        entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef= "h2TransactionManagerFactory")
public class H2DataSourceConfiguration {


    @Bean
    @ConfigurationProperties("spring.datasource.h2")
    public DataSourceProperties h2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "h2DataSource")
    @ConfigurationProperties("spring.datasource.h2.configuration")
    public DataSource h2DataSource() {
        try{
            return h2DataSourceProperties().initializeDataSourceBuilder()
                    .type(HikariDataSource.class).build();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(h2DataSource())
                .packages("hu.multipledatasource.model.H2")
                .build();
    }

    @Bean
    public PlatformTransactionManager h2TransactionManagerFactory(
            final @Qualifier("h2EntityManagerFactory") LocalContainerEntityManagerFactoryBean h2EntityManagerFactor) {
        return new JpaTransactionManager(h2EntityManagerFactor.getObject());
    }

}
