package com.sytoss.trainee;

import java.util.List;

import com.sytoss.trainee.reader.DOMReader;
import org.apache.log4j.Logger;

public class DataConverter {
    public static final Logger log = Logger.getLogger(DataConverter.class);

    public static void main(String[] args) {
        List<PersonLine> lines;

        //String filename = "src/main/resources/testfile1.csv";
        //com.sytoss.trainee.reader.CSVReader csvReader = new com.sytoss.trainee.reader.CSVReader();
        String filename = "src/main/resources/testfile1.xml";
        DOMReader domReader = new DOMReader();
        //lines = csvReader.read(filename);
        lines = domReader.read(filename);

        //  com.sytoss.trainee.writer.DOMWriter writer = new com.sytoss.trainee.writer.DOMWriter();
        // com.sytoss.trainee.writer.SAXWriter writer = new com.sytoss.trainee.writer.SAXWriter();
        //String outputFilename = "src/main/resources/saxtestfile.xml";
        //writer.write(outputFilename,lines);
        for (int i = 0; i < lines.size(); i++) {
            System.out.print(lines.get(i).getIDX() + ", ");
            System.out.print(lines.get(i).getFirstName() + ", ");
            System.out.print(lines.get(i).getLastName() + ", ");
            System.out.print(lines.get(i).getBirthDate() + ", ");
            System.out.println(lines.get(i).getComment());
        }

    }

}
