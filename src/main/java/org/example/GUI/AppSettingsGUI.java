package org.example.GUI;

import org.example.Settings.AppSettings;
import org.example.Memory.MemoryOperations;
import org.example.Utils.ScaleLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AppSettingsGUI implements ActionListener, CreatorGUI {

    ScaleLayout scallingController = new ScaleLayout();
    MemoryOperations memoryController = new MemoryOperations();

    static JPanel appSettingsPanel = new JPanel();
    JLabel panelTitleLB = new JLabel("Settings:");
    JLabel databasePortLB = new JLabel("Set database Port:");
    JTextField databasePortTF = new JTextField();
    JLabel databaseHostLB = new JLabel("Set database Host:");
    JTextField databaseHostTF = new JTextField();
    JLabel databaseNameLB = new JLabel("Set database Name:");
    JTextField databaseNameTF = new JTextField();
    JLabel databaseLoginLB = new JLabel("Set database Login:");
    JPasswordField databaseLoginTF = new JPasswordField();
    JLabel databasePasswordLB = new JLabel("Set database Password:");
    JPasswordField databasePasswordTF = new JPasswordField();
    JLabel locationsLB = new JLabel("locations: ");
    JComboBox locationsCB = new JComboBox();
    JButton addLocationBT = new JButton("ADD");
    JButton removeLocationBT = new JButton("REMOVE");
    JButton saveBT = new JButton("SAVE");


    public static JPanel initGUI() {
        AppSettingsGUI settingsGui = new AppSettingsGUI();
        settingsGui.setGuiParams();
        settingsGui.addGuiComponents();
        settingsGui.addGuiComponentsToListeners();
        settingsGui.setGuiComponentsParams();
        settingsGui.setSettingsIntoControls();
        return appSettingsPanel;
    }

    @Override
    public void setGuiParams() {
        Point settingsPanelSize = scallingController.getWindowSize(80, 100);
        appSettingsPanel.setSize(settingsPanelSize.x, settingsPanelSize.y);
        appSettingsPanel.setVisible(true);
        appSettingsPanel.setLayout(null);
        appSettingsPanel.setBackground(Color.decode("#C0C0C0"));
    }

    @Override
    public void addGuiComponents() {
        appSettingsPanel.add(panelTitleLB);
        appSettingsPanel.add(databasePortLB);
        appSettingsPanel.add(databasePortTF);
        appSettingsPanel.add(databaseHostLB);
        appSettingsPanel.add(databaseHostTF);
        appSettingsPanel.add(databaseNameLB);
        appSettingsPanel.add(databaseNameTF);
        appSettingsPanel.add(databaseLoginLB);
        appSettingsPanel.add(databaseLoginTF);
        appSettingsPanel.add(databasePasswordLB);
        appSettingsPanel.add(databasePasswordTF);
        appSettingsPanel.add(locationsLB);
        appSettingsPanel.add(locationsCB);
        appSettingsPanel.add(addLocationBT);
        appSettingsPanel.add(removeLocationBT);
        appSettingsPanel.add(saveBT);
    }

    @Override
    public void addGuiComponentsToListeners() {
        saveBT.addActionListener(this);
        addLocationBT.addActionListener(this);
        removeLocationBT.addActionListener(this);
    }

    @Override
    public void setGuiComponentsParams() {
        scallingController.setScallingParams(33, 10, 60, 5, 40, panelTitleLB, appSettingsPanel);
        scallingController.setScallingParams(20, 20, 15, 15, 15, databasePortLB, appSettingsPanel);
        scallingController.setScallingParams(30, 5, 50, 22.5f, 40, databasePortTF, appSettingsPanel);
        scallingController.setScallingParams(20, 20, 15, 25, 15,databaseHostLB, appSettingsPanel);
        scallingController.setScallingParams(30, 5, 50, 32.5f, 40,databaseHostTF, appSettingsPanel);
        scallingController.setScallingParams(20, 20, 15, 35, 15,databaseNameLB, appSettingsPanel);
        scallingController.setScallingParams(30, 5, 50, 42.5f, 40,databaseNameTF, appSettingsPanel);
        scallingController.setScallingParams(20, 20, 15, 45, 15,databaseLoginLB, appSettingsPanel);
        scallingController.setScallingParams(30, 5, 50, 52.5f, 40,databaseLoginTF, appSettingsPanel);
        scallingController.setScallingParams(20, 20, 15, 55, 15,databasePasswordLB, appSettingsPanel);
        scallingController.setScallingParams(30, 5, 50, 62.5f, 40,databasePasswordTF, appSettingsPanel);

        scallingController.setScallingParams(20, 5, 40, 18, 75, locationsLB, appSettingsPanel);
        scallingController.setScallingParams(20, 5, 30, 22, 75, locationsCB, appSettingsPanel);
        scallingController.setScallingParams(9.5f, 5, 15, 28, 75, addLocationBT, appSettingsPanel);
        scallingController.setScallingParams(9.5f, 5, 15, 28, 85.5f, removeLocationBT, appSettingsPanel);

        scallingController.setScallingParams(33, 7, 20, 80, 33,saveBT, appSettingsPanel);
        saveBT.setBackground(Color.decode("#d4d4d4"));
        addLocationBT.setBackground(Color.decode("#d4d4d4"));
        removeLocationBT.setBackground(Color.decode("#d4d4d4"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();

        if(component == addLocationBT)
        {
            String newLocation = JOptionPane.showInputDialog(appSettingsPanel, "Write location name: ", "Add location:", JOptionPane.DEFAULT_OPTION);
            if(!newLocation.isEmpty()) {
                locationsCB.addItem(newLocation);
            }
        }

        if(component == removeLocationBT)
        {
            int index = locationsCB.getSelectedIndex();
            locationsCB.removeItemAt(index);
        }

        if (component == saveBT) {
            try {
                updateCurrentSettings();
                memoryController.serializeSettings(AppSettings.getInstance());
                JOptionPane.showConfirmDialog(appSettingsPanel, "Settings has been saved.","Warning!", JOptionPane.DEFAULT_OPTION);
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(appSettingsPanel, "Couldn't save Settings!","Warning!", JOptionPane.DEFAULT_OPTION);
            }
        }
    }

    private void setSettingsIntoControls()
    {
        AppSettings settings = AppSettings.getInstance();
        databasePortTF.setText(settings.getDatabasePort());
        databaseHostTF.setText(settings.getDatabaseHost());
        databaseNameTF.setText(settings.getDatabaseName());
        databaseLoginTF.setText(settings.getDatabaseLogin());
        databasePasswordTF.setText(settings.getDatabasePassword());
        loadLocations();
    }

    private void updateCurrentSettings()
    {
        AppSettings settings = AppSettings.getInstance();
        settings.setDatabasePort(databasePortTF.getText());
        settings.setDatabaseHost(databaseHostTF.getText());
        settings.setDatabaseName(databaseNameTF.getText());
        settings.setDatabaseLogin(databaseLoginTF.getText());
        settings.setDatabasePassword(databasePasswordTF.getText());
        settings.setLocations(getLocations());
    }

    private ArrayList<String> getLocations()
    {
        ArrayList<String> array = new ArrayList<>();
        int size = locationsCB.getItemCount();
        for(int i =0;i<size;i++)
        {
            String location = locationsCB.getItemAt(i).toString();
            array.add(location);
        }
        return array;
    }

    private void loadLocations()
    {
       ArrayList<String> locations = AppSettings.getInstance().getLocations();
       if(locations != null) {
           for (String location : locations) {
               locationsCB.addItem(location);
           }
       }
    }
}
