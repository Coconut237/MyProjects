package com.example.view;

import com.example.model.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Color;
import java.awt.Font;

/**
 * Dies ist die Dokumentation der Klasse MainView. Der MainView bildet die Main-GUI und zeigt die Sensoren des SmartHomes an.
 * Hier kann der User bestimmte Aktionen tätigen: "Sensor hinzufügen" | "Sensor-Messwert aktualisieren" | "Sensor-Standort ändern" | "Sensor löschen"
 * Die Benutzereingaben werden zur weiteren Bearbeitung an den Controller übergeben.
 * @author  Cordola O'Brien
 * @version 1.0
 * @see     Controller
 */
public class MainView {

    private SmartHome smartHome;
    private String[] columnNames;
    private Object[][] data;
    public JFrame frame;
    public JButton addSensorButton;
    public JTable table;
    
    /**
     * Konstruktor fuer die Klasse MainView
     * @param smartHome Das SmartHome
     */
    public MainView(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    /**
     * Erstellen und Anzeigen des Main Views
     * Es wird ein neuer JFrame erstellt und folgendes gesetzt: Hintergrundfarbe, Titel, Sensor-Tabelle, Add-Button und der Frame.
     * @param smartHome Das SmartHome
     */
    public void showView(){
        frame=new JFrame("SmartHome");
        setBackgroundColor();
        setTitle();
        setTable(smartHome);        
        setAddButton();
        setFrame();
    }

    /**
     * Setzen des Titels
     */
    private void setTitle(){
        JLabel titleLabel = new JLabel("  Sensoren");  
        titleLabel.setBounds(70,5, 100,30);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        frame.add(titleLabel);
    }

    /**
     * Setzen der Hintergrundfarbe
     */
    private void setBackgroundColor(){
        Color definedColor = new Color(190,190, 200);
        frame.getContentPane().setBackground(definedColor);
    }

    
    /** 
     * Setzen der Tabelle
     * @param smartHome Das SmartHome
     */
    public void setTable(SmartHome smartHome){
        columnNames = new String[] {"Id", "Sensor-Typ", "Standort", "Messwert", "Update", "Edit", "Delete"};
         
        setDataForTableView(smartHome);

        //Folgende Spalten sollen vom User bearbeitbar sein: Sensor-Typ, Standort und die Aktionen Update, Edit und Delete.
        DefaultTableModel modelForTable = new DefaultTableModel(data, columnNames){
                @Override
                public boolean isCellEditable(int row, int column) {
                   return column==2 || column==4 || column ==5 || column == 6;
                }
        };
        table = new JTable(modelForTable);

        table.setFont(new Font(null, Font.PLAIN, 11));
        setColumnWidths(table, 18, 120, 110, 85, 90, 90, 90);
        table.setRowHeight(30);

        JScrollPane scrollpaneForTable = new JScrollPane(table);

        scrollpaneForTable.setBounds(50,50, 550, 400);
        frame.add(scrollpaneForTable);
    }

    /** 
     * Befüllen der Sensor-Tabelle mit den entsprechenden Daten
     * @param smartHome Das SmartHome
     */
    private void setDataForTableView(SmartHome smartHome){
        data = new Object[smartHome.sensorList.size()][columnNames.length];
        for(int i = 0; i < data.length; i++){
                data[i][0] = smartHome.getSensor(i).getId();
                data[i][1] = smartHome.getSensor(i).getType();
                data[i][2] = smartHome.getSensor(i).getLocation();
                data[i][3] = smartHome.getSensor(i).getCurrentValue()+" "+smartHome.getSensor(i).getUnit();
                data[i][4] = "update";
                data[i][5] = "save";
                data[i][6] = "delete";
        }
    }
    
    /** 
     * Setzen der Spalten-Breite der Tabelle
     * @param table Die Sensor-Tabelle
     * @param widths Die Breite der einzelnen Spalten
     */
    private void setColumnWidths(JTable table, int... widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            }
            else break;
        }
    }

    /** 
     * Setzen des Add-Buttons
     */
    private void setAddButton(){
        addSensorButton = new JButton("+");
        addSensorButton.setFont(new Font("Serif", Font.BOLD, 15));
        addSensorButton.setBounds(200, 9,43, 30);
        frame.add(addSensorButton);
    }

    /** 
     * Setzen und Anzeigen des Frames
     */
    private void setFrame(){
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}