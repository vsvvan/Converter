package com.sytoss.trainee.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sytoss.trainee.DataConverter;
import com.sytoss.trainee.lines.PersonLine;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.sytoss.trainee.exception.XmlFormatException;


public class SAXReader extends AbstractReader {

    @Override
    public List<PersonLine> read(String inputFilename) throws IOException {

        List<PersonLine> lines = new ArrayList<>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            File inputFile = new File(inputFilename);
            if (!inputFile.exists()) {
                throw new FileNotFoundException("File " + inputFilename + " does not exists.");
            }
            xpp.setInput(new FileReader(inputFile));

            int eventType = 0;
            eventType = xpp.getEventType();
            StringBuilder line = new StringBuilder();
            String id = null;
            String firstName = null;
            String lastName = null;
            String birthdate = null;
            String comment = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("person")) {
                        id = xpp.getAttributeValue("", "id");
                    } else if (xpp.getName().equals("FirstName")) {
                        firstName = xpp.nextText();
                    } else if (xpp.getName().equals("LastName")) {
                        lastName = xpp.nextText();
                    } else if (xpp.getName().equals("Birthday")) {
                        birthdate = xpp.getAttributeValue("", "date");
                    } else if (xpp.getName().equals("Comment")) {
                        comment = xpp.nextText();
                    } else if (xpp.getName().equals("persons") | xpp.getName().equals("Name")) {

                    } else {
                        throw new XmlFormatException("Wrong xml-file structure");
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("person")) {
                        if (id != null && firstName != null && lastName != null && birthdate != null && comment != null) {
                            lines.add(new PersonLine(id, firstName, lastName, birthdate, comment));
                        } else {
                            throw new XmlFormatException("Wrong xml-file structure");
                        }
                        id = null;
                        firstName = null;
                        lastName = null;
                        birthdate = null;
                        comment = null;
                    }
                }
                eventType = xpp.next();
            }
        } catch (Exception exception) {

            DataConverter.log.error("SAXReader: Error while reading xml-file: \n\t- " + exception.getMessage());
            throw new IOException(exception.getMessage());
            //System.exit(-1);

        }

        return lines;
    }

}
