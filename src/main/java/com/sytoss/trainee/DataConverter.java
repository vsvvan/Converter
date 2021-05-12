package com.sytoss.trainee;

import java.util.List;

import com.sytoss.trainee.reader.DOMReader;
import com.sytoss.trainee.reader.SAXReader;
import com.sytoss.trainee.writer.CSVWriter;
import org.apache.log4j.Logger;

public class DataConverter {
    public static final Logger log = Logger.getLogger(DataConverter.class);

    public static void main(String[] args) {
        List<PersonLine> lines;


        String filename = "src/main/resources/testfile1.xml";
        SAXReader saxReader = new SAXReader();

        lines = saxReader.read(filename);
        CSVWriter writer = new CSVWriter();
        writer.write("src/main/resources/testfileOutputCSV.csv", lines);

    }

}
