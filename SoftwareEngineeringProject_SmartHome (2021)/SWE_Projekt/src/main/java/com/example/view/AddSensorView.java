package com.example.view;

import com.example.model.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

/**
 * Dies ist die Dokumentation der Klasse AddSensorView. Dieser View bildet die GUI bzw. ein Fenster, das es ermöglicht, einen Sensor zum SmartHome hinzuzufügen.
 * Hier kann der User die Daten (Sensor-Typ und Standort) für einen neuen Sensor eingeben und diesen speichern.
 * Die Benutzereingaben werden zur weiteren Bearbeitung an den Controller übergeben.
 * @author  Cordola O'Brien
 * @version 1.0
 */
public class AddSensorView {

     public JFrame frame;
     public JButton saveSensorButton;
     private SmartHome smartHome;
     private JTextField sensorLocationInput;
     private JComboBox sensorTypeOption;

    /**
     * Konstruktor fuer die Klasse AddSensorView
     * @param smartHome Das SmartHome
     */
    public AddSensorView(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    /**
     * Erstellen und Anzeigen des Views
     * Es wird ein neuer JFrame erstellt und folgendes gesetzt: Titel, Hintergrundfarbe, Label & Selektion für den Sensor-Typ-Eintrag, Label & Textfeld für den Standort-Eintrag, Save-Button und der Frame.
     */
    public void showView(){
        frame=new JFrame("Sensor hinzufügen");
        setTitle();
        setBackgroundColor();
        setLabelSensorTyp();
        setSelectionSensorTypes(smartHome);
        setLabelSensorLocation();
        setTextFieldSensorLocation();
        setSaveButton(smartHome);
        setFrame();
    }

    /**
     * Setzen des Titels
     */
    private void setTitle(){
        JLabel titleLabel = new JLabel("Sensor hinzufügen");  
        titleLabel.setBounds(5,5, 400,50);
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
     * Setzen des Labels für den Sensor-Typ
     */
    private void setLabelSensorTyp(){
    JLabel labelSensorTyp = new JLabel("Sensor-Typ: ");
    labelSensorTyp.setBounds(15,45, 100,50);
    labelSensorTyp.setFont(new Font("Serif", Font.BOLD, 16));
    frame.add(labelSensorTyp);
    }

    /** 
     * Setzen der Selektions-Möglichkeit des Sensor-Typs mittels JComboBox
     * Setzen eines ActionListeners zum Speichern des SensorTyps
     * @param smartHome Das SmartHome
     */
    private void setSelectionSensorTypes(SmartHome smartHome){
        String[] sensorTypesArray = new String[smartHome.sensorTypes.size()];

        for(int i = 0; i < smartHome.sensorTypes.size(); i++){
            sensorTypesArray[i] = smartHome.getSensorType(i);
        }

        sensorTypeOption = new JComboBox(sensorTypesArray);

        sensorTypeOption.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            }
         });

         sensorTypeOption.setBounds(120,57, 150,30);
        frame.add(sensorTypeOption);
    }

    /**
     * Setzen des Labels für den Standort des Sensors
     */
    private void setLabelSensorLocation(){
        JLabel labelSensorLocation = new JLabel("Standort: ");
        labelSensorLocation.setBounds(15,85, 150,50);
        labelSensorLocation.setFont(new Font("Serif", Font.BOLD, 16));
        frame.add(labelSensorLocation);
    }

    /**
     * Setzen des Textfeldes für den Standort-Eintrag
     * Setzen eines ActionListeners zum Speichern des Sensor-Standortes
     */
    private void setTextFieldSensorLocation(){
        sensorLocationInput = new JTextField("");
        sensorLocationInput.setBounds(120,100, 150,30);
        sensorLocationInput.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            }
         });
        frame.add(sensorLocationInput);
    }

    
    /** 
     * Setzen des Speicher-Button, um den neuen Sensor hinzuzufügen und zu speichern
     * @param smartHome Das SmartHome
     */
    public void setSaveButton(SmartHome smartHome){
        saveSensorButton = new JButton("Save");
        saveSensorButton.setFont(new Font("Serif", Font.BOLD, 15));
        saveSensorButton.setBounds(120, 145,70, 30);

        frame.add(saveSensorButton);
    }

    /**
     * Setzen und Anzeigen des Frames
     */
    private void setFrame(){
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    /** 
     * Ausgabe des neu hinzugefügten Sensor-Typs
     * @return String Sensor-Typ
     */
    public String getSensorType() {
        return String.valueOf(sensorTypeOption.getSelectedItem());
    }
    
    /** 
     * Ausgabe des neu hinzugefügten Sensor-Standortes
     * @return String Sensor-Standort
     */
    public String getSensorLocation() {
        return sensorLocationInput.getText();
    }
}