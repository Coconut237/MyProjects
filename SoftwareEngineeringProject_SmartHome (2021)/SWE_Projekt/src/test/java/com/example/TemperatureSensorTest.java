package com.example;

import com.example.model.*;

import org.junit.*;
import static org.junit.Assert.*;

public class TemperatureSensorTest {

   private static Sensor sensor;

   @BeforeClass
   public static void create(){
      sensor = new TemperatureSensor("Kinderzimmer");
   }

    @Test
    public void testSetAndGetId() {
       sensor.setId(2);
       assertEquals(2, sensor.getId());  
    }

    @Test
    public void testGetType() {
       assertEquals("Temperatur-Sensor", sensor.getType());     
    }

    @Test
    public void testGetUnit() {
       assertEquals("°C", sensor.getUnit());     
    }

    @Test
    public void testSetAndGetLocation() {
       assertEquals("Kinderzimmer", sensor.getLocation());  

       sensor.setLocation("Badezimmer");
       assertEquals("Badezimmer", sensor.getLocation());
    }

    @Test
    public void testMeasureAndGetCurrentValue() {
      assertNotNull(sensor.getCurrentValue());  
      
      sensor.measureCurrentValue();
      float minRange = sensor.getMinValue()*0.9f;
      float maxRange = sensor.getMaxValue()*1.1f;

       assertTrue(minRange < sensor.getCurrentValue() && sensor.getCurrentValue() < maxRange);    
    }

    @Test
    public void getMinValue(){
       assertEquals(15, sensor.getMinValue(), 0);
    }

    @Test
    public void getMaxValue(){
       assertEquals(25, sensor.getMaxValue(), 0);
    }
 }


