package com.example.minefield.model;

import static java.lang.Math.floor;
import static java.lang.Math.pow;

public class Field {
    private FieldElement[][] fields;

    private boolean hasMineExploded;

    public Field(FieldElement[][] inputfields) {
        this.fields = inputfields;
    }

    public void explodeMine() {
        this.hasMineExploded = true;
    }

    public void uncoverNearbyEmpty(Coordinate coordinate) {
        int sizeX = this.getFieldsX();
        int sizeY = this.getFieldsY();
        boolean isInLeftmostCol = coordinate.getX() == 0;
        boolean isInRightmostCol = coordinate.getX() == sizeX-1;
        boolean isInLowestRow = coordinate.getY() == 0;
        boolean isInHighestRow = coordinate.getY() == sizeY-1;
        boolean[] boollist = {isInHighestRow, isInRightmostCol, isInLowestRow, isInLeftmostCol};
        for (int i = 0; i < 4; i++) {
            if (!boollist[i]) {
                int deltaX = i%2*(int)pow(-1, i/2);
                int deltaY = (i+1)%2*(int)pow(-1, i/2);
                getFieldElement(new Coordinate(coordinate.getX() + deltaX, coordinate.getY() + deltaY)).uncoverEmpty();
            }
        }
    }

    public FieldElement getFieldElement(Coordinate coordinate) {
        return fields[coordinate.getX()][coordinate.getY()];
    }

    public int getFieldsX() {
        return fields.length;
    }

    public int getFieldsY() {
        return fields[0].length;
    }

    public boolean hasMineExploded() {
        return hasMineExploded;
    }
}
