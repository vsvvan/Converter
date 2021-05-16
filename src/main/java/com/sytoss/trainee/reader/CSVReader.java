package com.sytoss.trainee.reader;

import com.sytoss.trainee.lines.PersonLine;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader extends AbstractReader {
    private static final Logger log = Logger.getLogger(CSVReader.class);

    @Override
    public List<PersonLine> read(String inputFilename) {
        List<PersonLine> lines = new ArrayList<>();
        File inputFile = new File(inputFilename);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(processLine(line));
            }
        } catch (IOException e) {
            log.fatal(e.getMessage());
        }
        return lines;
    }

    public PersonLine processLine(String inputData) {
        boolean isQuoteOpened = false;
        StringBuilder builder = new StringBuilder();
        PersonLine line = new PersonLine();
        int idx = 0;
        while (idx < inputData.length()) {

            if (inputData.charAt(idx) == '"') {

                if (idx < inputData.length() - 1 && inputData.charAt(idx + 1) == '"') {
                    builder.append(inputData.charAt(idx));
                    ++idx;
                } else {
                    isQuoteOpened = !isQuoteOpened;
                }

            } else if (inputData.charAt(idx) == ',') {
                if (isQuoteOpened) {
                    builder.append(inputData.charAt(idx));
                } else {
                    line.addCell(builder.toString());
                    builder.delete(0, builder.length());
                }

            } else {
                builder.append(inputData.charAt(idx));
            }
            ++idx;
        }
        line.addCell(builder.toString());
        return line;
    }
}
