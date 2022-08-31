package hu.multipledatasource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultipleDataSourceHibernateApplication implements CommandLineRunner {

    public static void main(String[] args)  {
        SpringApplication.run(MultipleDataSourceHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

// creating database schemas manually
 /*
        Map<String, String> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:7754/postgres");
        settings.put("hibernate.connection.username", "postgres");
        settings.put("hibernate.connection.password", "pw");
//        settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");
        settings.put("hibernate.hbm2ddl.auto", "create-drop");
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(DataPart1.class);
//        metadataSources.addAnnotatedClass(DataPart2.class);
//        metadataSources.addAnnotatedClass(DataPart3.class);
        Metadata metadata = metadataSources.buildMetadata();

        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setFormat(true);
        schemaExport.setOutputFile("create.sql");
        schemaExport.createOnly(EnumSet.of(TargetType.SCRIPT), metadata);
        */
    }
}