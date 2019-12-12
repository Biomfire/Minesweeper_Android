package com.example.minefield.model;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class Empty extends FieldElement {
    public int getNearbyMines() {
        return nearbyMines;
    }

    private int nearbyMines;

    public Empty(Coordinate coordinate, int nearbyMines) {
        super(coordinate);
        this.nearbyMines = nearbyMines;
    }

    @Override
    void onUncover() {
        if(!isFlagged){
            setUncovered(true);
            field.uncoverNearbyEmpty(coordinate);
        }
    }

    @Override
    void uncoverEmpty() {
        if(!isUncovered() && !isFlagged) {
            setUncovered(true);
            if(nearbyMines == 0) {
                field.uncoverNearbyEmpty(coordinate);
            }
        }
    }

    @Override
    public void save(OutputStream output) throws IOException {
        output.write(Integer.toString(nearbyMines).getBytes(Charset.forName("UTF-8")));
        super.save(output);
    }
    static public Empty load(Coordinate coordinate, int numberOfMines, boolean isUncovered, boolean isFlagged){
        Empty empty = new Empty(coordinate, numberOfMines);
        empty.isUncovered = isUncovered;
        empty.isFlagged = isFlagged;
        return empty;
    }
}
