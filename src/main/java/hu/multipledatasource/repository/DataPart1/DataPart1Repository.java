package hu.multipledatasource.repository.DataPart1;

import hu.multipledatasource.model.Data1.DataPart1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DataPart1Repository extends JpaRepository<DataPart1, Long> {


    @Query("SELECT distinct d.id " +
            "FROM DataPart1 d")
    Optional<List<Long>> getAllIds();

}
