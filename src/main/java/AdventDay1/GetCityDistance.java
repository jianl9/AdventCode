package AdventDay1;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetCityDistance {

    // Take a list of string representing input instruction
    // print out answer
    // north - 0, east - 1, south - 2, west - 3;
    public static void move(List<String> steps) {
        int facing = 0;
        int xDistance = 0;
        int yDistance = 0;
        HashSet<Pair<Integer, Integer>> visited = new HashSet<>();
        boolean revisited = false;
        for (String step : steps) {
            int distance = Integer.parseInt(step.substring(1));
            int right = 1;
            if (step.startsWith("L")) {
                right = -1;
            }

            if (facing < 0) {
                facing += 4;
            }
            for (int i = 0; i < distance; i++) {
                if (!revisited && !visited.add(new Pair<>(xDistance, yDistance))) {
                    System.out.println("Part 2: " + (Math.abs(xDistance) + Math.abs(yDistance)));
                    revisited = true;
                }
                switch (facing % 4) {
                    case 0:
                        xDistance += right;
                        break;
                    case 1:
                        yDistance -= right;
                        break;
                    case 2:
                        xDistance -= right;
                        break;
                    case 3:
                        yDistance += right;
                        break;
                }
            }
            facing += right;

        }
        System.out.println("Part 1: " + (Math.abs(xDistance) + Math.abs(yDistance)));
    }

    // invoke readInput("path") and move(new List<String>("L1", "R2"....))
    public static void getDistance(String path) {
        List<String> steps = readInput(path);
        move(steps);
    }

    // read input instruction and process each line split by ", "
    // return a list of string
    public static List<String> readInput(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream
                    .map(str -> str.split(", "))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        getDistance("src/main/resources/day1Input.txt");
        // 246 124
    }
}
