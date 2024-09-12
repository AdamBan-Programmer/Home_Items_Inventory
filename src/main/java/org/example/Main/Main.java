package org.example.Main;
import org.example.GUI.CurrentGuiEnum;
import org.example.GUI.ManagerGUI;
import org.example.Memory.FileScanner;
import org.example.Memory.MemoryOperations;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    static FileScanner fileScanner = new FileScanner();
    static MemoryOperations memoryController = new MemoryOperations();

    public static void main(String[] args) {
        try {
            fileScanner.checkAllDirectoriesExist();
            memoryController.readSerializedSettings();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            EventQueue.invokeLater(new Runnable()
            {
                public void run()
                {
                    new ManagerGUI();
                }
            });
        }
    }
}