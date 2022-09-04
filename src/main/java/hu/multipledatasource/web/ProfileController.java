/*
package hu.multipledatasource.web;

import hu.multipledatasource.model.Properties.PropertiesModel;
import hu.multipledatasource.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class ProfileController {

    @Autowired PropertiesService propertiesService;

    @PutMapping
    public PropertiesModel setDataSource(@RequestBody PropertiesModel propertiesModel){
        return propertiesService.saveProperty(propertiesModel);
    }
}
*/
