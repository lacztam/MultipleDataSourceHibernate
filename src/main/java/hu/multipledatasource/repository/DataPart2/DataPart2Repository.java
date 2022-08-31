package hu.multipledatasource.repository.DataPart2;

import hu.multipledatasource.model.Data2.DataPart2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DataPart2Repository extends JpaRepository<DataPart2, Long> {

    @Query("SELECT d2 " +
            "FROM DataPart2 d2 " +
            "WHERE d2.dataPart1Id =:dataPart1Id ")
    Optional<DataPart2> findByDataPart1Id(long dataPart1Id);
}
