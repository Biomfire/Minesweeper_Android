package com.example.minefield.presenter;

import com.example.minefield.model.Coordinate;
import com.example.minefield.model.Empty;
import com.example.minefield.model.Field;
import com.example.minefield.model.FieldElement;
import com.example.minefield.model.FieldFactory;
import com.example.minefield.model.Mine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FieldPresenter {
    public FieldPresenter(int xSize, int ySize, int mineNumber) {
        FieldFactory factory = new FieldFactory();
        factory.setSizeX(xSize);
        factory.setSizeY(ySize);
        factory.setMineCount(mineNumber);
        field = factory.create();
    }

    public int getFieldSizeX() {
        return field.getFieldsX();
    }

    public int getFieldSizeY() {
        return field.getFieldsY();
    }

    public void save(OutputStream output) {
        try {
            field.save(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Double getPoints() {
        return field.getPoints();
    }

    public enum FieldDrawType {
        MINE,
        FLAGGED,
        COVERED,
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        OTHER;

        static FieldDrawType getNumberValue(int x){
            switch (x){
                case 0:
                    return ZERO;
                case 1:
                    return ONE;
                case 2:
                    return TWO;
                case 3:
                    return  THREE;
                case 4:
                    return  FOUR;
                case 5:
                    return FIVE;
                case 6:
                    return SIX;
                case 7:
                    return SEVEN;
                case 8:
                    return EIGHT;
            }
            throw new IllegalArgumentException();
        }

    }
    private Field field;

    public FieldPresenter(InputStream input) {
        field = new FieldFactory().load(input);
    }

    public FieldDrawType getField(int x, int y) {
        FieldElement fieldElement = field.getFieldElement(new Coordinate(x,y));
        if(fieldElement.isFlagged())
            return FieldDrawType.FLAGGED;
        else if(!fieldElement.isUncovered())
            return FieldDrawType.COVERED;
        else if(fieldElement instanceof Mine)
            return FieldDrawType.MINE;
        else if(fieldElement instanceof Empty){
            int mines = ((Empty)fieldElement).getNearbyMines();
            return FieldDrawType.getNumberValue(mines);
        }
        return FieldDrawType.OTHER;
    }

    public void uncoverField(int x, int y){
        field.uncoverField(new Coordinate(x ,y));
    }
    public void flagField(int x, int y) {
        field.flagField(new Coordinate(x,y));
    }
    public boolean hasEnded(){
        return field.hasEnded();
    }
}
