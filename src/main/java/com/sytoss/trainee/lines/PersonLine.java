package com.sytoss.trainee.lines;

import com.sytoss.trainee.DataConverter;
import com.sytoss.trainee.utils.ValidationUtils;

import java.util.List;

public class PersonLine extends Line {

    private final int valuesCount = 5;

    private enum IdxForPersonLine {IDX, FIRSTNAME, LASTNAME, BIRTHDATE, COMMENT}

    public PersonLine() {
    }

    public PersonLine(String id, String firstName, String lastName, String birthday, String comment) {

        boolean isValidPerson = true;

        if(ValidationUtils.isValidId(id))
            cells.add(id);
        else {
            DataConverter.log.error("Error while creating Person: \n\t- wrong id format, id has to be numeric");
            isValidPerson = false;
        }

        if(ValidationUtils.isValidName(firstName))
            cells.add(firstName);
        else {
            DataConverter.log.error("Error while creating Person: \n\t- wrong Name format, Name should consist of letters");
            isValidPerson = false;
        }

        if(ValidationUtils.isValidName(lastName))
            cells.add(lastName);
        else {
            DataConverter.log.error("Error while creating Person: \n\t- wrong LastName format, LastName should consist of letters");
            isValidPerson = false;
        }

        if(ValidationUtils.isValidDate(birthday))
            cells.add(birthday);
        else {
            DataConverter.log.error("Error while creating Person: \n\t- wrong birthday format, birthday has to be yyyy-mm-dd");
            isValidPerson = false;
        }
        cells.add(comment);

        if(!isValidPerson) {
            System.err.println("Person values aren't valid");
        }
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
