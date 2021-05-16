package com.sytoss.trainee.writer;

import com.sytoss.trainee.lines.PersonLine;

import java.util.List;

public interface Writer {
    void write(String outputFilename, List<PersonLine> lines);
}
