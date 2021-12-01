package se.trito;

import java.util.List;

import static se.trito.utils.FileReaderUtil.toIntList;

public class Dec01 {

    public static void run() {
        List<Integer> input = toIntList("files/01.txt");
        part1(input);
        part2(input);
    }

    private static void part1(List<Integer> input) {
        int meassure = Integer.MAX_VALUE;
        int counter = 0;
        for (int i : input) {
            if (i > meassure) {
                counter++;
            }
            meassure = i;
        }
        System.out.println(counter);
    }

    private static void part2(List<Integer> input) {
        int meassure = Integer.MAX_VALUE;
        int counter = 0;
        for (int i = 0; i < input.size() - 2; i++) {
            int temp = input.get(i) + input.get(i + 1) + input.get(i + 2);
            if (temp > meassure) {
                counter++;
            }
            meassure = temp;
        }
        System.out.println(counter);
    }


}
