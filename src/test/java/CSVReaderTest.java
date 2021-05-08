import com.sytoss.trainee.reader.CSVReader;
import com.sytoss.trainee.PersonLine;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CSVReaderTest {
    CSVReader csvReader = new CSVReader();

    @Test
    public void openFile() {
        csvReader.read("src/test/resources/emptyfile.csv");
    }

    /*@Test(expected = FileNotFoundException.class)
    public void fileNotFound() throws IOException {
        csvReader.read("src/test/resources/emptyfile1.csv");
    }*/

    @Test
    public void emptyFile() {
        List<PersonLine> lines = csvReader.read("src/test/resources/emptyfile.csv");
        Assert.assertEquals(lines.size(), 0);
    }

    @Test
    public void normalFormatFile() {
        List<PersonLine> lines = csvReader.read("src/test/resources/oneLineFile.csv");
        Assert.assertEquals(lines.get(0).getCells().get(0), "111");
        Assert.assertEquals(lines.get(0).getCells().get(1), "222");
        Assert.assertEquals(lines.get(0).getCells().get(2), "333");
    }

    @Test
    public void fileWithExtraSymbols() {
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
}