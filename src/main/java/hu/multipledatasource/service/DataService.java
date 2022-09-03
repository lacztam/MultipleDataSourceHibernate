package hu.multipledatasource.service;

import hu.multipledatasource.config.CanModifyData;
import hu.multipledatasource.config.LobDirectoryConfig;
import hu.multipledatasource.model.AssembledData.AssembledDataModel;
import hu.multipledatasource.model.Data1.DataPart1;
import hu.multipledatasource.model.Data2.DataPart2;
import hu.multipledatasource.model.Data3.DataPart3;
import hu.multipledatasource.model.DatasWrapper;
import hu.multipledatasource.repository.DataPart1.DataPart1Repository;
import hu.multipledatasource.repository.DataPart2.DataPart2Repository;
import hu.multipledatasource.repository.DataPart3.DataPart3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    @Autowired DataPart1Repository dataPart1Repository;
    @Autowired DataPart2Repository dataPart2Repository;
    @Autowired DataPart3Repository dataPart3Repository;
    @Autowired @Qualifier("data1DataSource")
    DataSource data1DataSource;
    @Autowired @Qualifier("data3DataSource")
    DataSource data3DataSource;
    @Autowired @Qualifier("data2DataSource")
    DataSource data2DataSource;
    @Autowired @Qualifier("data3Jdbc")
    JdbcTemplate data3Jdbc;
    @Autowired CanModifyData canUploadData;
    @Lazy @Autowired DataBaseService dataBaseService;
    @Autowired LobDirectoryConfig lobDirectoryConfig;

    public AssembledDataModel assembleData(long dataPart1ID) {
        boolean checkThatAllDbsIsUp = dataBaseService.checkThatAllDbsIsUp();

        if (checkThatAllDbsIsUp) {
            byte[] part1 = new byte[0];
            Optional<DataPart1> optionalDataPart1 = dataPart1Repository.findById(dataPart1ID);
            if (optionalDataPart1.isPresent()) {
                part1 = optionalDataPart1.get().getPart1Binaries();
            } else {
                throw new NullPointerException("There is no file with this id:" + dataPart1ID);
            }

            byte[] part2 = new byte[0];
            Optional<DataPart2> optionalDataPart2 = dataPart2Repository.findByDataPart1Id(dataPart1ID);
            if (optionalDataPart2.isPresent())
                part2 = optionalDataPart2.get().getPart2Binaries();

            byte[] part3 = new byte[0];
            Optional<DataPart3> optionalDataPart3 = dataPart3Repository.findByDataPart1Id(dataPart1ID);
            if (optionalDataPart3.isPresent())
                part3 = optionalDataPart3.get().getPart3Binaries();

            List<Byte> assembleList = new ArrayList<>();
            for (int i = 0; i < part1.length; i++)
                assembleList.add(part1[i]);

            for (int i = 0; i < part2.length; i++)
                assembleList.add(part2[i]);

            for (int i = 0; i < part3.length; i++)
                assembleList.add(part3[i]);

            byte[] assemble = new byte[assembleList.size()];
            for (int i = 0; i < assembleList.size(); i++) {
                assemble[i] = assembleList.get(i);
            }

            AssembledDataModel assembledDataModel = new AssembledDataModel();
            assembledDataModel.setName(optionalDataPart1.get().getName());
            assembledDataModel.setDataBinaries(assemble);

            return assembledDataModel;
        } else {
            throw new NullPointerException("One of or all of the database connections were not established.");
        }
    }

    public List<Long> getAllIds() {
        Optional<List<Long>> allIds = dataPart1Repository.getAllIds();
        if (allIds.isPresent())
            return allIds.get();
        else
            throw new NullPointerException("The list is null.");
    }

    public DatasWrapper uploadFileToDatabase(MultipartFile file) {
        boolean canUpload = canUploadData.isCanModify();
        if (canUpload) {
            boolean checkAllDatabaseConnection = dataBaseService.checkThatAllDbsIsUp();

            if (checkAllDatabaseConnection) {
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
                dataPart1.setName(file.getOriginalFilename());
                dataPart1.setPart1Binaries(part1);

                DataPart2 dataPart2 = new DataPart2();
                dataPart2.setPart2Binaries(part2);

                DataPart3 dataPart3 = new DataPart3();
                dataPart3.setPart3Binaries(part3);

                DatasWrapper upload = upload(dataPart1, dataPart2, dataPart3);

                return upload;
            } else {
                throw new NullPointerException("One of or all of the database connections were not established.");
            }
        } else {
            throw new RuntimeException("Currently can not upload data, backing up database ..");
        }
    }

    private DatasWrapper upload(DataPart1 dataPart1, DataPart2 dataPart2, DataPart3 dataPart3) {
        boolean checkAllDatabaseConnection = dataBaseService.checkThatAllDbsIsUp();
        if (checkAllDatabaseConnection) {
            dataPart1 = dataPart1Repository.save(dataPart1);

            dataPart2.setDataPart1Id(dataPart1.getId());
            dataPart3.setDataPart1Id(dataPart1.getId());

            dataPart2 = dataPart2Repository.save(dataPart2);
            dataPart3 = dataPart3Repository.save(dataPart3);

            DatasWrapper datasWrapper = new DatasWrapper(dataPart1, dataPart2, dataPart3);

            return datasWrapper;
        } else {
            throw new NullPointerException("One of or all of the database connections were not established.");
        }
    }

    public DataPart1 saveDataPart1(DataPart1 dataPart1) {
        boolean canUpload = canUploadData.isCanModify();
        if (canUpload) {
            return dataPart1Repository.save(dataPart1);
        } else {
            throw new RuntimeException("Currently can not save entity, backing up database ..");
        }
    }

    public DataPart2 saveDataPart2(DataPart2 dataPart2) {
        boolean canUpload = canUploadData.isCanModify();
        if (canUpload) {
            return dataPart2Repository.save(dataPart2);
        } else {
            throw new RuntimeException("Currently can not save entity, backing up database ..");
        }
    }

    public DataPart3 saveDataPart3(DataPart3 dataPart3) {
        boolean canUpload = canUploadData.isCanModify();
        if (canUpload) {
            return dataPart3Repository.save(dataPart3);
        } else {
            throw new RuntimeException("Currently can not save entity, backing up database ..");
        }
    }
}
