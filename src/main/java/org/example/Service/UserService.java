package org.example.Service;

import org.example.Authentication.Authentication;
import org.example.Authentication.AuthenticationStatusEnum;
import org.example.Entity.User;

import java.util.List;

public class UserService {

    DatabaseConnectionService databaseService = new DatabaseConnectionService();

    //returns user after validation
    public User validUser(Authentication authentication) {
        List<User> users = databaseService.getAccounts(authentication);
        try {
            return users.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
