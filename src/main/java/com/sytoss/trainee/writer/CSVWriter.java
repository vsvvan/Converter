package com.sytoss.trainee.writer;

import com.sytoss.trainee.DataConverter;
import com.sytoss.trainee.lines.PersonLine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVWriter extends AbstractWriter {

    @Override
    public void write(String outputFilename, List<PersonLine> lines) throws IOException {
        File outputFile = new File(outputFilename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (PersonLine person : lines) {
                writer.write(person.getIDX());
                writer.write(",");
                writer.write(person.getFirstName());
                writer.write(",");
                writer.write(person.getLastName());
                writer.write(",");
                writer.write("\"");
                writer.write(person.getBirthDate());
                writer.write("\"");
                writer.write(",");
                writer.write(format(person.getComment()));
                writer.write("\n");
            }
        } catch (IOException exception) {
            DataConverter.log.error("CSVWriter: Error while writing xml-file: \n\t- " + exception.getMessage());
            throw new IOException(exception.getMessage());
            //System.exit(-2);
        }
    }

    private String format(String value) {
        Pattern extraSymbolPattern = Pattern.compile("[ /.=\"\\-;:(){}\\[\\]]");
        Matcher extraSymbolMatcher = extraSymbolPattern.matcher(value);

        if (extraSymbolMatcher.find()) {
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            int quoteIdx = value.indexOf('\"');

            if (quoteIdx != -1) {
                for (int i = 0; i < value.length(); ++i) {
                    char curChar = value.charAt(i);
                    sb.append(curChar);
                    if (curChar == '\"') {
                        sb.append('\"');
                    }
                }
            } else {
                sb.append(value);
            }
            sb.append("\"");
            return sb.toString();
        }
        return value;
    }
}
