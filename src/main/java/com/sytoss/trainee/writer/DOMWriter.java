package com.sytoss.trainee.writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.sytoss.trainee.PersonLine;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.apache.log4j.Logger;

public class DOMWriter extends AbstractWriter {
    private static final Logger log = Logger.getLogger(DOMWriter.class);

    @Override
    public void write(String outputFilename, List<PersonLine> personLines) {
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
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
