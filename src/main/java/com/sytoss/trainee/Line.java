package com.sytoss.trainee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Line {
    List<String> cells = new ArrayList<>();

    public void setCells(List<String> inputCells) {
        cells = inputCells;
    }

    public List<String> getCells() {
        return Collections.unmodifiableList(cells);
    }

    public void addCell(String value) {
        cells.add(value);
    }
}
