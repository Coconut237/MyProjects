package com.example.controller;

import com.example.model.*;
import com.example.tools.*;
import com.example.view.*;

import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.table.DefaultTableModel;

/**
 * Dies ist die Dokumentation der Klasse Controller. Der Controller ist die Schnittstelle zwischen MainView (GUI) und Model (Fachklassen).
 * Er steuert die ausgeführten Aktionen, die vom Nutzer über den MainView getätigt werden können.
 * Er gibt Benutzereingaben zur weiteren Bearbeitung an das Model oder MainView weiter.
 * @author  Cordola O'Brien
 * @version 1.0
 * @see     MainView
 */
public class Controller {
    private SmartHome smartHome;
    private MainView mainView;
    private String currentNotification;
    private NotificationObserver highValueNotificationObserver;
    private NotificationObserver lowValueNotificationObserver;

    /**
     * Konstruktor fuer die Klasse Controller
     * Beim Erstellen eines Controllers, wird automatisch ein SmartHome mit den jeweiligen Sensor-Typen beigefügt.
     * Zur Veranschaulichung werden dem SmartHome Default-Sensoren beigefügt.
     * Außerdem werden direkt die zwei erforderlichen Observer beigefügt.
     */
    public Controller() {
            smartHome = new SmartHome();
            this.addSensorTypes();
            this.addDefaultSensors();
            addHighValueNotificationObserver();
            addLowValueNotificationObserver();
            checkifNotification();
    }

    /**
     *Hinzufügen der Sensor-Typen
     */
    private void addSensorTypes(){
    smartHome.addSensorType("Helligkeits-Sensor");
    smartHome.addSensorType("CO2-Gehalt-Sensor");
    smartHome.addSensorType("Temperatur-Sensor");
    }

    /**
     *Hinzufügen der Sensor-Typen
     */
    private void addDefaultSensors(){
            BrightnessSensor sensorBrightnessKitchen = new BrightnessSensor("Küche");
            smartHome.addSensor(sensorBrightnessKitchen);

            BrightnessSensor sensorBrightnessWohnzimmer = new BrightnessSensor("Wohnzimmer");
            smartHome.addSensor(sensorBrightnessWohnzimmer);

            CO2Sensor sensorCo2BedRoom = new CO2Sensor("Schlafzimmer");
            smartHome.addSensor(sensorCo2BedRoom);

            TemperatureSensor sensorTemperatureLivingRoom = new TemperatureSensor("Wohnzimmer");
            smartHome.addSensor(sensorTemperatureLivingRoom);
    }

    /**
     *Aufrufen des Main Views
     *Hinzufügen von Funktionen: "Sensor hinzufügen" | "Sensor-Messwert aktualisieren" | "Sensor-Standort ändern" | "Sensor löschen"
     */
    public void showMainView() {
        mainView = new MainView(smartHome);
        mainView.showView();

        addAction();
        updateAction();
        editAction();
        deleteAction();
    }  

