package org.example.Settings;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
public class AppSettings implements Serializable {

    String databaseHost;
    String databasePort;
    String databaseName;
    String databaseLogin;
    String databasePassword;
    ArrayList<String> locations;

    static AppSettings currentAppSettings = null;

    public AppSettings(String databaseHost, String databasePort, String databaseName, String databaseLogin, String databasePassword, ArrayList<String> locations) {
        this.databaseHost = databaseHost;
        this.databasePort = databasePort;
        this.databaseName = databaseName;
        this.databaseLogin = databaseLogin;
        this.databasePassword = databasePassword;
        this.locations = locations;
    }

    public AppSettings() {
    }

    public AppSettings getCurrentAppSettings()
    {
        if(currentAppSettings == null)
        {
            return new AppSettings();
        }
        return currentAppSettings;
    }

    public void setCurrentAppSettings(AppSettings newAppSettings)
    {
        currentAppSettings = newAppSettings;
    }
}
