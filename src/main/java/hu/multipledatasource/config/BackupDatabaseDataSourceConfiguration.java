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
        basePackages = "hu.multipledatasource.repository.BackupDatabase",
        entityManagerFactoryRef = "backupEntityManagerFactory",
        transactionManagerRef= "backupTransactionManagerFactory")
public class BackupDatabaseDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.backup")
    public DataSourceProperties backupDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "backupDataSource")
    @ConfigurationProperties("spring.datasource.backup.configuration")
    public DataSource backupDataSource() {
        return backupDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "backupEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean backupEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(backupDataSource())
                .packages("hu.multipledatasource.model.AssembledData")
                .build();
    }

    @Bean
    public PlatformTransactionManager backupTransactionManagerFactory(
            final @Qualifier("backupEntityManagerFactory") LocalContainerEntityManagerFactoryBean backupEntityManagerFactor) {
        return new JpaTransactionManager(backupEntityManagerFactor.getObject());
    }
}
