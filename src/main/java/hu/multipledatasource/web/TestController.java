package hu.multipledatasource.web;

import hu.multipledatasource.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired ProfileService profileService;

    @GetMapping
    public String returnProfileName(){
        return profileService.returnProfileName();
    }

}
