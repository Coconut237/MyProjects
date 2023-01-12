package com.example.model;

/**
 * Dies ist die Dokumentation der Klasse LowValueNotificationObserver. Sie implementiert das NotificationObserver Interface.
 * Die Klasse dient als Beobachter für das SmartHome-System, wenn ein bestimmter Messwert eines Sensors die minimal tolerierte Grenze unterschreitet.
 * @author  Cordola O'Brien
 * @version 1.0
 * @see     SmartHome
 */
public class LowValueNotificationObserver implements NotificationObserver{
    
    private Sensor sensor;
    private String type;
    private String location;
    private String unit;
    private int id;
    private float minValue;
    private float currentValue;
    private SmartHome smartHome;
    private int sensorIndex;

    /** 
     * Ausgabe einer spezifischen Benachrichtigung, wenn der aktualisierte Sensor-Messwert den definierten Minimal-Wert unterschreitet.
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
            minValue = sensor.getMinValue();
            currentValue = sensor.getCurrentValue();
            this.smartHome = smartHome;
            this.sensorIndex = sensorIndex;

            if(currentValue < minValue){
                displayNotification();
            }
    }

     /** 
     * Ausgabe einer spezifischen Benachrichtigung, diese wird an das SmartHome übergeben.
     */
    @Override
    public void displayNotification(){
            float gapToMinValue = minValue-currentValue;
            gapToMinValue = Math.round(gapToMinValue*10);
            gapToMinValue = gapToMinValue/10;

            String notification = "Achtung: Messwert bei "+currentValue+" "+unit+"\n\nDer "+type+" "+location+" (Id "+id+") hat \nden minimalen Wert von "+minValue+" "+unit+" um "+ gapToMinValue+ " unterschritten.";

            smartHome.setNotification(notification);
    }
}