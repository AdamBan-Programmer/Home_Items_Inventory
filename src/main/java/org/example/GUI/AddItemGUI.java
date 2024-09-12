package org.example.GUI;

import org.example.Connection.DatabaseConnection;
import org.example.Entity.Item;
import org.example.Settings.AppSettings;
import org.example.Utils.ScaleLayout;
import org.example.Utils.SystemData;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddItemGUI implements ActionListener, CreatorGUI, PopupMenuListener {

    ScaleLayout scallingController = new ScaleLayout();

    static JPanel addItemPanel = new JPanel();
    JLabel panelTitleLB = new JLabel("Insert Item:");
    JLabel eanLB = new JLabel("Ean:");
    JTextField eanTF = new JTextField();
    JLabel nameLB = new JLabel("Name:");
    JTextField nameTF = new JTextField();
    JLabel locationLB = new JLabel("Location:");
    JComboBox locationCB = new JComboBox();
    JLabel qtyLB = new JLabel("Quantity:");
    JSpinner qtySR = new JSpinner(new SpinnerNumberModel(1,1,100,1));
    JLabel commentLB = new JLabel("Comment:");
    JTextArea commentTF = new JTextArea();
    JButton insertItemBT = new JButton("INSERT");


    public static JPanel initGUI()
    {
        AddItemGUI addItemGUI = new AddItemGUI();
        addItemGUI.setGuiParams();
        addItemGUI.addGuiComponents();
        addItemGUI.addGuiComponentsToListeners();
        addItemGUI.setGuiComponentsParams();
        return addItemPanel;
    }

    @Override
    public void setGuiParams() {
        Point screenSize = scallingController.getWindowSize(80, 100);
        addItemPanel.setSize(screenSize.x, screenSize.y);
        addItemPanel.setVisible(true);
        addItemPanel.setLayout(null);
        addItemPanel.setBackground(Color.decode("#C0C0C0"));
    }

    @Override
    public void addGuiComponents() {
        addItemPanel.add(panelTitleLB);
        addItemPanel.add(insertItemBT);
        addItemPanel.add(eanLB);
        addItemPanel.add(eanTF);
        addItemPanel.add(nameLB);
        addItemPanel.add(nameTF);
        addItemPanel.add(locationLB);
        addItemPanel.add(locationCB);
        addItemPanel.add(qtyLB);
        addItemPanel.add(qtySR);
        addItemPanel.add(commentLB);
        addItemPanel.add(commentTF);
    }

    @Override
    public void addGuiComponentsToListeners() {
        insertItemBT.addActionListener(this);
        locationCB.addPopupMenuListener(this);
    }

    @Override
    public void setGuiComponentsParams() {
        scallingController.setScallingParams(33, 10, 60, 5, 40, panelTitleLB, addItemPanel);

        scallingController.setScallingParams(15, 10, 30, 20, 20, eanLB, addItemPanel);
        scallingController.setScallingParams(10, 5, 50, 22.5f, 35, eanTF, addItemPanel);
        scallingController.setScallingParams(15, 10, 30, 27.5f, 20, nameLB, addItemPanel);
        scallingController.setScallingParams(20, 5, 50, 30, 35, nameTF, addItemPanel);
        scallingController.setScallingParams(15, 10, 30, 35, 20, locationLB, addItemPanel);
        scallingController.setScallingParams(20, 5, 30, 37.5f, 35, locationCB, addItemPanel);
        scallingController.setScallingParams(15, 10, 30, 42.5f, 20, qtyLB, addItemPanel);
        scallingController.setScallingParams(10, 5, 50, 45, 35, qtySR, addItemPanel);
        scallingController.setScallingParams(15, 10, 30, 50, 20, commentLB, addItemPanel);
        scallingController.setScallingParams(40, 10, 20, 52.5f, 35, commentTF, addItemPanel);

        scallingController.setScallingParams(33, 7, 30, 80, 33,insertItemBT, addItemPanel);
        insertItemBT.setBackground(Color.decode("#d4d4d4"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();

        if (component == insertItemBT) {
            try
            {
                Item item = getParamsFromControls();
                DatabaseConnection.insertItem(item);
                JOptionPane.showConfirmDialog(addItemPanel, "item inserted.", "Warning!", JOptionPane.DEFAULT_OPTION);
                clearControls();
            }
            catch (Exception ex)
            {
                JOptionPane.showConfirmDialog(addItemPanel, "Couldn't insert an item!", "Warning!", JOptionPane.DEFAULT_OPTION);
            }
        }
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        reloadLocations();
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {

    }


    private Item getParamsFromControls() throws NumberFormatException
    {
        String ean = eanTF.getText();
        String name = nameTF.getText();
        String location = locationCB.getSelectedItem().toString();
        int qty = (int) qtySR.getValue();
        String comment = commentTF.getText();
        String username = SystemData.getInstance().getUser();
        String date = SystemData.getInstance().getDate();
        return new Item(0,ean,name,location,qty,comment,date,"PC_APP",username);
    }

    //clear all fields
    private void clearControls()
    {
        for(Component component : addItemPanel.getComponents())
        {
            if(component instanceof JTextField)
            {
                ((JTextField) component).setText("");
            }
            if(component instanceof JTextArea)
            {
                ((JTextArea) component).setText("");
            }
            qtySR.setValue(1);
        }
    }

    //reloads locations set in SETTINGS
    private void reloadLocations()
    {
        locationCB.removeAllItems();
        ArrayList<String> locations = AppSettings.getInstance().getLocations();
        for (String location : locations) {
            locationCB.addItem(location);
        }
    }
}
