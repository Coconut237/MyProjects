package com.example.model;

/**
 * Dies ist die Dokumentation der Klasse HighValueNotificationObserver. Sie implementiert das NotificationObserver Interface.
 * Die Klasse dient als Beobachter für das SmartHome-System, wenn ein bestimmter Messwert eines Sensors die maximal tolerierte Grenze überschreitet.
 * @author  Cordola O'Brien
 * @version 1.0
 * @see     SmartHome
 */
public class HighValueNotificationObserver implements NotificationObserver{
    
    private Sensor sensor;
    private String type;
    private String location;
    private String unit;
    private int id;
    private float maxValue;
    private float currentValue;
    private SmartHome smartHome;
    private int sensorIndex;
    private String notification;

    public HighValueNotificationObserver(){

    }

    /** 
     * Ausgabe einer spezifischen Benachrichtigung, wenn der aktualisierte Sensor-Messwert den definierten Maximal-Wert überschreitet.
     * Bei Eintreffen dieses Ereignisses, wird eine Benachrichtigung generiert.
     * @param smartHome Das SmartHome
     * @param sensorIndex Die Id des angesprochenen Sensors
     */
    @Override
    public void verifyIfNotificationNecessary(SmartHome smartHome, int sensorIndex) {
            sensor = smartHome.getSensor(sensorIndex);
            type = sensor.getType();
            location = sensor.getLocation();
            unit = sensor.getUnit();
            id = sensor.getId();
            maxValue = sensor.getMaxValue();
            currentValue = sensor.getCurrentValue();
            this.smartHome = smartHome;
            this.sensorIndex = sensorIndex;
            
        if(currentValue > maxValue){
            displayNotification();
        }
    }

     /** 
     * Ausgabe einer spezifischen Benachrichtigung, diese wird an das SmartHome übergeben.
     */
    public void displayNotification(){
            float gapToMaxValue = currentValue-maxValue;
            gapToMaxValue = Math.round(gapToMaxValue*10);
            gapToMaxValue = gapToMaxValue/10;

            notification = "Achtung: Messwert bei "+currentValue+" "+unit+"\n\nDer "+type+" "+location+" (Id "+id+") hat \nden maximalen Wert von "+maxValue+" "+unit+" um "+ gapToMaxValue+ " überschritten.";

            smartHome.setNotification(notification);
    }
}