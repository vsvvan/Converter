package com.sytoss.trainee.reader;

import com.sytoss.trainee.DataConverter;
import com.sytoss.trainee.lines.PersonLine;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMReader extends AbstractReader {

    @Override
    public List<PersonLine> read(String inputFilename) throws IOException {
        List<PersonLine> persons = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;
            documentBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = documentBuilder.parse(new File(inputFilename));
            DOMBuilder domBuilder = new DOMBuilder();

            Document jdomDocument = domBuilder.build(doc);
            Element root = jdomDocument.getRootElement();
            List<Element> personListElements = root.getChildren("person");

            String id;
            String firstName;
            String lastName;
            String birthdate;
            String comment;

            for (Element personEl : personListElements) {

                id = personEl.getAttributeValue("id");
                List<Element> name = personEl.getChildren("Name");
                firstName = name.get(0).getChildText("FirstName");
                lastName = name.get(0).getChildText("LastName");
                List<Element> birthday = personEl.getChildren("Birthday");
                birthdate = birthday.get(0).getAttributeValue("date");
                comment = personEl.getChildText("Comment");

                persons.add(new PersonLine(id, firstName, lastName, birthdate, comment));
            }

        } catch (Exception exception) {
            DataConverter.log.error("DOMReader: Error while reading xml-file: \n\t- " + exception.getMessage());
            throw new IOException(exception.getMessage());
            //System.exit(-1);
        }
        return persons;
    }


}
