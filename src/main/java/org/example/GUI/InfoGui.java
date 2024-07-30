package org.example.GUI;

import org.example.Utils.ScaleLayout;

import javax.swing.*;
import java.awt.*;

public class InfoGui implements InterfaceGUI {

    ScaleLayout scallingController = new ScaleLayout();
    static JPanel infoPanel = new JPanel();
    JLabel panelTitleLB = new JLabel("Information:");
    JLabel readItemInfoLB = new JLabel("to read items from database use: ------------------>");
    JLabel addItemInfoLB = new JLabel("to insert item into database use: ------------------>");
    JLabel settingsInfoLB = new JLabel("to change database settings use: ---------------->");
    JLabel logoutInfoLB = new JLabel("to logout use: ------------------------------------------->");


    public static JPanel initGUI()
    {
        InfoGui infoGui = new InfoGui();
        infoGui.setGUIParams();
        infoGui.addGUIComponents();
        infoGui.addGUIComponentsToListeners();
        infoGui.setGUIComponentsParams();
        return infoPanel;
    }

    @Override
    public void setGUIParams() {
        Point screenSize = scallingController.getWindowSize(80, 100);
        infoPanel.setSize(screenSize.x, screenSize.y);
        infoPanel.setVisible(true);
        infoPanel.setLayout(null);
        infoPanel.setBackground(Color.decode("#C0C0C0"));
    }

    @Override
    public void addGUIComponents() {
        infoPanel.add(panelTitleLB);
        infoPanel.add(readItemInfoLB);
        infoPanel.add(addItemInfoLB);
        infoPanel.add(settingsInfoLB);
        infoPanel.add(logoutInfoLB);
    }

    @Override
    public void addGUIComponentsToListeners() {
        //
    }

    @Override
    public void setGUIComponentsParams() {
        ScaleLayout scallingInfoPanel = new ScaleLayout(infoPanel.getWidth(), infoPanel.getHeight());
        scallingInfoPanel.setScallingParams(33, 10, 60, 5, 40, panelTitleLB, infoPanel);
        scallingInfoPanel.setScallingParams(75, 7, 50, 28, 25, readItemInfoLB, infoPanel);
        scallingInfoPanel.setScallingParams(75, 7, 50, 36, 25, addItemInfoLB, infoPanel);
        scallingInfoPanel.setScallingParams(75, 7, 50, 77, 25, settingsInfoLB, infoPanel);
        scallingInfoPanel.setScallingParams(75, 7, 50, 85, 25, logoutInfoLB, infoPanel);
    }
}
