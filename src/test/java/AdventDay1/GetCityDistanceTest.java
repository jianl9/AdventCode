package AdventDay1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetCityDistanceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void readInput() {
        List<String> strings = GetCityDistance.readInput("src/main/resources/day1InputMock.txt");
        assertEquals(Arrays.asList("R2", "L3", "R2", "R4", "L2", "L1"), strings);
    }

    @Test
    public void move() {
        List<String> strings = GetCityDistance.readInput("src/main/resources/day1Input.txt");
        GetCityDistance.move(strings);
        assertEquals("Part 2: 124\n" +
                "Part 1: 246\n", outContent.toString());
    }

    @Test
    public void getDistance() {
        GetCityDistance.getDistance("src/main/resources/day1Input.txt");
        assertEquals("Part 2: 124\n" +
                "Part 1: 246\n", outContent.toString());
    }


}