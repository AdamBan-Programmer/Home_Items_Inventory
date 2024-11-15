package org.example.GUI;

import org.example.Authentication.Authentication;
import org.example.Authentication.AuthenticationStatusEnum;
import org.example.Authentication.MD5Encryption;
import org.example.Entity.User;
import org.example.Service.SystemDataService;
import org.example.Service.UserService;
import org.example.Utils.ScaleLayout;
import org.example.Utils.SystemData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class LoginGui implements ActionListener, CreatorGUI {

    ScaleLayout scallingController = new ScaleLayout();
    UserService authService = new UserService();
    MD5Encryption encryptionController = new MD5Encryption();
    SystemDataService systemDataService = new SystemDataService();

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
        loginGui.setGuiParams();
        loginGui.addGuiComponents();
        loginGui.addGuiComponentsToListeners();
        loginGui.setGuiComponentsParams();
        return authenticationPanel;
    }

    @Override
    public void setGuiParams() {
        Point screenSize = scallingController.getWindowSize(80, 100);
        authenticationPanel.setSize(screenSize.x, screenSize.y);
        authenticationPanel.setVisible(true);
        authenticationPanel.setLayout(null);
        authenticationPanel.setBackground(Color.decode("#C0C0C0"));
    }

    @Override
    public void addGuiComponents() {
        authenticationPanel.add(panelTitleLB);
        authenticationPanel.add(usernameLB);
        authenticationPanel.add(usernameTF);
        authenticationPanel.add(passwordLB);
        authenticationPanel.add(passwordTF);
        authenticationPanel.add(loginBT);
    }

    @Override
    public void addGuiComponentsToListeners() {
        loginBT.addActionListener(this);
    }

    @Override
    public void setGuiComponentsParams() {
        scallingController.setScallingParams(63, 10, 60, 5, 37, panelTitleLB, authenticationPanel);
        scallingController.setScallingParams(30, 5, 40, 30, 30, usernameLB, authenticationPanel);
        scallingController.setScallingParams(30, 5, 40, 35, 30, usernameTF, authenticationPanel);
        scallingController.setScallingParams(30, 5, 40, 45, 30, passwordLB, authenticationPanel);
        scallingController.setScallingParams(30, 5, 40, 50, 30, passwordTF, authenticationPanel);
        scallingController.setScallingParams(15, 5, 40, 65, 30, loginBT, authenticationPanel);
        loginBT.setBackground(Color.decode("#d4d4d4"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();
        try {
            if (component == loginBT) {
                Authentication auth = getDataFromControls();
                User currentUser = authService.validUser(auth);
                if (currentUser != null) {
                    systemDataService.updateSystemData(currentUser);
                    SystemData.getInstance().setStatus(AuthenticationStatusEnum.LOGGED_IN);
                    ManagerGUI.changeCurrentWindow(CurrentGuiEnum.INFO);
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
