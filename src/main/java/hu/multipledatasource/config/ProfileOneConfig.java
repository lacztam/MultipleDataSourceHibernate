package hu.multipledatasource.config;

import hu.multipledatasource.service.ProfileOneService;
import hu.multipledatasource.service.ProfileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileOneConfig {

    @Bean
    @Profile("profile_one")
    public ProfileService profileService(){
        return new ProfileOneService();
    }
}
