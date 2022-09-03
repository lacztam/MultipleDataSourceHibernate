package hu.multipledatasource.service;

import hu.multipledatasource.config.CanModifyData;
import hu.multipledatasource.dto.AssembledDataModelDto;
import hu.multipledatasource.mapper.AssembledDataMapper;
import hu.multipledatasource.model.AssembledData.AssembledDataModel;
import hu.multipledatasource.repository.BackupDatabase.BackupDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseService {

    @Autowired DataService dataService;
    @Autowired BackupDatabaseRepository backupDatabaseRepository;
    @Autowired
    CanModifyData canUploadData;
    @Autowired AssembledDataMapper assembledDataMapper;

    @Transactional
    public boolean checkThatAllDbsIsUp() {
        boolean valid = false;
        Connection connection = null;
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // All the databases have the same credentials
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("pw");

        try {
            // db1
            dataSource.setUrl("jdbc:postgresql://localhost:7754/postgres");
            connection = dataSource.getConnection();
            valid = connection.isValid(10000);

            // db2
            dataSource.setUrl("jdbc:postgresql://localhost:7755/postgres");
            connection = dataSource.getConnection();
            valid = connection.isValid(10000);

            // db3
            dataSource.setUrl("jdbc:postgresql://localhost:7756/postgres");
            connection = dataSource.getConnection();
            valid = connection.isValid(10000);
            connection.close();

            return valid;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public List<AssembledDataModelDto> backupDataPartsIntoAnotherDatbase(){
        // while backing up the data from the three databases there should be no modification (upload, modify, remove)
        canUploadData.setCanModify(false);

        List<AssembledDataModelDto> datasDto = new ArrayList<>();

        List<Long> ids = dataService.getAllIds();
        for(long id : ids){
            AssembledDataModel assembledDataModel = dataService.assembleData(id);
            backupDatabaseRepository.save(assembledDataModel);
            datasDto.add(assembledDataMapper.dataToDto(assembledDataModel));
        }
        canUploadData.setCanModify(true);

        return datasDto;
    }

}
