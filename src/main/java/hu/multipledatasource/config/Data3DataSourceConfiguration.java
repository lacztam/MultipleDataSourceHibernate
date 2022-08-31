package hu.multipledatasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "hu.multipledatasource.repository.DataPart3",
        entityManagerFactoryRef = "data3EntityManagerFactory",
        transactionManagerRef= "data3TransactionManagerFactory"
)
public class Data3DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.data3")
    public DataSourceProperties data3DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "data3DataSource")
    @ConfigurationProperties("spring.datasource.data3.configuration")
    public DataSource data3DataSource() {
        try{
            return data3DataSourceProperties().initializeDataSourceBuilder()
                    .type(HikariDataSource.class).build();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "data3EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean data3EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(data3DataSource())
                .packages("hu.multipledatasource.model.Data3")
                .build();
    }

    @Bean
    public PlatformTransactionManager data3TransactionManagerFactory(
            final @Qualifier("data3EntityManagerFactory") LocalContainerEntityManagerFactoryBean data3EntityManagerFactor) {
        return new JpaTransactionManager(data3EntityManagerFactor.getObject());
    }

    @Bean(name = "data3Jdbc")
    JdbcTemplate data3Jdbc(@Qualifier("data3DataSource") DataSource data3DataSource){
        return new JdbcTemplate(data3DataSource);
    }

}
