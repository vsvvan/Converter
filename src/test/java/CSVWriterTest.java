
import com.sytoss.trainee.lines.PersonLine;
import com.sytoss.trainee.reader.CSVReader;
import com.sytoss.trainee.writer.CSVWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class CSVWriterTest
{
    CSVWriter writer = new CSVWriter();
    @Test
    public void emptyFile() throws IOException {
        String filename = "src/test/resources/emptyfile.csv";
        List<PersonLine> lines = new ArrayList<>();
        writer.write(filename, lines);

        File resultFile = new File(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(resultFile))) {
            String[] expected = {};
            Scanner scanner = new Scanner(reader);
            int i = 0;
            while (scanner.hasNextLine()) {
                Assert.assertEquals(expected[i], scanner.nextLine());
                ++i;
            }
            Assert.assertEquals(i, expected.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void oneLineCount() throws IOException {
        String filename = "src/test/resources/csvOneLine.csv";
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
            expected.add("1,Artur,Kasumov,\"2001-08-23\",\"Комментарий/\"");

            Scanner scanner = new Scanner(reader);
            int i = 0;
            while (scanner.hasNextLine()) {

                ++i;
                scanner.nextLine();
            }
            Assert.assertEquals(i, expected.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void oneLine() throws IOException {
        String filename = "src/test/resources/csvOneLine.csv";
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
            expected.add("1,Artur,Kasumov,\"2001-08-23\",\"Комментарий/\"");

            Scanner scanner = new Scanner(reader);
            int i = 0;
            while (scanner.hasNextLine()) {
                Assert.assertEquals(expected.get(i), scanner.nextLine());
                ++i;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void someLines() throws IOException {
        String filename = "src/test/resources/csvOneLine.csv";
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
            expected.add("1,Artur,Kasumov,\"2001-08-23\",\"Комментарий/\"");

            Scanner scanner = new Scanner(reader);
            int i = 0;
            while (scanner.hasNextLine()) {
                Assert.assertEquals(expected.get(i), scanner.nextLine());
                ++i;
            }
            Assert.assertEquals(i, expected.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void emptyFileWrite() throws IOException {

        CSVReader reader = new CSVReader();
        List<PersonLine> persons = new ArrayList<>(reader.read("src/test/resources/empty.csv"));

        writer.write( "src/test/resources/empty.csv",persons);

        File file = new File("src/test/resources/empty.csv");
        assertEquals(file.length(), 0);
    }

    @Test
    public void oneLineFileWrite() throws IOException {

        CSVReader reader = new CSVReader();
        List<PersonLine> persons = new ArrayList<>(reader.read("src/test/resources/csvOneLine.csv"));

        writer.write("src/test/resources/csvOneLine.csv",persons);

        File file = new File("src/test/resources/csvOneLine.csv");
        assertEquals(file.length(), 55);

        List<PersonLine> personsNew = new ArrayList<>(reader.read("src/test/resources/csvOneLine.csv"));

        assertEquals(persons.size(), personsNew.size());
    }

    @Test
    public void linesFileWrite() throws IOException {
        CSVReader reader = new CSVReader();

        List<PersonLine> persons = new ArrayList<>(reader.read("src/test/resources/testfile1.csv"));

        writer.write("src/test/resources/testfile1.csv",persons);

        File file = new File("src/test/resources/testfile1.csv");
        assertEquals(file.length(), 165);

        List<PersonLine> personsNew = new ArrayList<>(reader.read("src/test/resources/testfile1.csv"));

        assertEquals(persons.size(), personsNew.size());
    }


}