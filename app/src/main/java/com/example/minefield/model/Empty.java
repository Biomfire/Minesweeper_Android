package com.example.minefield.model;

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
}
