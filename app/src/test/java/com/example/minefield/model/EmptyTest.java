package com.example.minefield.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class EmptyTest {
    @Mock
    Field field;

    @Before
    public void setUp() throws Exception {
        field = mock(Field.class);
    }

    @Test
    public void testFlagToggleOnce() {
        Empty empty = new Empty(new Coordinate(0,0),1);
        empty.toggleFlag();
        assertTrue(empty.isFlagged);
    }
    @Test
    public void testFlagToggleTwice() {
        Empty empty = new Empty(new Coordinate(0,0),1);
        empty.toggleFlag();
        empty.toggleFlag();
        assertFalse(empty.isFlagged);
    }
    @Test
    public void testFlagToggleThrice() {
        Empty empty = new Empty(new Coordinate(0,0),1);
        empty.toggleFlag();
        empty.toggleFlag();
        empty.toggleFlag();
        assertTrue(empty.isFlagged);
    }

    @Test
    public void testFlaggedUncover() {
        Empty empty = new Empty(new Coordinate(0,0),1);
        empty.toggleFlag();
        empty.onUncover();
        assertTrue(empty.isFlagged);
        assertFalse(empty.isUncovered());
    }

    @Test
    public void testonUncover() {
        Empty empty = new Empty(new Coordinate(0,0),1);
        empty.setField(field);
        empty.onUncover();
        assertTrue(empty.isUncovered());
    }

    @Test
    public void testEmptyUncoverWithoutBombNeighhbour() {
        Empty empty = new Empty(new Coordinate(0,0), 0);
        empty.setField(field);
        empty.uncoverEmpty();
        assertTrue(empty.isUncovered());
        verify(field, times(1)).uncoverNearbyEmpty(empty.coordinate);
    }
    @Test
    public void testonEmptyUncoverWithBombNeighbour(){
        Empty empty = new Empty(new Coordinate(0,0),1);
        empty.uncoverEmpty();
        assertTrue(empty.isUncovered());
    }

    @Test
    public void testSaveFunctionZeroNearbyMines() {
        Empty empty = new Empty(new Coordinate(0,0), 0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            empty.save(baos);
            byte[] byteArray = baos.toByteArray();
            Assert.assertEquals("000", new String(byteArray));
        } catch (IOException e) {
            fail();
        }

    }

    @Test
    public void testSaveFuncitonFourNearbyMines() {
        Empty empty = new Empty(new Coordinate(0,0), 4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            empty.save(baos);
            byte[] byteArray = baos.toByteArray();
            Assert.assertEquals("400", new String(byteArray));
        } catch (IOException e) {
            fail();
        }
    }
    @Test
    public void testSaveFunctionUncovered(){
        Empty empty = new Empty(new Coordinate(0,0), 4);
        empty.setField(field);
        empty.onUncover();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            empty.save(baos);
            byte[] byteArray = baos.toByteArray();
            Assert.assertEquals("410", new String(byteArray));
        } catch (IOException e) {
            fail();
        }
    }
    @Test
    public void testSaveFunctionFlagged(){
        Empty empty = new Empty(new Coordinate(0,0), 4);
        empty.setField(field);
        empty.toggleFlag();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            empty.save(baos);
            byte[] byteArray = baos.toByteArray();
            Assert.assertEquals("401", new String(byteArray));
        } catch (IOException e) {
            fail();
        }
    }
}