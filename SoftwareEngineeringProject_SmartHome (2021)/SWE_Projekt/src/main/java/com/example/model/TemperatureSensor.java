package com.example.model;

/**
 * Dies ist die Dokumentation der Klasse TemperatureSensor. Der Temperatur-Sensor gibt den Messwert der 
 * Temperatur in "°C" zurück. Es gibt einen minimal und maximal tolerierten Messwert, der festgelegt ist.
 * @author  Cordola O'Brien
 * @version 1.0
 * @see     Sensor
 */
public class TemperatureSensor extends Sensor{

    private final String TYPE = "Temperatur-Sensor";
    private final String UNIT = "°C";
    private final float MINVALUE = 15;
    private final float MAXVALUE = 25;

    /**
     * Konstruktor fuer die Klasse TemperatureSensor
     * @param location Standort
     */
    public TemperatureSensor(String location){
        super(location);
    }
    
    /** 
     * Ausgabe des Sensor-Typs
     * @return String Sensor-Typ
     */
    @Override
    public String getType() {
        return TYPE;
    }
    
    /** 
     * Ausgabe der Einheit
     * @return String Einheit
     */
    @Override
    public String getUnit() {
        return UNIT;
    }
    
    /** 
     * Ausgabe des maximal tolerierten Messwertes
     * @return float Maximaler Messwert
     */
    @Override
    public float getMaxValue() {
        return MAXVALUE;
    }
    
    /** 
     * Ausgabe des minimal tolerierten Messwertes
     * @return float Minimaler Messwert
     */
    @Override
    public float getMinValue() {
        return MINVALUE;
    }
}
