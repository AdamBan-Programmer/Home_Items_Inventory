package org.example.Main;
import org.example.GUI.CurrentGuiEnum;
import org.example.GUI.ManagerGUI;
import org.example.Memory.FileScanner;
import org.example.Memory.MemoryOperations;

import javax.swing.*;
import java.io.IOException;

public class Main {

    static FileScanner fileScanner = new FileScanner();
    static MemoryOperations memoryController = new MemoryOperations();
    static ManagerGUI guiController = new ManagerGUI();

    public static void main(String[] args) {
        try {
            guiController.createFrame();
            fileScanner.checkAllDirectoriesExist();
            memoryController.readSerializedSettings();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showConfirmDialog(guiController.getMainWindowFrame(), "Couldn't load Settings!", "Warning!", JOptionPane.DEFAULT_OPTION);
        }
        finally {
            guiController.loadPanelsMap();
            guiController.changeCurrentWindow(CurrentGuiEnum.LOGIN);
        }
    }
}