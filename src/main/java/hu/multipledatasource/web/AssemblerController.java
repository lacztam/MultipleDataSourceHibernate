package hu.multipledatasource.web;


import hu.multipledatasource.model.Data1.DataPart1;
import hu.multipledatasource.model.Data2.DataPart2;
import hu.multipledatasource.model.Data3.DataPart3;
import hu.multipledatasource.service.AssemblerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AssemblerController {

    @Autowired
    AssemblerService assembleService;

    @Autowired
    @Qualifier("data1DataSource")
    DataSource data1DataSource;

    @GetMapping("/getAllId")
    public List<Long> getAllIds() {
        return assembleService.getAllIds();
    }

    @GetMapping("/download/{id}")
    public @ResponseBody byte[] download(@PathVariable long id) {

        byte[] assembled = assembleService.download(id);

        return assembled;
    }

    @PostMapping("/upload")
    public DataPart1 upload(@RequestPart("file") MultipartFile file) {

        byte[] fileByteArr;
        try {
            fileByteArr = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < fileByteArr.length; i++) {
            list.add(fileByteArr[i]);
        }

        int listSize1 = fileByteArr.length / 3;
        int listSize2 = (fileByteArr.length - listSize1) / 2;
        int listSize3 = fileByteArr.length - listSize1 - listSize2;

        byte[] part1 = new byte[listSize1];
        for (int i = 0; i < listSize1; i++) {
            part1[i] = fileByteArr[i];
        }
        byte[] part2 = new byte[listSize2];
        for (int i = listSize1, j = 0; i < (listSize1 + listSize2); i++, j++) {
            part2[j] = fileByteArr[i];
        }
        byte[] part3 = new byte[listSize3];
        for (int i = listSize1 + listSize2, j = 0; i < listSize1 + listSize2 + listSize3; i++, j++) {
            part3[j] = fileByteArr[i];
        }

        DataPart1 dataPart1 = new DataPart1();
        dataPart1.setPart1(part1);

        DataPart2 dataPart2 = new DataPart2();
        dataPart2.setPart2(part2);

        DataPart3 dataPart3 = new DataPart3();
        dataPart3.setPart3(part3);

        DataPart1 upload = assembleService.upload(dataPart1, dataPart2, dataPart3);
        upload.setPart1(null);
        return upload;
    }

    @GetMapping("getDb1Status")
    public boolean getDBStatus() {
        try {

            // db1
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:7754/postgres");
            dataSource.setUsername("postgres");
            dataSource.setPassword("pw");

            Connection connection = dataSource.getConnection();
            boolean valid = connection.isValid(10000);
            connection.close();

            return valid;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
