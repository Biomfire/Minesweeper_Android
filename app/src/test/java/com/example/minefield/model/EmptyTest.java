package com.example.minefield.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
        assertFalse(empty.isUncovered());
    }
}