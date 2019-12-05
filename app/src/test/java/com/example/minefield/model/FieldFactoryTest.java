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
}