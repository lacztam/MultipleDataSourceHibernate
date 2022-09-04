package hu.multipledatasource;

import hu.multipledatasource.config.CanModifyData;
import hu.multipledatasource.model.Data1.DataPart1;
import hu.multipledatasource.model.Data2.DataPart2;
import hu.multipledatasource.model.Data3.DataPart3;
import hu.multipledatasource.model.LobModel.LobModel;
import hu.multipledatasource.service.DataService;
import hu.multipledatasource.service.LobService;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class MultipleDataSourceHibernateApplication implements CommandLineRunner {

    @Autowired DataService dataService;
    @Autowired CanModifyData canUploadData;
    @Autowired LobService lobService;

    private static ConfigurableApplicationContext context;

    public static void main(String[] args)  {
        SpringApplication springApplication = new SpringApplication(MultipleDataSourceHibernateApplication.class);

/*        Properties properties = new Properties();
        properties.put("spring.datasource.lob.url", "jdbc:postgresql://localhost:7758/postgres");
        properties.put("spring.datasource.lob.username", "postgres");
        properties.put("spring.datasource.lob.password", "pw");
        properties.put("spring.datasource.lob.driver-class-name", "org.postgresql.Driver");
        springApplication.setDefaultProperties(properties);*/

        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        //checkModel();
        //createSchemaManually();
    }

    public void checkModel(){
        System.out.println("canUploadData.canUpload: " + canUploadData.isCanModify());

        // test data for creating db schema
        DataPart1 dataPart1 = new DataPart1();
        dataPart1.setName("test");
        dataService.saveDataPart1(dataPart1);

        DataPart2 dataPart2 = new DataPart2();
        dataService.saveDataPart2(dataPart2);

        DataPart3 dataPart3 = new DataPart3();
        dataService.saveDataPart3(dataPart3);

        LobModel lobModel = new LobModel();
        lobModel.setName("name");
        lobModel.setSize(1234);
        lobModel = lobService.save(lobModel);
    }

    public void createSchemaManually(){

        // generate database schemas manually
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
    }

    public static void restartApplication() {
        ApplicationArguments args = context.getBean(ApplicationArguments.class);

        Thread thread = new Thread(() -> {
            context.close();
            context = SpringApplication.run(MultipleDataSourceHibernateApplication.class, args.getSourceArgs());
        });

        thread.setDaemon(false);
        thread.start();
    }


}