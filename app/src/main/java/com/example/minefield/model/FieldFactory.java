package com.example.minefield.model;

public class FieldFactory {
    private int sizeX = 1;
    private int sizeY = 1;
    private int mineCount = 0;

    public Field create(){
        FieldElement[][] fields = new FieldElement[sizeX][sizeY];
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                fields[i][j] = new Empty(new Coordinate(i, j), 0);
            }
        }
        return new Field(fields);
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }
}
