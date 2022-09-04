package hu.multipledatasource.service;

import org.springframework.stereotype.Service;

@Service
public class ProfileTwoService implements ProfileService {

    @Override
    public String returnProfileName() {
        return "Profile two";
    }
}
