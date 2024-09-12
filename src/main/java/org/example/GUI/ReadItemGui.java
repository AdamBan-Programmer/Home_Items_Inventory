package org.example.GUI;

import org.example.Connection.DatabaseConnection;
import org.example.Entity.Item;
import org.example.Settings.AppSettings;
import org.example.Utils.ScaleLayout;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReadItemGui implements ActionListener, CreatorGUI, PopupMenuListener {

    ScaleLayout scallingController = new ScaleLayout();

    static JPanel readPanel = new JPanel();
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable itemTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(new JScrollPane(itemTable));
    JLabel locationLB = new JLabel("Locations:");
    JComboBox locationCB = new JComboBox();
    JLabel panelTitleLB = new JLabel("Read Item:");
    JLabel itemNameLB = new JLabel("Name / Phrase");
    JTextField itemNameTF = new JTextField();
    JLabel resultsCountLB = new JLabel("Results: ");
    JButton searchBT = new JButton("Search");
    JButton removeBT = new JButton("Remove");

    public static JPanel initGUI()
    {
        ReadItemGui readItemGui = new ReadItemGui();
        readItemGui.setGuiParams();
        readItemGui.addGuiComponents();
        readItemGui.addGuiComponentsToListeners();
        readItemGui.setGuiComponentsParams();
        return readPanel;
    }

    @Override
    public void setGuiParams() {
        Point screenSize = scallingController.getWindowSize(80, 100);
        readPanel.setSize(screenSize.x, screenSize.y);
        readPanel.setVisible(true);
        readPanel.setLayout(null);
        readPanel.setBackground(Color.decode("#C0C0C0"));
    }

    @Override
    public void addGuiComponents() {
        readPanel.add(scrollPane);
        readPanel.add(panelTitleLB);
        readPanel.add(locationLB);
        readPanel.add(locationCB);
        readPanel.add(itemNameLB);
        readPanel.add(itemNameTF);
        readPanel.add(resultsCountLB);
        readPanel.add(searchBT);
        readPanel.add(removeBT);
    }

    @Override
    public void addGuiComponentsToListeners() {
        locationCB.addPopupMenuListener(this);
        searchBT.addActionListener(this);
        removeBT.addActionListener(this);
    }

    @Override
    public void setGuiComponentsParams() {
        scallingController.setScallingParams(33, 10, 60, 5, 40, panelTitleLB, readPanel);
        scallingController.setScallingParams(13, 3, 50, 15.5f, 10, locationLB, readPanel);
        scallingController.setScallingParams(13, 5, 30, 18, 10, locationCB, readPanel);
        scallingController.setScallingParams(56, 3, 50, 15.5f, 24, itemNameLB, readPanel);
        scallingController.setScallingParams(56, 5, 30, 18, 24, itemNameTF, readPanel);
        scallingController.setScallingParams(15, 3, 60, 15, 75, resultsCountLB, readPanel);
        scallingController.setScallingParams(9, 5, 30, 18, 81, searchBT, readPanel);
        scallingController.setScallingParams(80, 60, 0, 25, 10, scrollPane, readPanel);
        scallingController.setScallingParams(20, 5, 30, 87, 70, removeBT, readPanel);
        searchBT.setBackground(Color.decode("#d1d1d1"));
        removeBT.setBackground(Color.decode("#f74053"));
        locationCB.addItem("ALL");
        tableModel.addColumn("Id");
        tableModel.addColumn("Ean");
        tableModel.addColumn("Location");
        tableModel.addColumn("Name");
        tableModel.addColumn("Comment");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Date");
        tableModel.addColumn("AppId");
        tableModel.addColumn("User");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();

        if (component == searchBT) {
            String location = locationCB.getSelectedItem().toString();
            String name = itemNameTF.getText();
            List<Item> items = DatabaseConnection.getItemsList(location, name);
            updateTableContent(items);
            resultsCountLB.setText("Results: " + items.size() + " records.");
        }

        if (component == removeBT) {
            Item[] itemsToRemove = getItemsToRemove();
            if(removeConfirmed(itemsToRemove)) {
                removeItems(itemsToRemove);
            }
            resultsCountLB.setText("Results: " + tableModel.getRowCount() + " records.");
        }
    }

    //remove dialog action
    private boolean removeConfirmed(Item[] itemsToRemove) {
        String text = "Do you want to remove?\n\n";
        for(Item item : itemsToRemove)
        {
            text += item.getName() + " " + item.getComment() +" from " + item.getLocation() + " (Qty:" + item.getQty() + ")\n";
        }
        String[] options = { "OK", "Cancel"};
        int resultCode = JOptionPane.showOptionDialog(readPanel, text, "Warning!", JOptionPane.DEFAULT_OPTION,3,null,options,options[0]);
        return (resultCode == 0);
    }

    private void removeItems(Item[] itemsToRemove)
    {
        int[] selectedRows = itemTable.getSelectedRows();
        for(int i =0;i<itemsToRemove.length;i++)
        {
            tableModel.removeRow(selectedRows[i]-i);
            DatabaseConnection.removeItem(itemsToRemove[i]);
        }
    }

    private void updateTableContent(List<Item> items)
    {
        tableModel.setRowCount(0);
        for(Item item : items) {
            Object[] newRow = {item.getId(),item.getEan(),item.getLocation(),item.getName(),item.getComment(),item.getQty(),item.getDate(),item.getAppId(),item.getUser()};
            tableModel.addRow(newRow);
        }
        itemTable.repaint();
    }

    private Item[] getItemsToRemove()
    {
        int[] selectedRows = itemTable.getSelectedRows();
        Item[] itemsToRemove = new Item[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            itemsToRemove[i] = getSelectedItem(rowIndex);
        }
        return itemsToRemove;
    }

    private Item getSelectedItem(int rowIndex)
    {
        int id = (int) itemTable.getValueAt(rowIndex, 0);
        String ean = (String) itemTable.getValueAt(rowIndex, 1);
        String location = (String) itemTable.getValueAt(rowIndex, 2);
        String name = (String) itemTable.getValueAt(rowIndex, 3);
        String comment = (String) itemTable.getValueAt(rowIndex, 4);
        int qty = (int) itemTable.getValueAt(rowIndex, 5);
        String date = (String) itemTable.getValueAt(rowIndex, 6);
        String appId = (String) itemTable.getValueAt(rowIndex, 7);
        String user = (String) itemTable.getValueAt(rowIndex, 8);
        return new Item(id,ean,name,location,qty,comment,date,appId,user);
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

    private void reloadLocations()
    {
        locationCB.removeAllItems();
        locationCB.addItem("ALL");
        ArrayList<String> locations = AppSettings.getInstance().getLocations();
        for (String location : locations) {
            locationCB.addItem(location);
        }
    }
}