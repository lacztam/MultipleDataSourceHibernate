/*
package hu.multipledatasource.repository.PropertiesDatabase;

import hu.multipledatasource.model.Properties.PropertiesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropertiesDatabaseRepository extends JpaRepository<PropertiesModel, Long> {

    @Query("SELECT p.id " +
            "FROM PropertiesModel  p ")
    public List<Long> getAllid();


    @Modifying
    @Query("UPDATE PropertiesModel p " +
            "SET p.currentlyInUse = false " +
            "WHERE p.id = :id")
    public PropertiesModel disableOtherProperties(long id);


    @Query("SELECT p " +
            "FROM PropertiesModel p " +
            "WHERE p.currentlyInUse = true ")
    public Optional<PropertiesModel> findActiveProfile();

}
*/
