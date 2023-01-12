package com.example.model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Dies ist die Dokumentation der abstrakten Klasse Sensor. Ein Sensor eines bestimmten Sensor-Typs mit bestimmter Einheit
 * dient zum Messen eines Messwertes an einem bestimmten Standort. Jeder Sensor besitzt eine eigene Id.
 * Diesem Sensor besitzt einen Minimal- und einen Maximal-Wert.
 * @author  Cordola O'Brien
 * @version 1.0
 * @see     SmartHome
 */
public abstract class Sensor{
    private int id;
    private String type;
    private String location;
    private String unit;
    private float currentValue;
    private float minValue;
    private float maxValue;

    /**
     * Konstruktor fuer die Klasse Sensor.
     * Der Messwert wird erstmals gemessen.
     * @param location Standort des Sensors
     */
    public Sensor(String location){
        this.location = location;
        measureCurrentValue();
    }
    
    /** 
     * Ausgabe der Sensor_Id
     * @return int Sensor_Id
     */
    public int getId() {
        return id;
    }

    /** 
     * Setzen der Sensor_Id
     * @param id Sensor_Id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /** 
     * Ausgabe des Sensor-Tyos
     * @return String Sensor-Tyo
     */
    public String getType() {
        return type;
    }

    /** 
     * Ausgabe der Standort
     * @return String Standort
     */
    public String getLocation() {
        return location;
    }

    /** 
     * Setzen der Standort
     * @param location Standort
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /** 
     * Ausgabe der Einheit
     * @return String Einheit
     */
    public String getUnit() {
        return unit;
    }
    
    /** 
     * Ausgabe des aktuellen Messwertes
     * @return float Messwert
     */
    public float getCurrentValue() {
        return currentValue;
    }
    
    /** 
     * Ausgabe des maximal tolerierten Messwertes
     * @return float Maximaler Messwert
     */
    public float getMaxValue() {
        return maxValue;
    }
    
    /** 
     * Ausgabe des minimal tolerierten Messwertes
     * @return float Minimaler Messwert
     */
    public float getMinValue() {
        return minValue;
    }

    /** 
     * Setzen eines zufälligen Messwertes des Sensors (in einer +/- 10% abweichenden Spanne zwischen Minimal- und Maximal-Wert)
     */
    public void measureCurrentValue()
    {
        float randomNum = ThreadLocalRandom.current().nextFloat(getMinValue()*0.9f, getMaxValue()*1.1f);
        randomNum = Math.round(randomNum*10);
        currentValue = randomNum/10;
    }
}
