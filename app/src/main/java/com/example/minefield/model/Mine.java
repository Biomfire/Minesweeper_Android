package com.example.minefield.model;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

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
    void uncoverEmpty(){}

    @Override
    public void save(OutputStream output) throws IOException {
        output.write("M".getBytes(Charset.forName("UTF-8")));
        super.save(output);
    }
    static public Mine load(Coordinate coordinate, boolean isUncovered, boolean isFlagged){
        Mine mine = new Mine(coordinate);
        mine.isFlagged = isFlagged;
        mine.isUncovered = isUncovered;
        return mine;
    }
}
