package com.example.minefield.model;

import org.junit.Before;
import org.junit.Test;

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
}