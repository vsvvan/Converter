package com.sytoss.trainee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class PersonLine extends Line {
    public static final Logger log = Logger.getLogger(PersonLine.class);
    private final int valuesCount = 5;

    private enum IdxForPersonLine {IDX, FIRSTNAME, LASTNAME, BIRTHDATE, COMMENT}

    public PersonLine() {
    }

    public PersonLine(String id, String firstName, String lastName, String birthday, String comment) {
        cells.add(id);
        cells.add(firstName);
        cells.add(lastName);
        cells.add(birthday);
        cells.add(comment);
    }

    @Override
    public void setCells(List<String> inputCells) {
        if (inputCells.size() == valuesCount) {
            cells = inputCells;
        } else {
            log.warn("Input Line has wrong cells count.");
        }
    }

    public String getIDX() {
        String personIdx = getCells().get(IdxForPersonLine.IDX.ordinal());
        return personIdx;
    }

    public String getFirstName() {
        String personFirstName = getCells().get(IdxForPersonLine.FIRSTNAME.ordinal());
        return personFirstName;
    }

    public String getLastName() {
        String personLastName = getCells().get(IdxForPersonLine.LASTNAME.ordinal());
        return personLastName;
    }

    /*public Date getBirthDate() {
        String personBirthDate = getCells().get(IdxForPersonLine.BIRTHDATE.ordinal());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return date.parse(personBirthDate);
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null;
        }
    }*/

    public String getBirthDate() {
        String personBirthDate = getCells().get(IdxForPersonLine.BIRTHDATE.ordinal());
        return personBirthDate;
    }

    public String getComment() {
        String personComment = getCells().get(IdxForPersonLine.COMMENT.ordinal());
        return personComment;
    }
}
