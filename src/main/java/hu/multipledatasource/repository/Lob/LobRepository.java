package hu.multipledatasource.repository.Lob;

import hu.multipledatasource.model.LobModel.LobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LobRepository extends JpaRepository<LobModel, Long> {

    @Query("SELECT l.id " +
            "FROM LobModel l " )
    List<Long> getAllLobIds();
}
