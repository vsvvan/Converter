import com.sytoss.trainee.reader.CSVReader;
import com.sytoss.trainee.lines.PersonLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class CSVReaderTest {
    CSVReader csvReader = new CSVReader();

    @Test
    public void openFile() throws IOException {
        csvReader.read("src/test/resources/emptyfile.csv");
    }

    /*@Test(expected = FileNotFoundException.class)
    public void fileNotFound() throws IOException {
        csvReader.read("src/test/resources/emptyfile1.csv");
    }*/

    @Test
    public void emptyFile() throws IOException {
        List<PersonLine> lines = csvReader.read("src/test/resources/emptyfile.csv");
        Assert.assertEquals(lines.size(), 0);
    }

    @Test
    public void normalFormatFile() throws IOException {
        List<PersonLine> lines = csvReader.read("src/test/resources/oneLineFile.csv");
        Assert.assertEquals(lines.get(0).getCells().get(0), "111");
        Assert.assertEquals(lines.get(0).getCells().get(1), "222");
        Assert.assertEquals(lines.get(0).getCells().get(2), "333");
    }

    @Test
    public void fileWithExtraSymbols() throws IOException {
        List<String> expected = new ArrayList<>();
        expected.add("1 Artur Kasumov 2001-08-23 Комментарий/");
        expected.add("2 Lexa Ishchenko 2002-08-04 Кр\"ут\"ой, чел");
        expected.add("3 Misha Rybalka 2000-05-19 Тоже неплох");

        List<PersonLine> actual = csvReader.read("src/test/resources/testfile1.csv");
        Assert.assertEquals(actual.size(), expected.size());
        for (int i = 0; i < actual.size(); ++i) {
            Assert.assertEquals(String.join(" ", actual.get(i).getCells()), expected.get(i));
        }
    }

    /*@Test
    public void standardLineSize() {


        List<String> line = new ArrayList<String>(csvReader.parserLine("1,Leha,Dark,\"1800-05-05\",Vampir"));

        assertEquals(line.size(), 5);
    }

    @Test
    public void standardLine() {
        csvParser = new CsvParser();

        List<String> line = new ArrayList<String>(csvParser.parserLine("1,Leha,Dark,1990,Vampir"));

        String[] test = new String[]{"1", "Leha", "Dark", "1990", "Vampir"};

        for (int i = 0; i < line.size(); i++) {
            assertEquals(line.get(i), test[i]);
        }
    }

    @Test
    public void notStandardLine() {
        csvParser = new CsvParser();

        List<String> line = new ArrayList<String>(csvParser.parserLine("1,Leha,Dark,\"1800-05-05\",Vampir"));

        String[] test = new String[]{"1", "Leha", "Dark", "1800-05-05", "Vampir"};

        for (int i = 0; i < line.size(); i++) {
            assertEquals(line.get(i), test[i]);
        }
    }

    @Test
    public void quotedString() {
        csvParser = new CsvParser();

        List<String> line = new ArrayList<String>(csvParser.parserLine("1,Leha,Dark,\"1800-05-05\",\"\"\"Ne\"\"Vampir\""));

        String[] test = new String[]{"1", "Leha", "Dark", "1800-05-05", "\"Ne\"Vampir"};

        for (int i = 0; i < line.size(); i++) {
            assertEquals(line.get(i), test[i]);
        }
    }

    @Test
    public void oneQuotedString() {
        csvParser = new CsvParser();

        List<String> line = new ArrayList<String>(csvParser.parserLine("1,Leha,Dark,\"1800-05-05\",\"Ne\"\"Vampir\""));

        String[] test = new String[]{"1", "Leha", "Dark", "1800-05-05", "Ne\"Vampir"};

        for (int i = 0; i < line.size(); i++) {
            assertEquals(line.get(i), test[i]);
        }
    }

    @Test
    public void alienString() {
        csvParser = new CsvParser();

        List<String> line = new ArrayList<String>(csvParser.parserLine("1,Leha,Dark,\"1800-05-05\",\"Ne,Vampir\""));

        String[] test = new String[]{"1", "Leha", "Dark", "1800-05-05", "Ne,Vampir"};

        for (int i = 0; i < line.size(); i++) {
            assertEquals(line.get(i), test[i]);
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void notFoundFileRead() throws FileNotFoundException {
        csvParser = new CsvParser();

        List<PersonLine> persons = new ArrayList<PersonLine>(csvParser.read("src/test/resources/not.csv"));

        FileReader fr = new FileReader("src/test/resources/not.csv");
    }

    @Test
    public void emptyFileRead() {
        csvParser = new CsvParser();

        List<PersonLine> persons = new ArrayList<PersonLine>(csvParser.read("src/test/resources/empty.csv"));

        assertEquals(persons.size(), 0);
    }

    @Test
    public void oneLineFileRead() {
        csvParser = new CsvParser();

        List<PersonLine> persons = new ArrayList<PersonLine>(csvParser.read("src/test/resources/line.csv"));

        assertEquals(persons.size(), 1);
    }

    @Test
    public void linesFileRead() {
        csvParser = new CsvParser();

        List<PersonLine> persons = new ArrayList<PersonLine>(csvParser.read("src/test/resources/test.csv"));

        assertEquals(persons.size(), 5);
    }*/
}