package hu.multipledatasource.repository.DataPart3;

import hu.multipledatasource.model.Data3.DataPart3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DataPart3Repository extends JpaRepository<DataPart3, Long> {

    @Query("SELECT d3 " +
            "FROM DataPart3 d3 " +
            "WHERE d3.dataPart1Id =:dataPart1Id ")
    Optional<DataPart3> findByDataPart1Id(long dataPart1Id);

}
