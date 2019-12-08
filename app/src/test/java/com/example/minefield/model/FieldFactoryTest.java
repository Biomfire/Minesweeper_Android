package com.example.minefield.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FieldFactoryTest {
    @Test
    public void testFactorySizeX() {
        FieldFactory factory = new FieldFactory();
        factory.setSizeX(10);
        Field field = factory.create();
        assertEquals(10, field.getFieldsX());
    }

    @Test
    public void testFactorySizeY() {
        FieldFactory factory = new FieldFactory();
        factory.setSizeY(10);
        Field field = factory.create();
        assertEquals(10, field.getFieldsY());
    }

    @Test
    public void testFactoryOneMine() {
        FieldFactory factory = new FieldFactory();
        factory.setMineCount(1);
        Field field = factory.create();
        assertTrue(field.getFieldElement(new Coordinate(0, 0)) instanceof Mine);
    }

    @Test
    public void testFactoryMultipleMines() {
        FieldFactory factory = new FieldFactory();
        factory.setMineCount(10);
        factory.setSizeY(10);
        factory.setSizeX(10);
        Field field = factory.create();
        int cnt = 0;
        for (int i = 0; i < field.getFieldsX(); i++) {
            for (int j = 0; j < field.getFieldsY(); j++) {
                if(field.getFieldElement(new Coordinate(i,j)) instanceof Mine){
                    cnt++;
                }
            }
        }
        assertEquals(10, cnt);
    }

    @Test
    public void testGetMineCount() {
        FieldFactory factory = new FieldFactory();
    }
}
