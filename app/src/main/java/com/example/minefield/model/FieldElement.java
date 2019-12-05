package com.example.minefield.model;

abstract public class FieldElement {
    protected Coordinate coordinate;
    protected Field field;
    protected boolean isFlagged = false;
    private boolean isUncovered = false;
    abstract void onUncover();

    public void setField(Field field) {
        this.field = field;
    }

    public FieldElement(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    abstract void uncoverEmpty();
    void toggleFlag(){
        isFlagged = !isFlagged;
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public void setUncovered(boolean uncovered) {
        isUncovered = uncovered;
    }
}
