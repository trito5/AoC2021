package se.trito;

import java.util.Arrays;
import java.util.List;

import static se.trito.utils.FileReaderUtil.toStringList;

public class Dec05 {
    public static void run() {
        List<String> input = toStringList("files/05.txt");
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        int[][] matrix = new int[1000][1000];
        for (String s : input) {
            int[] row = splitRow(s);

            if (row[0] == row[2]) {
                addVerticals(matrix, row);
            } else if (row[1] == row[3]) {
                addHorizontals(matrix, row);
            }
        }
        int counter = countIntersections(matrix);
        System.out.println(counter);
    }

    private static void part2(List<String> input) {
        int[][] matrix = new int[1000][1000];
        for (String s : input) {
            int[] row = splitRow(s);

            if (row[0] == row[2]) {
                addVerticals(matrix, row);
            } else if (row[1] == row[3]) {
                addHorizontals(matrix, row);
            } else {
                addDiagonals(matrix, row);
            }
        }

        int counter = countIntersections(matrix);
        System.out.println(counter);
    }

    private static int[] splitRow(String s) {
        s = s.replaceAll(" ", "");
        s = s.replaceAll("->", ",");
        return Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    private static int countIntersections(int[][] matrix) {
        int counter = 0;
        for (int y = 0; y < 1000; y++) {
            for (int x = 0; x < 1000; x++) {
                if (matrix[y][x] > 1) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static void addDiagonals(int[][] matrix, int[] row) {

        if (row[0] < row[2] && row[1] < row[3]) {
            int it = row[2] - row[0];
            for (int i = 0; i <= it; i++) {
                int x = row[0] + i;
                int y = row[1] + i;
                matrix[y][x] = matrix[y][x] + 1;
            }
        }

        if (row[0] < row[2] && row[1] > row[3]) {
            int it = row[2] - row[0];
            for (int i = 0; i <= it; i++) {
                int x = row[0] + i;
                int y = row[1] - i;
                matrix[y][x] = matrix[y][x] + 1;
            }
        }

        if (row[0] > row[2] && row[1] > row[3]) {
            int it = row[0] - row[2];
            for (int i = 0; i <= it; i++) {
                int x = row[0] - i;
                int y = row[1] - i;
                matrix[y][x] = matrix[y][x] + 1;
            }
        }

        if (row[0] > row[2] && row[1] < row[3]) {
            int it = row[0] - row[2];
            for (int i = 0; i <= it; i++) {
                int x = row[0] - i;
                int y = row[1] + i;
                matrix[y][x] = matrix[y][x] + 1;
            }
        }
    }

    private static void addHorizontals(int[][] matrix, int[] row) {
        int y = row[1];
        int startValue = Math.min(row[0], row[2]);
        int stopValue = Math.max(row[0], row[2]);

        for (int x = startValue; x <= stopValue; x++) {
            matrix[y][x] = matrix[y][x] + 1;
        }
    }

    private static void addVerticals(int[][] matrix, int[] row) {
        int x = row[0];
        int startValue = Math.min(row[1], row[3]);
        int stopValue = Math.max(row[1], row[3]);

        for (int y = startValue; y <= stopValue; y++) {
            matrix[y][x] = matrix[y][x] + 1;
        }
    }
}
