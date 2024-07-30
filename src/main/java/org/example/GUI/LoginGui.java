package org.example.GUI;

import org.example.Authentication.Authentication;
import org.example.Authentication.AuthenticationStatusEnum;
import org.example.Authentication.MD5Encryption;
import org.example.Entity.User;
import org.example.Utils.ScaleLayout;
import org.example.Utils.SystemData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class LoginGui implements ActionListener, InterfaceGUI {

    ScaleLayout scallingController = new ScaleLayout();
    Authentication authenticationController = new Authentication();
    InfoGui infoGuiController = new InfoGui();
    SystemData systemDataController = new SystemData();
    ManagerGUI guiController = new ManagerGUI();
    MD5Encryption encryptionController = new MD5Encryption();

    static JPanel authenticationPanel = new JPanel();
    JLabel panelTitleLB = new JLabel("Authentication:");
    JLabel usernameLB = new JLabel("Username:");
    JTextField usernameTF = new JTextField();
    JLabel passwordLB = new JLabel("Password:");
    JPasswordField passwordTF = new JPasswordField();
    JButton loginBT = new JButton("Login");

    public static JPanel initGUI()
    {
        LoginGui loginGui = new LoginGui();
        loginGui.setGUIParams();
        loginGui.addGUIComponents();
        loginGui.addGUIComponentsToListeners();
        loginGui.setGUIComponentsParams();
        return authenticationPanel;
    }

    @Override
    public void setGUIParams() {
        Point screenSize = scallingController.getWindowSize(80, 100);
        authenticationPanel.setSize(screenSize.x, screenSize.y);
        authenticationPanel.setVisible(true);
        authenticationPanel.setLayout(null);
        authenticationPanel.setBackground(Color.decode("#C0C0C0"));
    }

    @Override
    public void addGUIComponents() {
        authenticationPanel.add(panelTitleLB);
        authenticationPanel.add(usernameLB);
        authenticationPanel.add(usernameTF);
        authenticationPanel.add(passwordLB);
        authenticationPanel.add(passwordTF);
        authenticationPanel.add(loginBT);
    }

    @Override
    public void addGUIComponentsToListeners() {
        loginBT.addActionListener(this);
    }

    @Override
    public void setGUIComponentsParams() {
        ScaleLayout scallingInViewElements = new ScaleLayout(authenticationPanel.getWidth(), authenticationPanel.getHeight());
        scallingInViewElements.setScallingParams(63, 10, 60, 5, 37, panelTitleLB, authenticationPanel);
        scallingInViewElements.setScallingParams(30, 5, 40, 30, 30, usernameLB, authenticationPanel);
        scallingInViewElements.setScallingParams(30, 5, 40, 35, 30, usernameTF, authenticationPanel);
        scallingInViewElements.setScallingParams(30, 5, 40, 45, 30, passwordLB, authenticationPanel);
        scallingInViewElements.setScallingParams(30, 5, 40, 50, 30, passwordTF, authenticationPanel);
        scallingInViewElements.setScallingParams(15, 5, 40, 65, 30, loginBT, authenticationPanel);
        loginBT.setBackground(Color.decode("#d4d4d4"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();
        try {
            if (component == loginBT) {
                Authentication auth = getDataFromControls();
                User currentUser = authenticationController.userValidated(auth);
                if (currentUser != null) {
                    InfoGui.initGUI();
                    String loginDate = systemDataController.getCurrentSystemDate();
                    systemDataController.setCurrentSystemData(new SystemData(currentUser.getName(), loginDate));
                    authenticationController.setAuthenticationStatus(AuthenticationStatusEnum.LOGGED_IN);
                    guiController.changeCurrentWindow(CurrentGuiEnum.INFO);
                }
                else {
                    JOptionPane.showConfirmDialog(authenticationPanel, "Entered username or password Incorrect!", "Warning!", JOptionPane.DEFAULT_OPTION);
                }
                clearControls();
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showConfirmDialog(authenticationPanel, "Error when logging!", "Warning!", JOptionPane.DEFAULT_OPTION);
        }
    }

    private Authentication getDataFromControls() throws NoSuchAlgorithmException {
        String username = usernameTF.getText();
        String password =  passwordTF.getText();
        return new Authentication(username,encryptionController.encryptedPassword(password));
    }

    private void clearControls()
    {
        usernameTF.setText("");
        passwordTF.setText("");
    }
}
