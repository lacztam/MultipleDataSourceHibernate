/*
package hu.multipledatasource.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hu.multipledatasource.service.PropertiesService;
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
        basePackages = "hu.multipledatasource.repository.PropertiesDatabase",
        entityManagerFactoryRef = "propertiesEntityManagerFactory",
        transactionManagerRef= "propertiesTransactionManagerFactory")
public class PropertiesDataSourceConfiguration {

    @Autowired Environment environment;
    @Autowired PropertiesService propertiesService;

    @Bean(name = "propertiesDataSource")
    public DataSource propertiesDataSource() {
*/
/*        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/spring-test");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("pw");

        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP");

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);*//*


        HikariDataSource dataSource = new HikariDataSource(propertiesService.getActiveProfile());

        return dataSource;
    }

    @Bean(name = "propertiesEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean propertiesEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(propertiesDataSource())
                .packages("hu.multipledatasource.model.PropertiesDatabase")
                .build();
    }

    @Bean
    public PlatformTransactionManager propertiesTransactionManagerFactory(
            final @Qualifier("propertiesEntityManagerFactory") LocalContainerEntityManagerFactoryBean propertiesEntityManagerFactor) {
        return new JpaTransactionManager(propertiesEntityManagerFactor.getObject());
    }

}
*/
