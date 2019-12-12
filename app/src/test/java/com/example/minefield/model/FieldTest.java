package com.example.minefield.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FieldTest {
    Field field;

    @Before
    public void setUp() throws Exception {
        FieldElement[][] fields = new FieldElement[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = mock(FieldElement.class);
            }
        }
        field = new Field(fields,0 );
    }

    @Test
    public void testBlowUp() {
        Field field = new FieldFactory().setSizeX(10).create();
        field.explodeMine();
        assertTrue(field.hasMineExploded());
    }

    @Test
    public void testunCoverMiddle() {
        field.uncoverNearbyEmpty(new Coordinate(1, 1));
        verify(field.getFieldElement(new Coordinate(0, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 1)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 0)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 1)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 2)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 0)), times(0)).uncoverEmpty();
    }

    @Test
    public void testunCoverEmptyLeftBottom() {
        field.uncoverNearbyEmpty(new Coordinate(0, 0));
        verify(field.getFieldElement(new Coordinate(0, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 1)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 0)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 0)), times(0)).uncoverEmpty();
    }

    @Test
    public void testunCoverEmptyMiddleBottom() {
        field.uncoverNearbyEmpty(new Coordinate(1, 0));
        verify(field.getFieldElement(new Coordinate(0, 0)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 1)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 0)), times(1)).uncoverEmpty();
    }

    @Test
    public void testunCoverEmptyRightBottom() {
        field.uncoverNearbyEmpty(new Coordinate(2, 0));
        verify(field.getFieldElement(new Coordinate(0, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 0)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 1)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 0)), times(0)).uncoverEmpty();
    }

    @Test
    public void testunCoverEmptyLeftMiddle() {
        field.uncoverNearbyEmpty(new Coordinate(0, 1));
        verify(field.getFieldElement(new Coordinate(0, 0)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 1)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 2)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 0)), times(0)).uncoverEmpty();
    }

    @Test
    public void testunCoverEmptyRightMiddle() {
        field.uncoverNearbyEmpty(new Coordinate(2, 1));
        verify(field.getFieldElement(new Coordinate(0, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 1)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 2)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 0)), times(1)).uncoverEmpty();
    }

    @Test
    public void testunCoverEmptyLeftTop() {
        field.uncoverNearbyEmpty(new Coordinate(0, 2));
        verify(field.getFieldElement(new Coordinate(0, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 1)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 2)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 0)), times(0)).uncoverEmpty();
    }

    @Test
    public void testunCoverEmptyMiddleTop() {
        field.uncoverNearbyEmpty(new Coordinate(1, 2));
        verify(field.getFieldElement(new Coordinate(0, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 1)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 2)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 2)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 0)), times(0)).uncoverEmpty();
    }
    @Test
    public void testunCoverEmptyRightTop() {
        field.uncoverNearbyEmpty(new Coordinate(2, 2));
        verify(field.getFieldElement(new Coordinate(0, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 0)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 1)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 1)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(1, 2)), times(1)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(0, 2)), times(0)).uncoverEmpty();
        verify(field.getFieldElement(new Coordinate(2, 0)), times(0)).uncoverEmpty();
    }

    @Test
    public void testSave() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        field.save(baos);
        verify(field.getFieldElement(new Coordinate(0, 0)), times(1)).save(baos);
        verify(field.getFieldElement(new Coordinate(0, 1)), times(1)).save(baos);
        verify(field.getFieldElement(new Coordinate(1, 0)), times(1)).save(baos);
        verify(field.getFieldElement(new Coordinate(1, 1)), times(1)).save(baos);
        verify(field.getFieldElement(new Coordinate(2, 1)), times(1)).save(baos);
        verify(field.getFieldElement(new Coordinate(1, 2)), times(1)).save(baos);
        verify(field.getFieldElement(new Coordinate(2, 2)), times(1)).save(baos);
        verify(field.getFieldElement(new Coordinate(0, 2)), times(1)).save(baos);
        verify(field.getFieldElement(new Coordinate(2, 0)), times(1)).save(baos);
        byte[] byteArray = baos.toByteArray();
        Assert.assertEquals("\n\n\n", new String(byteArray));
    }

}