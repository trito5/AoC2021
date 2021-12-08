package se.trito;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.sort;
import static se.trito.utils.FileReaderUtil.toStringList;

public class Dec08 {

    public static void run() {
        List<String> input = toStringList("files/08.txt");
        part1(input);
        part2(input);
    }

    public static void part1(List<String> input) {
        int counter = 0;
        for (String line : input) {
            String[] splitted = line.split("[|]");
            String[] output = splitted[1].trim().split(" ");

            for (String s : output) {
                if (s.length() == 2 || s.length() == 4 || s.length() == 3 || s.length() == 7) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    public static void part2(List<String> input) {
        int sum = 0;
        for (String line : input) {
            String[] splitted = line.split("[|]");
            String[] signals = splitted[0].trim().split(" ");
            String[] output = splitted[1].trim().split(" ");
            String[] sortedSignals = new String[10];

            sortUniqueSignals(signals, sortedSignals);

            for (String s : signals) {
                switch (s.length()) {
                    case 5 -> sort5LetterSignals(sortedSignals, s);
                    case 6 -> sort6LetterSignals(sortedSignals, s);
                }
            }

            StringBuilder s = new StringBuilder();
            for (String o : output) {
                determineNumber(sortedSignals, s, o);
            }
            sum += Integer.parseInt(s.toString());
        }
        System.out.println(sum);
    }

    private static void determineNumber(String[] sortedSignals, StringBuilder s, String o) {
        char[] outputSignal = o.toCharArray();
        Arrays.sort(outputSignal);

        for (int i = 0; i < sortedSignals.length; i++) {
            char[] inputSignal = sortedSignals[i].toCharArray();
            sort(inputSignal);

            if(Arrays.equals(outputSignal, inputSignal)) {
                s.append(i);
                break;
            }
        }
    }

    private static void sort6LetterSignals(String[] sortedSignals, String s) {
        int count4 = 0;
        int count1 = 0;
        for (char c : s.toCharArray()) {
            if (sortedSignals[4].contains(String.valueOf(c))) {
                count4++;
            }
            if (sortedSignals[1].contains(String.valueOf(c))) {
                count1++;
            }
        }
        if (count4 == 4) {
            sortedSignals[9] = s;
        } else if (count1 == 1) {
            sortedSignals[6] = s;
        } else {
            sortedSignals[0] = s;
        }
    }

    private static void sort5LetterSignals(String[] sortedSignals, String s) {
        int count4 = 0;
        int count1 = 0;
        for (char c : s.toCharArray()) {
            if (sortedSignals[4].contains(String.valueOf(c))) {
                count4++;
            }
            if (sortedSignals[1].contains(String.valueOf(c))) {
                count1++;
            }
        }
        if (count4 == 2) {
            sortedSignals[2] = s;
        } else if (count1 == 2) {
            sortedSignals[3] = s;
        } else {
            sortedSignals[5] = s;
        }
    }

    private static void sortUniqueSignals(String[] signals, String[] sortedSignals) {
        for (String s : signals) {
            switch (s.length()) {
                case 2 -> sortedSignals[1] = s;
                case 4 -> sortedSignals[4] = s;
                case 3 -> sortedSignals[7] = s;
                case 7 -> sortedSignals[8] = s;
            }
        }
    }
}
