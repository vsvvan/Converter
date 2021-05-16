import com.sytoss.trainee.writer.DOMWriter;
import com.sytoss.trainee.lines.PersonLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DOMWriterTest {
    DOMWriter writer = new DOMWriter();

    @Test
    public void zeroLines() throws IOException {
        String filename = "src/test/resources/zeroLinesFile.xml";

        List<PersonLine> lines = new ArrayList<>();
        writer.write(filename, lines);

        File resultFile = new File(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(resultFile))) {
            String[] expected = {"<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
                    "<persons />"};
            Scanner scanner = new Scanner(reader);
            int i = 0;
            while (scanner.hasNextLine()) {
                Assert.assertEquals(expected[i], scanner.nextLine());
                ++i;
            }
            Assert.assertEquals(i, expected.length);
        }

    }

    @Test
    public void someLines() {
        String filename = "src/test/resources/oneLineFile.xml";

        List<PersonLine> lines = new ArrayList<>();
        PersonLine line = new PersonLine();
        ArrayList<String> person = new ArrayList<>();
        person.add("1");
        person.add("Artur");
        person.add("Kasumov");
        person.add("2001-08-23");
        person.add("Комментарий/");
        line.setCells(person);
        lines.add(line);
        writer.write(filename, lines);

        File resultFile = new File(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(resultFile))) {
            List<String> expected = new ArrayList<>();
            expected.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            expected.add("<persons>");
            expected.add("  <person id=\"1\">");
            expected.add("    <Name>");
            expected.add("      <FirstName>Artur</FirstName>");
            expected.add("      <LastName>Kasumov</LastName>");
            expected.add("    </Name>");
            expected.add("    <Birthday date=\"2001-08-23\" />");
            expected.add("    <Comment>Комментарий/</Comment>");
            expected.add("  </person>");
            expected.add("</persons>");
            Scanner scanner = new Scanner(reader);
            int i = 0;
            while (scanner.hasNextLine()) {
                Assert.assertEquals(expected.get(i), scanner.nextLine());
                ++i;
            }
            Assert.assertEquals(i, expected.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}