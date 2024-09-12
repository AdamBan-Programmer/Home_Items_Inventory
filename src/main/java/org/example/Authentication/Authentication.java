package org.example.Authentication;

import lombok.Getter;
import lombok.Setter;
import org.example.Connection.DatabaseConnection;
import org.example.Entity.User;
import org.example.Utils.SystemData;

import java.util.List;

@Getter
@Setter
public class Authentication {

    String username;
    String password;

    @Getter
    @Setter
    public static AuthenticationStatusEnum authenticationStatus = AuthenticationStatusEnum.LOGGED_OUT;

    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Authentication() {
    }

    //returns user after validation
    public User userValidated(Authentication authentication) {
        List<User> users = DatabaseConnection.getAccountList(authentication);
        try {
            return users.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
