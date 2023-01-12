package com.example;

import com.example.model.*;

import org.junit.*;
import static org.junit.Assert.*;

public class CO2SensorTest {

   private static Sensor sensor;

   @BeforeClass
   public static void create(){
      sensor = new CO2Sensor("Sportzimmer");
   }

    @Test
    public void testSetAndGetId() {
       sensor.setId(3);
       assertEquals(3, sensor.getId());  
    }

    @Test
    public void testGetType() {
       assertEquals("CO2-Gehalt-Sensor", sensor.getType());     
    }

    @Test
    public void testGetUnit() {
       assertEquals("ppm", sensor.getUnit());     
    }

    @Test
    public void testSetAndGetLocation() {
       assertEquals("Sportzimmer", sensor.getLocation());  

       sensor.setLocation("Schlafzimmer");
       assertEquals("Schlafzimmer", sensor.getLocation());
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
       assertEquals(500, sensor.getMinValue(), 0);
    }

    @Test
    public void getMaxValue(){
       assertEquals(1400, sensor.getMaxValue(), 0);
    }
 }


