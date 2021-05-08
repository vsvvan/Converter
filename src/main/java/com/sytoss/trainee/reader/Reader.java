package com.sytoss.trainee.reader;

import com.sytoss.trainee.PersonLine;

import java.util.List;

public interface Reader {
    List<PersonLine> read(String inputFilename);

    PersonLine processLine(String inputData);
}
