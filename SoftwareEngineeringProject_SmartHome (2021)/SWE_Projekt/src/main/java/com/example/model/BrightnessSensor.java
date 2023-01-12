package com.example.model;

/**
 * Dies ist die Dokumentation der Klasse BrightnessSensor. Der Helligkeits-Sensor gibt den Messwert der 
 * Helligkeit in "Lux" zurück. Es gibt einen minimal und maximal tolerierten Messwert, der festgelegt ist.
 * @author  Cordola O'Brien
 * @version 1.0
 * @see     Sensor
 */
public class BrightnessSensor extends Sensor{

    private final String TYPE = "Helligkeits-Sensor";
    private final String UNIT = "Lux";
    private final float MINVALUE = 40;
    private final float MAXVALUE = 200;

    /**
     * Konstruktor fuer die Klasse BrightnessSensor
     * @param location Standort
     */
    public BrightnessSensor(String location){
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
