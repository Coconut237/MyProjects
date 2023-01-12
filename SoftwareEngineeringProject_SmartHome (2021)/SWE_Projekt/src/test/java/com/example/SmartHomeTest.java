package com.example;

import com.example.model.*;
import com.example.model.LowValueNotificationObserver;

import org.junit.*;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class SmartHomeTest {
    
    private static SmartHome smartHome;

    @Rule
	public ExpectedException methodThrowsException = ExpectedException.none();

    @BeforeClass
    public static void create(){
      smartHome = new SmartHome();
    }

    @Test
    public void testAddSensor() {
       //Check if adding a sensor to the sensorList works.
       Sensor sensor1 = new BrightnessSensor("Küche");
       smartHome.addSensor(sensor1);
       int indexOfLastAddedSensor = smartHome.sensorList.size()-1;
       assertEquals(sensor1, smartHome.getSensor(indexOfLastAddedSensor));  

       //Check if auto-inkrement Id works
       Sensor sensor2 = new TemperatureSensor("Wohnzimmer");
       smartHome.addSensor(sensor2);
       int idOfSensor1 = smartHome.getSensor(smartHome.sensorList.size()-2).getId();
       int idOfSensor2 = smartHome.getSensor(smartHome.sensorList.size()-1).getId();
       assertTrue(idOfSensor1+1 == idOfSensor2);
    }

    @Test
    public void testAddSensorType(){
        //Check if adding a sensorType to the sensorTypeList works.
        String sensorType = "Helligkeits-Sensor";
        smartHome.addSensorType(sensorType);
        assertEquals("Helligkeits-Sensor", smartHome.getSensorType(0));
        
        //Check, if a type can only be added once.
        smartHome.addSensorType(sensorType);
        methodThrowsException.expect(IndexOutOfBoundsException.class);
        String duplicate = smartHome.getSensorType(1);
    }

    @Test
    public void testMeasureCurrentValueOfSensor(){
        Sensor sensor = new BrightnessSensor("Kunstzimmer");
        smartHome.addSensor(sensor);

        //Check if measuring a sensor changes its value.
        float valueOfSensorIndex0 = smartHome.getSensor(0).getCurrentValue();
        smartHome.measureCurrentValueOfSensor(0);
        float newValueOfSensorIndex0 = smartHome.getSensor(0).getCurrentValue();
        assertTrue(valueOfSensorIndex0 != newValueOfSensorIndex0);
    }

    @Test
    public void testEditSensor(){
        Sensor sensor = new BrightnessSensor("Kunstzimmer");
        smartHome.addSensor(sensor);
        smartHome.editSensor(0, "Kammer");
        assertEquals("Kammer", smartHome.getSensor(0).getLocation());
    }

    @Test
    public void testdeleteSensor(){
        Sensor sensor1 = new BrightnessSensor("Wohnzimmer");
        Sensor sensor2 = new CO2Sensor("Schlafzimmer");
        smartHome.addSensor(sensor1);
        smartHome.addSensor(sensor2);
        int countOfSensorsBeforeDeletingASensor = smartHome.sensorList.size();
        smartHome.removeSensor(1);
        int countOfSensorsAfterDeletingASensor = smartHome.sensorList.size();
        assertTrue(countOfSensorsBeforeDeletingASensor-1 == countOfSensorsAfterDeletingASensor);
    }

    @Test
    public void testdeleteSensorType(){
        smartHome.addSensorType("CO2-Gehalt-Sensor");
        smartHome.addSensorType("Temperatur-Sensor");
        int countOfSensorTypesBeforeDeletingASensorType = smartHome.sensorTypes.size();
        smartHome.deleteSensorType(1);
        int countOfSensorTypesAfterDeletingASensorType = smartHome.sensorTypes.size();
        assertTrue(countOfSensorTypesBeforeDeletingASensorType-1 == countOfSensorTypesAfterDeletingASensorType);
    }

    @Test
    public void testregisterNotificationObserver(){
        NotificationObserver notificationObserver = new LowValueNotificationObserver();
        smartHome.registerNotificationObserver(notificationObserver);
        assertEquals(1, smartHome.observerList.size());
        assertEquals(notificationObserver, smartHome.observerList.get(0));
    }

    @Test
    public void testremoveNotificationObserver(){
        NotificationObserver notificationObserver = new LowValueNotificationObserver();
        smartHome.registerNotificationObserver(notificationObserver);
        smartHome.removeNotificationObserver(notificationObserver);
        assertEquals(0, smartHome.observerList.size());
    }

    @Test
    public void testUpdateNoticationObserver(){
        NotificationObserver notificationObserver = new LowValueNotificationObserver();
        smartHome.registerNotificationObserver(notificationObserver);
        
        Sensor sensor1 = new BrightnessSensor("Garage");
        smartHome.addSensor(sensor1);
        while(smartHome.getSensor(0).getCurrentValue() > smartHome.getSensor(0).getMinValue()){
            smartHome.measureCurrentValueOfSensor(0);
        }

        String notification = smartHome.getNotification();
        assertTrue(notification.contains("Achtung"));
    }

    @Test
    public void testSetAndGetNotification(){
        String notification = "Benachrichtigung";
        smartHome.setNotification(notification);
        assertEquals("Benachrichtigung", smartHome.getNotification());
    }
}
