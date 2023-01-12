package com.example;

import com.example.model.*;

import org.junit.*;
import static org.junit.Assert.*;

public class LowValueNotificationObserverTest {

    private static NotificationObserver notificationObserver;

    @BeforeClass
    public static void create(){
        notificationObserver = new LowValueNotificationObserver();
    }

    @Test
    public void testVerifyIfNotificationNecessaryAndDisplayNotification(){
        //Check if Notification gets through, when the Value is under the Limit-Value.
        SmartHome smartHome = new SmartHome();
        smartHome.registerNotificationObserver(notificationObserver);
        
        Sensor sensor1 = new TemperatureSensor("Badezimmer");
        smartHome.addSensor(sensor1);
        while(smartHome.getSensor(0).getCurrentValue() > smartHome.getSensor(0).getMinValue()){
            smartHome.measureCurrentValueOfSensor(0);
        }

        String notification1 = smartHome.getNotification();
        assertTrue(notification1.contains("Achtung"));
 
        //Check if Notification is not renewed, when the value is in the tolerated range.
        Sensor sensor2 = new TemperatureSensor("Kinderzimmer");
        smartHome.addSensor(sensor2);
        while(smartHome.getSensor(1).getCurrentValue() < smartHome.getSensor(1).getMinValue()){
            smartHome.measureCurrentValueOfSensor(0);
        }

        //Notification should not have been updated
        String notification2 = smartHome.getNotification();
        assertEquals(notification1, notification2);
    }
}
