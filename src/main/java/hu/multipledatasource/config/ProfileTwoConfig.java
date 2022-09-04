package hu.multipledatasource.config;

import hu.multipledatasource.service.ProfileService;
import hu.multipledatasource.service.ProfileTwoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileTwoConfig {

    @Bean
    @Profile("profile_two")
    ProfileService profileService(){
        return new ProfileTwoService();
    }
}
