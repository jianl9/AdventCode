package AdventDay4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SecurityThroughObscurityTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void validSectorID() {
        int sectorID = SecurityThroughObscurity.getSectorID("123[abxyz]");
        assertEquals(123, sectorID);
    }

    @Test
    public void validCheckSum() throws Exception {
        String checkSum = SecurityThroughObscurity.getCheckSum("[abxyz]");
        assertEquals("abxyz", checkSum);
    }

    @Test
    public void decryptChar() throws Exception {
        char realChar = SecurityThroughObscurity.shift('q', 343);
        assertEquals('v', realChar);
    }

    @Test
    public void decryptName() throws Exception {
        String realName = SecurityThroughObscurity.shift("qzmtzixmtkozyivhz", 343);
        assertEquals("veryencryptedname", realName);
    }

    @Test
    public void answer() {
        SecurityThroughObscurity.decoding("src/main/resources/day4Input.txt");
        assertEquals("Part 2: 482\n" +
                "Part 1: 361724\n", outContent.toString());
    }

}