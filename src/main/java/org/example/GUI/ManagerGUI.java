package org.example.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ManagerGUI {

    static JFrame mainWindowFrame = new JFrame();
    static private JPanel currentDisplayingPanel = new JPanel();

    private static HashMap<CurrentGuiEnum,JPanel> panelsArray = new HashMap<>();

    public void createFrame()
    {
        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindowFrame.setVisible(true);
        mainWindowFrame.setResizable(true);
        mainWindowFrame.setTitle("Home Inventory");
        mainWindowFrame.setLayout(null);
        mainWindowFrame.getContentPane().setBackground(Color.decode("#C0C0C0"));
        setWindowIcon();

        mainWindowFrame.add(currentDisplayingPanel);
        mainWindowFrame.add(MenuGui.initMenuGui());
        mainWindowFrame.repaint();
    }

    //Jpanels are in array to switch them easy
    public void loadPanelsMap() {
        panelsArray.put(CurrentGuiEnum.LOGIN,LoginGui.initGUI());
        panelsArray.put(CurrentGuiEnum.READ_ITEM,ReadItemGui.initGUI());
        panelsArray.put(CurrentGuiEnum.ADD_ITEM, AddItemGUI.initGUI());
        panelsArray.put(CurrentGuiEnum.INFO, InfoGui.initGUI());
        panelsArray.put(CurrentGuiEnum.SETTGINS, AppSettingsGUI.initGUI());
    }

    //switch Jpanel (gui)
    public void changeCurrentWindow(CurrentGuiEnum guiEnum) {

       JPanel newGui = panelsArray.get(guiEnum);

        if (currentDisplayingPanel != newGui) {
            mainWindowFrame.remove(currentDisplayingPanel);
            currentDisplayingPanel = newGui;
            mainWindowFrame.getContentPane().add(currentDisplayingPanel);
            currentDisplayingPanel.repaint();
            currentDisplayingPanel.revalidate();
        }
        MenuGui menuGuiController = new MenuGui();
        menuGuiController.updateMenuGui();
    }

    //sets icon of application
    private void setWindowIcon() {
        try {
            ImageIcon icon = new ImageIcon("C:/Users/Adam/IdeaProjects/Home_Items_Inventory/src/main/resources/Icon/HomeInventoryIcon.png");
            mainWindowFrame.setIconImage(icon.getImage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JFrame getMainWindowFrame() {
        return mainWindowFrame;
    }

    public JPanel getCurrentDisplayingPanel() {
        return currentDisplayingPanel;
    }
}
