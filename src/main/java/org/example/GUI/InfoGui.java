package org.example.GUI;

import org.example.Utils.ScaleLayout;

import javax.swing.*;
import java.awt.*;

public class InfoGui implements CreatorGUI {

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
        infoGui.setGuiParams();
        infoGui.addGuiComponents();
        infoGui.addGuiComponentsToListeners();
        infoGui.setGuiComponentsParams();
        return infoPanel;
    }

    @Override
    public void setGuiParams() {
        Point screenSize = scallingController.getWindowSize(80, 100);
        infoPanel.setSize(screenSize.x, screenSize.y);
        infoPanel.setVisible(true);
        infoPanel.setLayout(null);
        infoPanel.setBackground(Color.decode("#C0C0C0"));
    }

    @Override
    public void addGuiComponents() {
        infoPanel.add(panelTitleLB);
        infoPanel.add(readItemInfoLB);
        infoPanel.add(addItemInfoLB);
        infoPanel.add(settingsInfoLB);
        infoPanel.add(logoutInfoLB);
    }

    @Override
    public void addGuiComponentsToListeners() {
        //
    }

    @Override
    public void setGuiComponentsParams() {
        scallingController.setScallingParams(33, 10, 60, 5, 40, panelTitleLB, infoPanel);
        scallingController.setScallingParams(75, 7, 50, 28, 25, readItemInfoLB, infoPanel);
        scallingController.setScallingParams(75, 7, 50, 36, 25, addItemInfoLB, infoPanel);
        scallingController.setScallingParams(75, 7, 50, 77, 25, settingsInfoLB, infoPanel);
        scallingController.setScallingParams(75, 7, 50, 85, 25, logoutInfoLB, infoPanel);
    }
}
