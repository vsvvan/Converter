package com.sytoss.trainee.reader;

import com.sytoss.trainee.lines.PersonLine;

import java.util.List;

public interface Reader {
    List<PersonLine> read(String inputFilename);
}
