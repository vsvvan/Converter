package com.sytoss.trainee.lines;

import com.sytoss.trainee.DataConverter;
import com.sytoss.trainee.utils.Validation;

import java.util.List;

public class PersonLine extends Line {

    private final int valuesCount = 5;

    private enum IdxForPersonLine {IDX, FIRSTNAME, LASTNAME, BIRTHDATE, COMMENT}

    public PersonLine() {
    }

    public PersonLine(String id, String firstName, String lastName, String birthday, String comment) {
        if(Validation.isValidId(id))
            cells.add(id);
        else
            DataConverter.log.error("Error while creating Person: \t- wrong id format, id has to be numeric");

        if(Validation.isValidName(firstName))
            cells.add(firstName);
        else
            DataConverter.log.error("Error while creating Person: \t- wrong Name format, Name should consist of letters");

        if(Validation.isValidName(lastName))
            cells.add(lastName);
        else
            DataConverter.log.error("Error while creating Person: \t- wrong LastName format, LastName should consist of letters");

        if(Validation.isValidDate(birthday))
            cells.add(birthday);
        else
            DataConverter.log.error("Error while creating Person: \t- wrong birthday format, birthday has to be yyyy-mm-dd");

        cells.add(comment);
    }


    @Override
    public void setCells(List<String> inputCells) {
        if (inputCells.size() == valuesCount) {
            cells = inputCells;
        } else {
            DataConverter.log.warn("Input Line has wrong cells count.");
        }
    }

    public String getIDX() {
        return getCells().get(IdxForPersonLine.IDX.ordinal());
    }

    public String getFirstName() {
        return getCells().get(IdxForPersonLine.FIRSTNAME.ordinal());
    }

    public String getLastName() {
        return getCells().get(IdxForPersonLine.LASTNAME.ordinal());
    }

    public String getBirthDate() {
        return getCells().get(IdxForPersonLine.BIRTHDATE.ordinal());
    }

    public String getComment() {
        return getCells().get(IdxForPersonLine.COMMENT.ordinal());
    }
}
