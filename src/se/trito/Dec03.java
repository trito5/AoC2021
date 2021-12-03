package se.trito;

import java.util.ArrayList;
import java.util.List;

import static se.trito.utils.FileReaderUtil.toStringList;

public class Dec03 {

    public static void run() {
        List<String> input = toStringList("files/03.txt");
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        StringBuilder epsilon = new StringBuilder();
        StringBuilder gamma = new StringBuilder();
        for (int i = 0; i < input.get(0).length(); i ++) {
            int counter0 = 0;
            int counter1 = 0;
            for (String s : input) {
                if (s.charAt(i) == '0') {
                    counter0++;
                } else {
                    counter1++;
                }
            }
            if (counter0 > counter1) {
                epsilon.append(0);
                gamma.append(1);
            } else {
                epsilon.append(1);
                gamma.append(0);
            }
        }

        System.out.println(binToDec(gamma.toString()) * binToDec(epsilon.toString()));
    }

    private static void part2(List<String> input) {
        String ox = getOxygen(input);
        String co2 = getCo2(input);
        System.out.println(binToDec(ox) * binToDec(co2));
    }

    private static String getCo2(List<String> input) {
        List<String> co2 = new ArrayList<>(input);
        for (int i = 0; i < co2.get(0).length(); i ++) {
            List<String> co2Zeros = new ArrayList<>();
            List<String> co2Ones = new ArrayList<>();
            for (String s : co2) {
                if (s.charAt(i) == '0') {
                    co2Zeros.add(s);
                } else {
                    co2Ones.add(s);
                }
            }
            if (co2Zeros.size() > co2Ones.size()) {
                co2 = new ArrayList<>(co2Zeros);
            } else {
                co2 = new ArrayList<>(co2Ones);
            }
            if (co2.size() == 1) {
                break;
            }
        }
        return co2.get(0);
    }

    private static String getOxygen(List<String> input) {
        List<String> oxygen = new ArrayList<>(input);
        for (int i = 0; i < oxygen.get(0).length(); i ++) {
            List<String> oxygenZeros = new ArrayList<>();
            List<String> oxygenOnes = new ArrayList<>();
            for (String s : oxygen) {
                if (s.charAt(i) == '0') {
                    oxygenZeros.add(s);
                } else {
                    oxygenOnes.add(s);
                }
            }
            if (oxygenZeros.size() > oxygenOnes.size()) {
                oxygen = new ArrayList<>(oxygenOnes);
            } else {
                oxygen = new ArrayList<>(oxygenZeros);
            }
            if (oxygen.size() == 1) {
                break;
            }
        }
        return oxygen.get(0);
    }

    private static int binToDec(String binary) {
        return Integer.parseInt(binary, 2);
    }
}
