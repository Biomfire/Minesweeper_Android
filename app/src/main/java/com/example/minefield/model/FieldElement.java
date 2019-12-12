package com.example.minefield.model;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

abstract public class FieldElement {
    protected Coordinate coordinate;
    protected Field field;

    public boolean isFlagged() {
        return isFlagged;
    }

    protected boolean isFlagged = false;
    protected boolean isUncovered = false;

    abstract void onUncover();

    public void setField(Field field) {
        this.field = field;
    }

    public FieldElement(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    abstract void uncoverEmpty();

    void toggleFlag() {
        if (!isUncovered)
            isFlagged = !isFlagged;
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public void setUncovered(boolean uncovered) {
        isUncovered = uncovered;
    }

    public void save(OutputStream output) throws IOException {
        output.write(Integer.toString(isUncovered() ? 1 : 0).getBytes(Charset.forName("UTF-8")));
        output.write(Integer.toString(isFlagged() ? 1 : 0).getBytes(Charset.forName("UTF-8")));
    }
}