    /**
     *Funktion: Hinzufügen eines Sensors
     */
    private void addAction(){
        mainView.addSensorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddSensorView();
            }
        });
    }

    /**
     *Funktion: Aktualisieren des Sensor-Messwertes
     */
    private void updateAction(){
        Action updateAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                int modelRow = Integer.valueOf( e.getActionCommand() );
                smartHome.measureCurrentValueOfSensor(modelRow);
                updateTable();
            }
        };
        ButtonColumn buttonColumnUpdate = new ButtonColumn(mainView.table, updateAction, 4);
        buttonColumnUpdate.setMnemonic(KeyEvent.VK_D);
    }

    /**
     *Funktion: Ändern des Sensor-Standortes
     */
    private void editAction(){
        Action editAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
            JTable table = (JTable)e.getSource();
            int modelRow = Integer.valueOf( e.getActionCommand() ); 
            String location = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 2));
            smartHome.editSensor(modelRow, location);
            updateTable();
            }
        };
        ButtonColumn buttonColumnEdit = new ButtonColumn(mainView.table, editAction, 5);
        buttonColumnEdit.setMnemonic(KeyEvent.VK_D);
    }

    /**
     *Funktion: Löschen eines Sensors
     */
    private void deleteAction(){
        Action deleteAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
                smartHome.removeSensor(modelRow);
            }
        };
        ButtonColumn buttonColumnDelete = new ButtonColumn(mainView.table, deleteAction, 6);
        buttonColumnDelete.setMnemonic(KeyEvent.VK_D);
    }

    /**
     *Aufrufen des MainViews zum Hinzufügen von Sensoren
     *Hinzufügen der Funktion: "Sensor-Daten speichern"
     */
    public void showAddSensorView(){
        final AddSensorView addSensorView = new AddSensorView(smartHome);
        addSensorView.showView();
        saveAction(addSensorView);
    }

    /**
     *Funktion: Speichern der Daten des neu hinzugefügten Sensors
     *@param addSensorView Der View, um einen Sensor hinzuzufügen
     */
    private void saveAction(final AddSensorView addSensorView){
        addSensorView.saveSensorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String locationOfAddedSensor = addSensorView.getSensorLocation();
                String typeOfAddedSensor = addSensorView.getSensorType();
                
                if(locationOfAddedSensor!= null && !locationOfAddedSensor.isEmpty()){
                if(typeOfAddedSensor =="Helligkeits-Sensor"){
                    smartHome.addSensor(new BrightnessSensor(locationOfAddedSensor));
                }else if(typeOfAddedSensor =="CO2-Gehalt-Sensor"){
                    smartHome.addSensor(new CO2Sensor(locationOfAddedSensor));
                }else if(typeOfAddedSensor =="Temperatur-Sensor"){
                    smartHome.addSensor(new TemperatureSensor(locationOfAddedSensor));
                }else{
                }
                addSensorView.frame.dispatchEvent(new WindowEvent(addSensorView.frame, WindowEvent.WINDOW_CLOSING));
                mainView.frame.dispatchEvent(new WindowEvent(mainView.frame, WindowEvent.WINDOW_CLOSING));
                showMainView(); 
                }else{
                    String addLocationNotification = "Achtung: Keine Angabe über Standort!\n\nBitte gebe einen Standort für den Sensor an.";
                    NotificationView addLocationNotificationView = new NotificationView(addLocationNotification);
                    addLocationNotificationView.showView();
                }
            }
        });
    }
    
    /** 
     * Aktualisieren der Sensor-Tabelle
     */
    private void updateTable(){
        mainView.setTable(smartHome);
        updateAction(); editAction(); deleteAction();
    }

    /** 
     * Laufende Kontrolle über neue Nachrichten der Beobachter
     */
    private void checkifNotification(){

        Runnable continuousCheckForNewNotifications = new Runnable() {
            @Override
            public void run() {
                String notification = smartHome.getNotification();

                //Wenn es eine neue Nachricht gibt (die von der letzten abweicht) => neue Meldung
                if(currentNotification != notification && notification!=null){
                    showNotificationView(notification);
                }
                currentNotification = notification;
            }
        };
    
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        int initialDelay = 0;
        int delay = 250;
        scheduler.scheduleWithFixedDelay(continuousCheckForNewNotifications, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     *Aufrufen des NotificationViews
     @param notification Benachrichtigung der Meldung
     */
    public void showNotificationView(String notification){
        NotificationView notificationView = new NotificationView(notification);
        notificationView.showView();
    }

    /** 
     * Hinzufügen des HighValueNotification-Observers zum SmartHome
     */
    private void addHighValueNotificationObserver(){
        highValueNotificationObserver = new HighValueNotificationObserver();
        smartHome.registerNotificationObserver(highValueNotificationObserver);
    }

    /** 
     * Hinzufügen des LowValueNotification-Observers zum SmartHome
     */
    private void addLowValueNotificationObserver(){
        lowValueNotificationObserver = new LowValueNotificationObserver();
        smartHome.registerNotificationObserver(lowValueNotificationObserver);
    }
}
