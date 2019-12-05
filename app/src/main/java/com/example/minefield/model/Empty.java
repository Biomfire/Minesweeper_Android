package com.example.minefield.model;

public class Empty extends FieldElement {
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
        if(nearbyMines == 0) {
            setUncovered(true);
            field.uncoverNearbyEmpty(coordinate);
        }
    }
}
