package hu.multipledatasource.service;

import hu.multipledatasource.model.Data1.DataPart1;
import hu.multipledatasource.model.Data2.DataPart2;
import hu.multipledatasource.model.Data3.DataPart3;
import hu.multipledatasource.repository.DataPart1.DataPart1Repository;
import hu.multipledatasource.repository.DataPart2.DataPart2Repository;
import hu.multipledatasource.repository.DataPart3.DataPart3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssemblerService {

    @Autowired DataPart1Repository dataPart1Repository;
    @Autowired DataPart2Repository dataPart2Repository;
    @Autowired DataPart3Repository dataPart3Repository ;

    @Autowired @Qualifier("data1DataSource")
    DataSource data1DataSource;
    @Autowired @Qualifier("data3DataSource")
    DataSource data3DataSource;
    @Autowired @Qualifier("data2DataSource")
    DataSource data2DataSource;

    @Autowired @Qualifier("data3Jdbc")
    JdbcTemplate data3Jdbc;

    private void checkDBsConnections(){
        Connection data3Connection = null;
        try{
            data3Connection = data3DataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("\n----------------");
            throw new RuntimeException(e);
        }finally {
            data3Jdbc.setDataSource(null);

            System.out.println("sat null datasource");
        }

        try {
            Connection data1Connection = data1DataSource.getConnection();
            Connection data2Connection = data2DataSource.getConnection();
        } catch (Exception e) {
            System.out.println("e.getMessage():\n" + e.getMessage());
            e.printStackTrace();
            System.out.println("\n----------------");
            throw new RuntimeException(e);
        }
    }

    public DataPart1 upload(DataPart1 dataPart1, DataPart2 dataPart2, DataPart3 dataPart3) {
        checkDBsConnections();
        dataPart1 = dataPart1Repository.save(dataPart1);
        dataPart2.setDataPart1Id(dataPart1.getId());
        dataPart3.setDataPart1Id(dataPart1.getId());
        dataPart2Repository.save(dataPart2);
        dataPart3Repository.save(dataPart3);

        return dataPart1;
    }

    public byte[] download(long dataPart1ID){

        byte[] part1 = new byte[0];
        Optional<DataPart1> optionalDataPart1 = dataPart1Repository.findById(dataPart1ID);
        if(optionalDataPart1.isPresent()){
            part1 =  optionalDataPart1.get().getPart1();
        }else{
            throw new NullPointerException("There is no file with this id:" + dataPart1ID);
        }

        byte[] part2 = new byte[0];
        Optional<DataPart2> optionalDataPart2 = dataPart2Repository.findByDataPart1Id(dataPart1ID);
        if(optionalDataPart2.isPresent())
            part2 = optionalDataPart2.get().getPart2();

        byte[] part3 = new byte[0];
        Optional<DataPart3> optionalDataPart3 = dataPart3Repository.findByDataPart1Id(dataPart1ID);
        if(optionalDataPart3.isPresent())
            part3 = optionalDataPart3.get().getPart3();

        List<Byte> assembleList = new ArrayList<>();
        for(int i = 0; i < part1.length; i++)
            assembleList.add(part1[i]);

        for(int i = 0; i < part2.length; i++)
            assembleList.add(part2[i]);

        for(int i = 0; i < part3.length; i++)
            assembleList.add(part3[i]);

        byte[] assemble = new byte[assembleList.size()];
        for(int i = 0; i < assembleList.size(); i++){
            assemble[i] = assembleList.get(i);
        }

        return assemble;
    }

    public List<Long> getAllIds(){
        Optional<List<Long>> allIds = dataPart1Repository.getAllIds();
        if(allIds.isPresent())
            return allIds.get();
        else
            throw new NullPointerException("The list is null.");
    }

}
