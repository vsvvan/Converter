import com.sytoss.trainee.PersonLine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonLineTest {
    PersonLine line = new PersonLine();

    @Test
    public void notEqualsCellsCount() {
        List<String> cells = new ArrayList<>();
        cells.add("1");
        cells.add("Misha");
        cells.add("Rybalka");
        cells.add("dfkj");
        cells.add("Comment");
        line.setCells(cells);
    }
}