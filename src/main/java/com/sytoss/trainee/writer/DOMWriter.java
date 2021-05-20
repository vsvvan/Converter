package com.sytoss.trainee.writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.sytoss.trainee.DataConverter;
import com.sytoss.trainee.lines.PersonLine;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class DOMWriter extends AbstractWriter {

    @Override
    public void write(String outputFilename, List<PersonLine> personLines) throws IOException {
        Document doc = new Document();
        doc.setRootElement(new Element("persons"));
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        for (PersonLine p : personLines) {
            Element person = new Element("person");
            person.setAttribute("id", p.getIDX());
            person.addContent(new Element("Name")
                    .addContent(new Element("FirstName").setText(p.getFirstName()))
                    .addContent(new Element("LastName").setText(p.getLastName())));
            person.addContent(new Element("Birthday").setAttribute("date", p.getBirthDate()));
            person.addContent(new Element("Comment").setText(p.getComment()));
            doc.getRootElement().addContent(person);
        }
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        try {
            xmlOutputter.output(doc, new FileOutputStream(outputFilename));
        } catch (IOException exception) {
            DataConverter.log.error("DOMWriter: Error while writing xml-file: \n\t- " + exception.getMessage());
            throw new IOException(exception.getMessage());
            //System.exit(-2);
        }
    }
}
