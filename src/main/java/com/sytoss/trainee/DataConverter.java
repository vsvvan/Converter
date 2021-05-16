package com.sytoss.trainee;

import java.io.File;
import java.util.List;

import com.sytoss.trainee.lines.PersonLine;
import com.sytoss.trainee.reader.CSVReader;
import com.sytoss.trainee.reader.DOMReader;
import com.sytoss.trainee.reader.Reader;
import com.sytoss.trainee.reader.SAXReader;
import com.sytoss.trainee.utils.FormatUtils;
import com.sytoss.trainee.writer.CSVWriter;
import com.sytoss.trainee.writer.DOMWriter;
import com.sytoss.trainee.writer.SAXWriter;
import com.sytoss.trainee.writer.Writer;
import org.apache.log4j.Logger;

public class DataConverter {

    public static final Logger log = Logger.getLogger(DataConverter.class);

    private Reader reader;
    private Writer writer;

    public List<PersonLine> readLinesFromFile(String inputPath) {

        if(FormatUtils.isCsv(inputPath)) {
            //csv and xml
            reader = new CSVReader();
        }
        else if( FormatUtils.isXml(inputPath)) {

            //xml and csv
            File file = new File(inputPath);
            double bytes = file.length();
            bytes = bytes / 1024;
            reader = (bytes < 20d) ? new DOMReader() : new SAXReader();

        }
        else {
            log.error("Invalid file format or wrong usage");
            return null;
        }

        return reader.read(inputPath);

    }

    public void writeLinesToFile(String outputPath, List<PersonLine> persons) {

        if(FormatUtils.isCsv(outputPath)) {
            writer = new CSVWriter();
        }
        else if(FormatUtils.isXml(outputPath)) {
            writer = (persons.size() < 20) ? new DOMWriter() : new SAXWriter();
        }
        else {
            log.error("DataConverter can't use following type of file. . .");
        }

        writer.write(outputPath, persons);
    }

    public static void main(String[] args) {

        String xmlIn = "src/main/resources/testfile1.xml";
        String csvOut = "src/main/resources/testfileOutputCSV.csv";
        String csvIn = "src/main/resources/testfile1.csv";
        String xmlOut = "src/main/resources/saxtestfile.xml";

        log.info("Program is started");
        List<PersonLine> lines;

        DataConverter converter = new DataConverter();
        //from xml to csv
        lines = converter.readLinesFromFile(xmlIn);
        converter.writeLinesToFile(csvOut, lines);

        //form csv to xml
        lines = converter.readLinesFromFile(csvIn);
        converter.writeLinesToFile(xmlOut, lines);

        log.info("Program process ended");

    }

}
