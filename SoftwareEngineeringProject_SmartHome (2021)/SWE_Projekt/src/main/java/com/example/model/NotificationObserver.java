package com.example.model;

/**
 * Dies ist die Dokumentation des Interfaces NotificationObserver.
 * Das Observer-Interface bildet die Grundlage für nützliche Benachritigungs-Observer für das SmartHome-System.
 * @author  Cordola O'Brien
 * @version 1.0
 * @see     SmartHome
 */
public interface NotificationObserver{
    /**
     * Kontrolle, ob die zugehörige Bedingung erfüllt ist.
     * @param smartHome  Das SmartHome
     * @param indexOfSensor Der angesprochene Sensor
     */
    void verifyIfNotificationNecessary(SmartHome smartHome, int indexOfSensor);

    /**
     * Ausgabe einer spezifischen Benachrichtigung, wenn die betreffende Bedingung erfüllt ist.
     */
    void displayNotification();
}
