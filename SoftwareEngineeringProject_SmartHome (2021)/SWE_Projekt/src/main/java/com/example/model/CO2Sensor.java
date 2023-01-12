package com.example.model;

/**
 * Dies ist die Dokumentation der Klasse CO2Sensor. Der CO2-Gehalt-Sensor gibt den Messwert des 
 * CO2-Gehaltes in "ppm" zurück. Es gibt einen minimal und maximal tolerierten Messwert, der festgelegt ist.
 * @author  Cordola O'Brien
 * @version 1.0
 * @see     Sensor
 */
public class CO2Sensor extends Sensor{

    private final String TYPE = "CO2-Gehalt-Sensor";
    private final String UNIT = "ppm";
    private final float MINVALUE = 500;
    private final float MAXVALUE = 1400;

    /**
     * Konstruktor fuer die Klasse CO2Sensor
     * @param location Standort
     */
    public CO2Sensor(String location){
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