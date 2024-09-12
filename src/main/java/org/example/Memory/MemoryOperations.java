package org.example.Memory;

import org.example.Settings.AppSettings;

import java.io.*;

public class MemoryOperations {

    private static final String APP_NAME = "Home_Items_Inventory";
    private static final String APP_SAVE_LOCATION = "C:/";
    private static final String APP_SETTINGS_DIR = "Settings";
    private static final String SETTINGS_SAVE_FILE = "Settings.ser";


    // Saves object into a file
    public void serializeSettings(AppSettings appSettings) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(APP_SAVE_LOCATION + APP_NAME + "/" + APP_SETTINGS_DIR + "/" + SETTINGS_SAVE_FILE);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(appSettings);
        out.close();
        fileOut.close();
    }

    //reads object from file
    public void readSerializedSettings() throws IOException, ClassNotFoundException, NullPointerException, ArrayIndexOutOfBoundsException {
        File directory = new File(APP_SAVE_LOCATION + APP_NAME + "/" + APP_SETTINGS_DIR);
        File[] serializedSettings = directory.listFiles();
        if (serializedSettings != null && serializedSettings.length > 0) {
            FileInputStream streamIn = new FileInputStream(serializedSettings[0]);
            ObjectInputStream objectInputStream = new ObjectInputStream(streamIn);
            updateAppSettings((AppSettings) objectInputStream.readObject());
        }
    }

    public static void updateAppSettings(AppSettings newAppSettings)
    {
        AppSettings currentSettings = AppSettings.getInstance();

        currentSettings.setDatabaseHost(newAppSettings.getDatabaseHost());
        currentSettings.setDatabasePort(newAppSettings.getDatabasePort());
        currentSettings.setDatabaseName(newAppSettings.getDatabaseName());
        currentSettings.setLocations(newAppSettings.getLocations());
        currentSettings.setDatabaseLogin(newAppSettings.getDatabaseLogin());
        currentSettings.setDatabasePassword(newAppSettings.getDatabasePassword());
        currentSettings.setLocations(newAppSettings.getLocations());
    }
}
