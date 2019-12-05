package com.example.minefield.model;

public class Mine extends FieldElement {

    public Mine(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    void onUncover() {
        if(!isFlagged){
            field.explodeMine();
            setUncovered(true);
        }
    }
    void uncoverEmpty(){};
}
