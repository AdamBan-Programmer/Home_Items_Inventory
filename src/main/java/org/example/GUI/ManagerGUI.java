package org.example.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ManagerGUI {

    static JFrame mainFrame = new JFrame();

    static private JPanel currentDisplayingPanel = new JPanel();

    private static HashMap<CurrentGuiEnum,JPanel> panelsArray = new HashMap<>();

    public ManagerGUI(){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
        mainFrame.setResizable(true);
        mainFrame.setTitle("Home Inventory");
        mainFrame.setLayout(null);
        mainFrame.getContentPane().setBackground(Color.decode("#C0C0C0"));
        setWindowIcon();

        mainFrame.add(currentDisplayingPanel);
        mainFrame.add(MenuGui.initMenuGui());
        mainFrame.repaint();

        loadPanelsMap();
        changeCurrentWindow(CurrentGuiEnum.LOGIN);
    }

    //Jpanels are in array to switch them easy
    public void loadPanelsMap() {
        panelsArray.put(CurrentGuiEnum.LOGIN,LoginGui.initGUI());
        panelsArray.put(CurrentGuiEnum.READ_ITEM,ReadItemGui.initGUI());
        panelsArray.put(CurrentGuiEnum.ADD_ITEM, AddItemGUI.initGUI());
        panelsArray.put(CurrentGuiEnum.INFO, InfoGui.initGUI());
        panelsArray.put(CurrentGuiEnum.SETTINGS, AppSettingsGUI.initGUI());
    }

    //switch Jpanel (gui)
    public static void changeCurrentWindow(CurrentGuiEnum guiEnum) {

       JPanel newGui = panelsArray.get(guiEnum);

        if (currentDisplayingPanel != newGui) {
            mainFrame.remove(currentDisplayingPanel);
            currentDisplayingPanel = newGui;
            mainFrame.getContentPane().add(currentDisplayingPanel);
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
            mainFrame.setIconImage(icon.getImage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JPanel getCurrentDisplayingPanel() {
        return currentDisplayingPanel;
    }
}
