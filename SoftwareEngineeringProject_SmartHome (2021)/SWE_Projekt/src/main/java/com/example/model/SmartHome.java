package com.example.model;

import java.util.*;

/**
 * Dies ist die Dokumentation der Klasse SmartHome. Ein SmartHome dient zur Verwaltung von  
 * verschiedenen Sensoren mit einem bestimmtem SensorTyp.
 * @author  Cordola O'Brien
 * @version 1.0
 * @see     Sensor
 */
public class SmartHome {
    public List<Sensor> sensorList;
    public List<String> sensorTypes;
    private int idCounter=1;
    private String currentNotification;

    public ArrayList<NotificationObserver> observerList = new ArrayList<NotificationObserver>();

    /**
     * Konstruktor fuer die Klasse SmartHome
     * Für jedes SmartHome wird erstmals eine Liste für die enthaltenen Sensor-Typen
     * und eine Liste für die Sensoren erstellt.
     */
    public SmartHome(){
        sensorList = new ArrayList<>();
        sensorTypes = new ArrayList<>();
    }
    
    /** 
     * Hinzufügen von Sensoren zum SmartHome
     * Eine auto-inkremente Id wird zugewiesen.
     * @param sensor Sensor
     */
    public void addSensor(Sensor sensor){
        sensorList.add(sensor);
        sensor.setId(idCounter);
        idCounter++;
    }

    /** 
     * Löschen eines Sensors vom SmartHome
     * @param index Index in SensorListe
     */
    public void removeSensor(int index){
        sensorList.remove(index);
    }
    
    /** 
     * Hinzufügen von Sensor-Typen
     * @param SensorType SensorTyp
     */
    public void addSensorType(String sensorType){
        if(!sensorTypes.contains(sensorType)){
            sensorTypes.add(sensorType);
        }
    }

    /** 
     * Löschen eines Sensors-Typs
     * @param index Index in SensorTyp-Liste
     */
    public void deleteSensorType(int index){
        sensorTypes.remove(index);
    }
    
    /** 
     * Ausgabe eines spezifischen Sensors
     * @param index Index in SensorListe
     * @return Sensor Der Sensor
     */
    public Sensor getSensor(int index){
        return sensorList.get(index);
    }

    /** 
     * Ausgabe eines spezifischen SensorTyps
     * @param index Index in SensorTypenListe
     * @return String Der Sensor-Typ
     */
    public String getSensorType(int index){
        return sensorTypes.get(index);
    }
    
    /** 
     * Setzen der Standort eines Sensors
     * @param index Index in SensorListe
     * @param location Standort des Sensors
     */
    public void editSensor(int index, String location){
        this.getSensor(index).setLocation(location);
    }

    /** 
     * Setzen des aktuellen Messwertes eines Sensors
     * Die Observer werden dabei benachrichtigt.
     * @param indexOfSensor Index in SensorListe
     */
    public void measureCurrentValueOfSensor(int indexOfSensor)
    {
        sensorList.get(indexOfSensor).measureCurrentValue();
        updateNotificationObserver(indexOfSensor);
    }

    /** 
     * Hinzufügen eines Beobachters
     * @param notificationObserver Beobachter
     */
    public void registerNotificationObserver(NotificationObserver notificationObserver){
        observerList.add(notificationObserver);
    }
    
    /** Entfernen eines Beobachters
     * @param notificationObserver Beobachter
     */
    public void removeNotificationObserver(NotificationObserver notificationObserver){
        observerList.remove(notificationObserver);
    }
    
    /** 
     * Benachrichtigen eines Beobachters
     * @param smartHome Das SmartHome
     * @param indexOfSensor Index in SensorListe
     */
    private void updateNotificationObserver(int indexOfSensor){
        for (NotificationObserver notificationObserver : observerList){
            notificationObserver.verifyIfNotificationNecessary(this, indexOfSensor);
        }
    }

    /** 
     * Ausgabe der aktuellen Notification des Beobachters
     * @param smartHome Das SmartHome
     * @param indexOfSensor Index in SensorListe
     */
    public String getNotification(){
        return currentNotification;
    }

    /** 
     * Setzen der aktuellen Notification vom Beobachter
     * @param smartHome Das SmartHome
     * @param indexOfSensor Index in SensorListe
     */
    public void setNotification(String notification){
        currentNotification = notification;
    }
}
