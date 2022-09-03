package hu.multipledatasource.repository.BackupDatabase;

import hu.multipledatasource.model.AssembledData.AssembledDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupDatabaseRepository extends JpaRepository<AssembledDataModel, Long> {

}
