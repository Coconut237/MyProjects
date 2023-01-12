package com.example;

import com.example.controller.*;

/**
* Die Klasse App dient zum Ausführen des Programms.
* @author  Cordola O'Brien
* @version 1.0
* @see     Controller
*/
public class App{
    
    /** 
     * Main Method. Der Controller wird aufgerufen.
     */
    public static void main( String[] args )
    {
        Controller controller = new Controller();
        controller.showMainView();
    }   
}
