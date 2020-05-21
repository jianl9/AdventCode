package AdventDay10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class BalanceBots {

    // process a line from input file
    // create Bot and put it into a map
    public static void handleLine(String[] split, Map<Integer, Bot> map) {
        if (split.length == 6) {
            Integer key = Integer.parseInt(split[5]);
            if (map.containsKey(key)) {
                map.get(key).takeChip(Integer.parseInt(split[1]));
            } else {
                Bot bot = new Bot(key, Integer.parseInt(split[1]));
                map.put(key, bot);
            }
        } else {
            int botID = Integer.parseInt(split[1]);
            int chipLow = Integer.parseInt(split[6]);
            int chipHigh = Integer.parseInt(split[11]);
            Bot newBot = map.getOrDefault(botID, new Bot(botID));
            if (split[5].equals("output")) {
                chipLow += 2000;
            }
            if (split[10].equals("output")) {
                chipHigh += 2000;
            }
            Bot botLow = map.getOrDefault(chipLow, new Bot(chipLow));
            Bot botHigh = map.getOrDefault(chipHigh, new Bot(chipHigh));
            map.putIfAbsent(botID, newBot);
            map.putIfAbsent(chipLow, botLow);
            map.putIfAbsent(chipHigh, botHigh);
            newBot.botLow = botLow;
            newBot.botHigh = botHigh;
        }
    }

    // read input file
    // invoke handleLine method for each line
    // start bots for processing chip
    // print out answer
    public static void handle() {
        Map<Integer, Bot> integerBotHashMap = new HashMap<>();
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/day10Input.txt"))) {
            stream
                    .map(str -> str.split(" "))
                    .forEach(line -> handleLine(line, integerBotHashMap));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // start transferring chip between bots until no bot has more than one chip
        while (integerBotHashMap.entrySet().stream().anyMatch(x -> x.getValue().chipCount == 2)) {
            integerBotHashMap.values().stream()
                    .filter(bot -> bot.chipCount == 2)
                    .forEach(Bot::giveChip);
        }

        // for part 2
        int sum = integerBotHashMap.values().stream()
                .filter(x -> x.id >= 2000 && x.id < 2003)
                .map(x -> x.chipHigh)
                .reduce(1, Math::multiplyExact);

        System.out.println("Part 2: the sum is " + sum);
        //Bot 118
        //sum = 143153
    }

    public static void main(String[] args) {
        handle();
    }

    static class Bot {
        int id;
        int chipLow;
        int chipHigh;
        int chipCount;
        Bot botLow;
        Bot botHigh;


        Bot(int id) {
            this.id = id;
        }

        Bot(int id, int chip) {
            this.id = id;
            this.chipHigh = chip;
            chipCount++;
        }

        // give low chip to low bot or output
        // give high chip to high bot or output
        // clear its record of chip
        void giveChip() {
            // For part 1
            if (chipLow == 17 && chipHigh == 61) {
                System.out.println("Part 1: Bot-" + id + " is responsible for comparing chip-17 and chip-61");
            }
            botLow.takeChip(this.chipLow);
            botHigh.takeChip(this.chipHigh);
            chipCount = 0;
        }

        // take chip and compare its value
        void takeChip(int chip) {
            if (chipCount >= 2) return;
            if (chipCount == 0) {
                chipHigh = chip;
            } else {
                if (chipLow == 0) {
                    if (chip < chipHigh) {
                        chipLow = chip;
                    } else {
                        chipLow = chipHigh;
                        chipHigh = chip;
                    }
                } else {
                    if (chip > chipLow) {
                        chipHigh = chip;
                    } else {
                        chipHigh = chipLow;
                        chipLow = chip;
                    }
                }
            }
            chipCount++;
        }
    }
}
