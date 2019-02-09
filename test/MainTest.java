import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void convertInputToLocalDateTime() {
        String date = "[2018-01-21 12:00:00] - Abertura da Porta OK";

        Main main = new Main();
        LocalTime localTime = main.validaEntrada(date);

        assertEquals(localTime, LocalTime.of(12,00,00));
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldReturnZeroWhenTimeIsInvalidFormatter() {

        String date = "[2018-01-21 123:00:00] - Abertura da Porta OK";

        Main main = new Main();
        main.validaEntrada(date);
    }

    @Test
    public void shouldReturnOneWhenHasTimeBetween10and16() {
        String date = "[2018-01-21 12:00:00] - Abertura da Porta OK";

        Main main = new Main();
        int quant = main.countTimesOfBank(date);

        assertEquals(quant, 1);
    }

    @Test
    public void shouldReturnZeroWhenHasNotTimeBetween10and16() {
        String date = "[2018-01-21 18:00:00] - Abertura da Porta OK";

        Main main = new Main();
        int quant = main.countTimesOfBank(date);

        assertEquals(quant, 0);
    }
}