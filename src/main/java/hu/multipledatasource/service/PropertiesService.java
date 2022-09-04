/*
package hu.multipledatasource.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import hu.multipledatasource.model.Properties.PropertiesModel;
import hu.multipledatasource.repository.PropertiesDatabase.PropertiesDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertiesService {
    @Autowired
    PropertiesDatabaseRepository propertiesDatabaseRepository;

    public PropertiesModel getActiveProfile(){
        return propertiesDatabaseRepository.findActiveProfile()
                .orElseThrow(() -> new NullPointerException("There is no active profile."));
    }

    public List<Long> getAllPropertyModelId() {
        return propertiesDatabaseRepository.getAllid();
    }

    public List<PropertiesModel> findAllProperties(){
        return propertiesDatabaseRepository.findAll();
    }

    public PropertiesModel saveProperty(PropertiesModel propertiesModel) {
        return propertiesDatabaseRepository.save(propertiesModel);
    }

    public boolean removeProperty(long id) {
        PropertiesModel propertiesModel = propertiesDatabaseRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("There is no property with this id:" + id));

        propertiesDatabaseRepository.delete(propertiesModel);
        return true;
    }

    public List<PropertiesModel> disableOtherProperties(long exceptMe){
        List<Long> propertyIds = getAllPropertyModelId();

        List<PropertiesModel> propertiesModels = new ArrayList<>();

        for(long id : propertyIds){
            if(id == exceptMe){
                continue;
            }
            PropertiesModel currentModel = propertiesDatabaseRepository.disableOtherProperties(id);
            propertiesModels.add(currentModel);
        }

        return propertiesModels;
    }
}
*/
