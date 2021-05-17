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

            reader = new CSVReader();
        }
        else if( FormatUtils.isXml(inputPath)) {

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

        log.info("Program is started");

        if(args.length < 2) {
            System.err.println("Wrong quantity of the arguments: \n\t- arguments should be filepath <csv | xml> or vise versa");
            System.exit(-3);
        }
        List<PersonLine> lines;

        DataConverter converter = new DataConverter();
        lines = converter.readLinesFromFile(args[0]);
        converter.writeLinesToFile(args[1], lines);

        log.info("Program process ended");

    }

}
