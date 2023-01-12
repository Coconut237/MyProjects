package com.example.view;

import javax.swing.*;
import java.awt.Color;

/**
 * Dies ist die Dokumentation der Klasse NotificationView. Dieser View bildet die GUI bzw. ein PopUp-Fenster, mit einer spezifischen Meldung.
 * Der User wird über ein bestimmtes Ereignis benachrichtigt z.B. das Überschreiten eines bestimmten Maximal-Messwertes eines Sensors.
 * @author  Cordola O'Brien
 * @version 1.0
 */
public class NotificationView {
    private JFrame frame;
    private String notification;
    
    /**
     * Konstruktor fuer die Klasse NotificationView
     * Dem Konstruktor wird eine bestimmte Nachricht übergeben, die er anzeigen soll.
     * @param notification Die Nachricht
     */
    public NotificationView(String notification){
        this.notification = notification;
    }

    /**
     * Erstellen und Anzeigen der Meldung in einer Pane
     */
    public void showView(){
        setBackgroundColor();
        setPane(notification);
    }

    /**
     * Setzen der Hintergrundfarbe
     */
    private void setBackgroundColor(){
        UIManager UI=new UIManager();
        Color definedColor = new Color(228, 180, 180); 
        UI.put("OptionPane.background", definedColor);
        UI.put("Panel.background", definedColor);
    }

    /**
     * Setzen und Anzeigen der Pane mit der entsprechenden Meldung.
     * @param notification Die Nachricht
     */
    private void setPane(String notification){
        JOptionPane pane = new JOptionPane();
        pane.showMessageDialog(frame, notification);
    }
}
