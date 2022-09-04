package hu.multipledatasource.service;

import org.springframework.stereotype.Service;

@Service
public class ProfileOneService implements ProfileService{
    @Override
    public String returnProfileName() {
        return "Profile one.";
    }
}
