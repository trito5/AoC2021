package se.trito;

import java.util.List;

import static se.trito.utils.FileReaderUtil.toStringList;

public class Dec02 {

    public static void run() {
        List<String> input = toStringList("files/02.txt");
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        int distance = 0;
        int depth = 0;
        for (String s : input) {
            String[] dir = s.split(" ");
            switch (dir[0]) {
                case ("forward") -> distance += Integer.parseInt(dir[1]);
                case ("down") -> depth += Integer.parseInt(dir[1]);
                case ("up") -> depth -= Integer.parseInt(dir[1]);
            }
        }
        System.out.println(depth * distance);
    }

    private static void part2(List<String> input) {
        int distance = 0;
        int depth = 0;
        int aim = 0;
        for (String s : input) {
            String[] dir = s.split(" ");
            switch (dir[0]) {
                case ("forward") -> {
                    distance += Integer.parseInt(dir[1]);
                    depth += aim * Integer.parseInt(dir[1]);
                }
                case ("down") -> aim += Integer.parseInt(dir[1]);
                case ("up") -> aim -= Integer.parseInt(dir[1]);
            }
        }
        System.out.println(depth * distance);
    }


}
