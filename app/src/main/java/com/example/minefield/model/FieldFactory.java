package com.example.minefield.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.floor;
import static java.lang.Math.min;

public class FieldFactory {
    private int sizeX = 1;
    private int sizeY = 1;
    private int mineCount = 5;

    public Field create() {
        assert(sizeX*sizeY>=mineCount);
        FieldElement[][] fields = new FieldElement[sizeX][sizeY];
        ArrayList<Coordinate> minePositions = FillFieldWithMines(fields);
        fillFieldWithEmpty(fields, minePositions);
        Field field = new Field(fields, mineCount);
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


    public FieldFactory setSizeX(int sizeX) {
        this.sizeX = sizeX;
        return this;
    }

    public FieldFactory setSizeY(int sizeY) {
        this.sizeY = sizeY;
        return this;
    }

    public FieldFactory setMineCount(int mineCount) {
        this.mineCount = mineCount;
        return this;
    }

    static public Field load(InputStream input) {
        ArrayList<ArrayList<FieldElement>> fieldList = new ArrayList<> ();
        int mineCount = 0;
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            String line;
            int i = 0;
            while((line = r.readLine()) != null){
                fieldList.add(new ArrayList<FieldElement>());
                for(int k = 0; k < line.length(); k+=3){
                    boolean isUncovered = line.charAt(k + 1) == '1';
                    boolean isFlagged = line.charAt(k + 2) == '1';
                    if(line.charAt(k) == 'M'){
                        fieldList.get(i).add(Mine.load(new Coordinate(i, k/3), isUncovered, isFlagged));
                        mineCount++;
                    }
                    else{
                        int numberOfNearbyMines = Character.getNumericValue(line.charAt(k));
                        fieldList.get(i).add(Empty.load(new Coordinate(i, k/3), numberOfNearbyMines, isUncovered, isFlagged));
                    }
                }
                i++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FieldElement[][] array = new FieldElement[fieldList.size()][];
        for (int i = 0; i < fieldList.size(); i++) {
            ArrayList<FieldElement> row = fieldList.get(i);
            array[i] = row.toArray(new FieldElement[row.size()]);
        }
        return new Field(array, mineCount);
    }
}
