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

    private String username;
    private String password;

    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Authentication() {
    }

}
