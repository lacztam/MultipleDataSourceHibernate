package hu.multipledatasource.web;

import hu.multipledatasource.dto.AssembledDataModelDto;
import hu.multipledatasource.service.DataBaseService;
import hu.multipledatasource.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/database")
public class DataBaseController {

    @Autowired DataBaseService dataBaseService;
    @Autowired DataService dataService;

    @GetMapping("/get_all_id")
    public List<Long> getAllIds() {
        return dataService.getAllIds();
    }

    @GetMapping("/check_all_database_status")
    public boolean checkAllDatabaseStatus(){
        return dataBaseService.checkThatAllDbsIsUp();
    }

    @GetMapping("/make_backup")
    public List<AssembledDataModelDto> backupDataPartsIntoAnotherDatabase(){
        List<AssembledDataModelDto> assembledDataModelDtos = dataBaseService.backupDataPartsIntoAnotherDatbase();

        return assembledDataModelDtos;
    }

}
