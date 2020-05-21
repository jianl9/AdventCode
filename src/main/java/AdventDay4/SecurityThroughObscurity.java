package AdventDay4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SecurityThroughObscurity {
    public static void decoding(String path) {
        int sectionIDSum = 0;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            sectionIDSum = stream
                    .map(SecurityThroughObscurity::checkSum)
                    .reduce(0, Integer::sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Part 1: " + sectionIDSum);
    }

    public static int checkSum(String line) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        String[] split = line.split("-");
        // count character and its appearance and put them into a map
        Arrays.stream(split, 0, split.length - 1)
                .forEach(str -> str.chars()
                        .forEach(x ->
                                freqMap.put((char) x,
                                        freqMap.getOrDefault((char) x, 0) + 1)));

        String curSum = freqMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .map(x -> Character.toString(x))
                .collect(Collectors.joining(""));

        // get real room and sum up valid sector ID
        if (curSum.equals(getCheckSum(line))) {
            String name = String.join("",
                    Arrays.copyOf(split, split.length - 2));

            int sectorID = getSectorID(split[split.length - 1]);
            if (shift(name, sectorID).equals("northpoleobject")) {
                System.out.println("Part 2: " + sectorID);
            }
            return getSectorID(split[split.length - 1]);
        }
        return 0;
    }

    // input format: "sectorID[checkSum]"
    // return: sectorID in integer
    public static int getSectorID(String s) {
        return Integer.parseInt(s.substring(0, s.indexOf("[")));
    }

    // input format: "[checkSum]"
    // return: "checkSum"
    public static String getCheckSum(String s) {
        return s.substring(s.indexOf("[") + 1, s.length() - 1);
    }

    // input format: String s - "encryptedName"
    // return "decryptedName"
    public static String shift(String s, int sectorID) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, s.length())
                .mapToObj(i -> (char) i)
                .map(i -> shift(s.charAt(i), sectorID))
                .forEach(sb::append);
        return new String(sb);
    }

    // decode Character
    public static char shift(char c, int rotate) {
        return (char) ((((c - 'a') + rotate) % 26) + 'a');
    }

    public static void main(String[] args) {
        decoding("src/main/resources/day4Input.txt"); // 361724 482
    }
}
