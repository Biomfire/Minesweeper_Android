package com.example.minefield.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MineTest {
    private Field field;
    @Before
    public void setUp() throws Exception {
        field = mock(Field.class);
    }

    @Test
    public void testFlagToggleOnce() {
        Mine mine = new Mine(new Coordinate(0,0));
        mine.toggleFlag();
        assertTrue(mine.isFlagged);
    }
    @Test
    public void testFlagToggleTwice() {
        Mine mine = new Mine(new Coordinate(0,0));
        mine.toggleFlag();
        mine.toggleFlag();
        assertFalse(mine.isFlagged);
    }
    @Test
    public void testFlagToggleThrice() {
        Mine mine = new Mine(new Coordinate(0,0));
        mine.toggleFlag();
        mine.toggleFlag();
        mine.toggleFlag();
        assertTrue(mine.isFlagged);
    }

    @Test
    public void testFlaggedUncover() {
        Mine mine = new Mine(new Coordinate(0,0));
        mine.toggleFlag();
        mine.onUncover();
        assertTrue(mine.isFlagged);
        assertFalse(mine.isUncovered());
    }

    @Test
    public void testunCover() {
        Mine mine = new Mine(new Coordinate(0,0));
        mine.setField(field);
        mine.onUncover();
        assertTrue(mine.isUncovered());
        verify(field, times(1)).explodeMine();
    }

    @Test
    public void testuncoverEmpty() {
        Mine mine = new Mine(new Coordinate(0,0));
        mine.uncoverEmpty();
        assertFalse(mine.isUncovered());
        assertFalse(mine.isFlagged);
    }

    @Test
    public void testSaveFunction() {
        Mine mine= new Mine(new Coordinate(0,0));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            mine.save(baos);
            byte[] byteArray = baos.toByteArray();
            Assert.assertEquals("M00", new String(byteArray));
        } catch (IOException e) {
            fail();
        }
    }
    @Test
    public void testSaveFunctionUncovered(){
        Mine mine = new Mine(new Coordinate(0,0));
        mine.setField(field);
        mine.onUncover();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            mine.save(baos);
            byte[] byteArray = baos.toByteArray();
            Assert.assertEquals("M10", new String(byteArray));
        } catch (IOException e) {
            fail();
        }
    }
    @Test
    public void testSaveFunctionFlagged(){
        Mine mine = new Mine(new Coordinate(0,0));
        mine.setField(field);
        mine.toggleFlag();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            mine.save(baos);
            byte[] byteArray = baos.toByteArray();
            Assert.assertEquals("M01", new String(byteArray));
        } catch (IOException e) {
            fail();
        }
    }
}