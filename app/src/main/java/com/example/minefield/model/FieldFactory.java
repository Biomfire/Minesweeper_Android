package com.example.minefield.model;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.floor;
import static java.lang.Math.min;

public class FieldFactory {
    private int sizeX = 1;
    private int sizeY = 1;
    private int mineCount = 5;

    public Field create() {
        FieldElement[][] fields = new FieldElement[sizeX][sizeY];
        ArrayList<Coordinate> minePositions = FillFieldWithMines(fields);
        fillFieldWithEmpty(fields, minePositions);
        Field field = new Field(fields, mineCount);
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                field.getFieldElement(new Coordinate(i, j)).setField(field);
            }
        }
        return field;
    }

    private ArrayList<Coordinate> FillFieldWithMines(FieldElement[][] fields) {
        int cnt = 0;
        Random rand = new Random();
        ArrayList<Coordinate> minePositions = new ArrayList<Coordinate>();
        while (cnt != mineCount) {
            int x = rand.nextInt(sizeX);
            int y = rand.nextInt(sizeY);
            if (fields[x][y] == null) {
                Coordinate coords = new Coordinate(x, y);
                fields[x][y] = new Mine(coords);
                minePositions.add(coords);
                cnt++;
            }
        }
        return minePositions;
    }

    private void fillFieldWithEmpty(FieldElement[][] fields, ArrayList<Coordinate> minePositions) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (fields[i][j] == null) {
                    int mineCount = getMineCount(minePositions, i, j);
                    fields[i][j] = new Empty(new Coordinate(i, j), mineCount);
                }
            }
        }
    }

    private int getMineCount(ArrayList<Coordinate> minePositions, int i, int j) {
        int mineCount = 0;
        for (int k = 0; k < 9; k++) {
            int deltaX = k % 3 - 1;
            int deltaY = (int) (floor(k / 3) - 1);
            for (Coordinate minecoord : minePositions) {
                if (minecoord.getX() == i + deltaX && minecoord.getY() == j + deltaY) {
                    mineCount++;
                }
            }
        }
        return mineCount;
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

    public Field load() {
        return null;
    }
}
