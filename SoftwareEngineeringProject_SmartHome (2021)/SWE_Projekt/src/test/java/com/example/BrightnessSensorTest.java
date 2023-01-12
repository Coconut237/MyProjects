package com.example;

import com.example.model.*;

import org.junit.*;
import static org.junit.Assert.*;

public class BrightnessSensorTest {

   private static Sensor sensor;

   @BeforeClass
   public static void create(){
      sensor = new BrightnessSensor("Wohnzimmer");
   }

    @Test
    public void testSetAndGetId() {
       sensor.setId(4);
       assertEquals(4, sensor.getId());  
    }

    @Test
    public void testGetType() {
       assertEquals("Helligkeits-Sensor", sensor.getType());     
    }

    @Test
    public void testGetUnit() {
       assertEquals("Lux", sensor.getUnit());     
    }

    @Test
    public void testSetAndGetLocation() {
       assertEquals("Wohnzimmer", sensor.getLocation());  

       sensor.setLocation("Küche");
       assertEquals("Küche", sensor.getLocation());
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
       assertEquals(40, sensor.getMinValue(), 0);
    }

    @Test
    public void getMaxValue(){
       assertEquals(200, sensor.getMaxValue(), 0);
    }
 }


