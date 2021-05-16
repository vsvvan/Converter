

import com.sytoss.trainee.lines.PersonLine;
import com.sytoss.trainee.reader.SAXReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SAXReaderTest
{
    private SAXReader reader = new SAXReader();
    @Test
    public void emptyFile() {
        List<PersonLine> lines = reader.read("src/test/resources/zeroLinesFile.xml");
        Assert.assertEquals(lines.size(), 0);
    }

    @Test
    public void oneLineFile() {
        List<PersonLine> lines = reader.read("src/test/resources/oneLineFile.xml");
        Assert.assertEquals(lines.size(), 1);
        PersonLine person = new PersonLine("1", "Artur", "Kasumov", "2001-08-23", "Комментарий/");
        Assert.assertEquals(lines.get(0).getIDX(), person.getIDX());
        Assert.assertEquals(lines.get(0).getFirstName(), person.getFirstName());
        Assert.assertEquals(lines.get(0).getLastName(), person.getLastName());
        Assert.assertEquals(lines.get(0).getBirthDate(), person.getBirthDate());
        Assert.assertEquals(lines.get(0).getComment(), person.getComment());
    }

    @Test
    public void linesFile() {
        List<PersonLine> lines = reader.read("src/test/resources/someLinesFile.xml");
        List<PersonLine> expected = new ArrayList<>();
        expected.add(new PersonLine("1", "Artur", "Kasumov", "2001-08-23", "Комментарий/"));
        expected.add(new PersonLine("2", "Lexa", "Ishchenko", "2002-08-04", "Кр\"ут\"ой, чел"));
        expected.add(new PersonLine("3", "Misha", "Rybalka", "2000-05-19", "Тоже неплох"));
        Assert.assertEquals( expected.size(),lines.size());
        for (int i = 0; i < lines.size(); i++) {
            Assert.assertEquals(lines.get(i).getIDX(), expected.get(i).getIDX());
            Assert.assertEquals(lines.get(i).getFirstName(), expected.get(i).getFirstName());
            Assert.assertEquals(lines.get(i).getLastName(), expected.get(i).getLastName());
            Assert.assertEquals(lines.get(i).getBirthDate(), expected.get(i).getBirthDate());
            Assert.assertEquals(lines.get(i).getComment(), expected.get(i).getComment());
        }
    }
}