package com.sytoss.trainee.writer;

import javax.xml.stream.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.io.IOException;

import com.sytoss.trainee.lines.PersonLine;
import org.apache.log4j.Logger;

public class SAXWriter extends AbstractWriter {
    public static final Logger log = Logger.getLogger(SAXWriter.class);

    @Override
    public void write(String outputFilename, List<PersonLine> lines) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = null;
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        try (FileWriter fileWriter = new FileWriter(outputFilename)) {
            writer = factory.createXMLStreamWriter(fileWriter);
            writer.writeStartDocument();

            writer.writeCharacters("\n");
            writer.writeStartElement("persons");
            writer.writeCharacters("\n  ");
            for (PersonLine p : lines) {
                writer.writeStartElement("person");
                writer.writeAttribute("id", p.getIDX());
                writer.writeCharacters("\n    ");

                writer.writeStartElement("Name");
                writer.writeCharacters("\n      ");

                writer.writeStartElement("FirstName");
                writer.writeCharacters(p.getFirstName());
                writer.writeEndElement();
                writer.writeCharacters("\n      ");

                writer.writeStartElement("LastName");
                writer.writeCharacters(p.getLastName());
                writer.writeEndElement();
                writer.writeCharacters("\n    ");

                writer.writeEndElement();
                writer.writeCharacters("\n    ");

                writer.writeEmptyElement("Birthday");
                writer.writeAttribute("date", p.getBirthDate());
                writer.writeCharacters("\n    ");

                writer.writeStartElement("Comment");
                writer.writeCharacters(p.getComment());
                writer.writeEndElement();
                writer.writeCharacters("\n  ");

                writer.writeEndElement();
                if (lines.get(lines.size() - 1) != p) {
                    writer.writeCharacters("\n  ");
                } else {
                    writer.writeCharacters("\n");
                }
            }
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();
        } catch (XMLStreamException | IOException e) {
            log.error(e.getMessage());
        }
    }

}
