package com.sytoss.trainee.reader;

import com.sytoss.trainee.PersonLine;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DOMReader extends AbstractReader {
    private static final Logger log = Logger.getLogger(DOMReader.class);

    @Override
    public List<PersonLine> read(String inputFilename) {
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
                PersonLine person = new PersonLine(id, firstName, lastName, birthdate, comment);
                persons.add(person);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return persons;
    }


}
