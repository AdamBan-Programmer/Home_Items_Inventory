package org.example.Settings;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
public final class AppSettings implements Serializable {

    String databaseHost;
    String databasePort;
    String databaseName;
    String databaseLogin;
    String databasePassword;
    ArrayList<String> locations;

    private static AppSettings currentAppSettings;

    private AppSettings(String databaseHost, String databasePort, String databaseName, String databaseLogin, String databasePassword, ArrayList<String> locations) {
        this.databaseHost = databaseHost;
        this.databasePort = databasePort;
        this.databaseName = databaseName;
        this.databaseLogin = databaseLogin;
        this.databasePassword = databasePassword;
        this.locations = locations;
    }
    public static AppSettings getInstance()
    {
        if(currentAppSettings == null)
        {
            currentAppSettings = new AppSettings("","","","","",new ArrayList<String>());
        }
        return currentAppSettings;
    }
}
