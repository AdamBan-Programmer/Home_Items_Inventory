package org.example.Memory;

import java.io.File;
import java.io.IOException;

public class FileScanner {

    private static final String APP_NAME = "Home_Items_Inventory";
    private static final String APP_SAVE_LOCATION = "C:/";
    private static final String APP_SETTINGS_DIR = "Settings";
    private static final String SETTINGS_SAVE_FILE = "Settings.ser";

    public void checkAllDirectoriesExist() throws IOException {
        checkDirectoryExists(APP_SAVE_LOCATION,APP_NAME);
        checkDirectoryExists(APP_SAVE_LOCATION + APP_NAME + "/",APP_SETTINGS_DIR);
        checkSaveFileExists(APP_SAVE_LOCATION + APP_NAME + "/" + APP_SETTINGS_DIR + "/"+SETTINGS_SAVE_FILE);
    }

    //checks the saves folder exists
    private void checkDirectoryExists(String path, String dirName) {
        String directoryPath = path + dirName;
        java.io.File settingsDirectory = new java.io.File(directoryPath);
        if (!settingsDirectory.exists()) {
            settingsDirectory.mkdirs();
        }
    }

    //checks settings.ser exists
    private void checkSaveFileExists(String path) throws IOException {
        File settingsDir = new File(path);
        if(!settingsDir.exists())
        {
            settingsDir.createNewFile();
        }
    }
}
