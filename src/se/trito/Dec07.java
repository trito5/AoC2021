package se.trito;

import java.util.List;

import static se.trito.utils.FileReaderUtil.splitOnRegexToIntList;

public class Dec07 {
    public static void run() {
        List<Integer> input = splitOnRegexToIntList("files/07.txt", ",");
        part1(input);
        part2(input);
    }

    private static void part1(List<Integer> input) {
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < input.size(); i++) {
            int sum = 0;
            for (Integer fish : input) {
                sum += Math.abs(input.get(i) - fish);
            }
            if (sum < minSum) {
                minSum = sum;
            }
        }
        System.out.println(minSum);
    }

    private static void part2(List<Integer> input) {
        int minSum = Integer.MAX_VALUE;
        for (int i = 1; i <= 2000; i++) {
            int sum = 0;
            for (Integer fish : input) {
                sum += getDif(Math.abs(i - fish));
            }
            if (sum < minSum) {
                minSum = sum;
            }
        }
        System.out.println(minSum);
    }

    private static int getDif(int value) {
        int sum = 0;
        int counter = 0;
        for (int i = 1; i <= value; i++) {
            counter++;
            sum += counter;
        }
        return sum;
    }
}
