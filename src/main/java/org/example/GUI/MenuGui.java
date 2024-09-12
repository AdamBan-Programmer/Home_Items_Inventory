package org.example.GUI;

import org.example.Authentication.Authentication;
import org.example.Authentication.AuthenticationStatusEnum;
import org.example.Utils.ScaleLayout;
import org.example.Utils.SystemData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGui implements ActionListener, CreatorGUI {

    ScaleLayout scallingController = new ScaleLayout();
    Authentication authenticationController = new Authentication();

   static JLabel appNameLB = new JLabel("Home-Inventory");
   static JLabel systemDataLB = new JLabel();
   static JButton loginModeBT = new JButton("Login");
   static JButton readModeBT = new JButton("Read");
   static JButton addItemModeBT = new JButton("Add item");
   static JButton infoModeBT = new JButton("Information");
   static JButton settingsModeBT = new JButton("Settings");
   static JButton logoutModeBT = new JButton("Logout");

    static JPanel menuPanel = new JPanel();

    public static JPanel initMenuGui() {
        MenuGui menuGui = new MenuGui();
        menuGui.setGuiParams();
        menuGui.addGuiComponents();
        menuGui.addGuiComponentsToListeners();
        menuGui.setGuiComponentsParams();
        return menuPanel;
    }

    @Override
    public void setGuiParams() {
        Point currentPanelSize = scallingController.getWindowSize(80, 100);
        Point modePanelSize = scallingController.getWindowSize(20, 100);
        menuPanel.setSize(modePanelSize.x, modePanelSize.y);
        menuPanel.setLayout(null);
        menuPanel.setLocation((int) currentPanelSize.getX(), 0);
        menuPanel.setBackground(Color.decode("#d90b0b"));
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
    }

    @Override
    public void addGuiComponents() {
        menuPanel.add(appNameLB);
        menuPanel.add(systemDataLB);
        menuPanel.add(loginModeBT);
        menuPanel.add(readModeBT);
        menuPanel.add(addItemModeBT);
        menuPanel.add(infoModeBT);
        menuPanel.add(settingsModeBT);
        menuPanel.add(logoutModeBT);
    }

    @Override
    public void addGuiComponentsToListeners() {
        loginModeBT.addActionListener(this);
        readModeBT.addActionListener(this);
        addItemModeBT.addActionListener(this);
        infoModeBT.addActionListener(this);
        settingsModeBT.addActionListener(this);
        logoutModeBT.addActionListener(this);
    }

    @Override
    public void setGuiComponentsParams() {
        scallingController.setScallingParams(90, 10, 40, 0, 5, appNameLB, menuPanel);
        scallingController.setScallingParams(90, 10, 20, 7, 5, systemDataLB, menuPanel);
        scallingController.setScallingParams(90, 7, 30, 20, 5, loginModeBT, menuPanel);
        scallingController.setScallingParams(90, 7, 30, 28, 5, readModeBT, menuPanel);
        scallingController.setScallingParams(90, 7, 30, 36, 5, addItemModeBT, menuPanel);
        scallingController.setScallingParams(90, 7, 30, 69, 5, infoModeBT, menuPanel);
        scallingController.setScallingParams(90, 7, 30, 77, 5, settingsModeBT, menuPanel);
        scallingController.setScallingParams(90, 7, 30, 85, 5, logoutModeBT, menuPanel);
        appNameLB.setForeground(Color.orange);
        appNameLB.setHorizontalAlignment(SwingConstants.CENTER);
        setButtonsDefaultColor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();
        if (component.isEnabled()) {
            CurrentGuiEnum newGui = null;
            setButtonsDefaultColor();
            component.setBackground(Color.GREEN);

            if(component == loginModeBT)
            {
                newGui = CurrentGuiEnum.LOGIN;
            }

            if(component == readModeBT)
            {
                newGui = CurrentGuiEnum.READ_ITEM;
            }

            if(component == addItemModeBT)
            {
                newGui = CurrentGuiEnum.ADD_ITEM;
            }

            if(component == infoModeBT)
            {
                newGui = CurrentGuiEnum.INFO;
            }

            if(component == settingsModeBT)
            {
                newGui = CurrentGuiEnum.SETTINGS;
            }

            if (component == logoutModeBT) {
                newGui = CurrentGuiEnum.LOGIN;
                authenticationController.setAuthenticationStatus(AuthenticationStatusEnum.LOGGED_OUT);
                setButtonsDefaultColor();
                loginModeBT.setBackground(Color.GREEN);
                JOptionPane.showConfirmDialog(ManagerGUI.getCurrentDisplayingPanel(), "Logged out successfully!", "Warning!", JOptionPane.DEFAULT_OPTION);
            }
            ManagerGUI.changeCurrentWindow(newGui);
        }
    }

    //username,date
    private void updateSystemData() {
        SystemData data = SystemData.getInstance();
        systemDataLB.setText("<html>username: " + data.getUser() + "<br/><br/>date: " + data.getDate() + "</html>");
    }

    //sets gray background-color
    public void setButtonsDefaultColor() {
        for (Component component : menuPanel.getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(Color.decode("#d1d1d1"));
                ((JButton) component).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }
        }
    }

    //enable right buttons
    private void changeModeButtonsEnable() {
        boolean status = !(authenticationController.getAuthenticationStatus() == AuthenticationStatusEnum.LOGGED_OUT);
        for (Component component : menuPanel.getComponents()) {
            if (component instanceof JButton) {
                if (component != settingsModeBT) {
                    component.setEnabled(status);
                }
            }
        }
        loginModeBT.setEnabled(!status);
    }

    public void updateMenuGui()
    {
        updateSystemData();
        changeModeButtonsEnable();
    }
}
