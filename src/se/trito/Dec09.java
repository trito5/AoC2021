package se.trito;

import java.util.List;

import static se.trito.utils.FileReaderUtil.toStringList;

public class Dec09 {

    public static void run() {
        List<String> input = toStringList("files/09.txt");
        part1(input);
        //part2(input);
    }

    public static void part1(List<String> input) {
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                boolean lowPoint = true;
                int number = Integer.parseInt(input.get(i).substring(j, j + 1));

                //check up
                if (i > 0) {
                    int ad = Integer.parseInt(input.get(i - 1).substring(j, j + 1));
                    lowPoint = isLowPoint(lowPoint, number, ad);
                }

                //check right
                if (j < input.get(i).length() - 1) {
                    int ad = Integer.parseInt(input.get(i).substring(j + 1, j + 2));
                    lowPoint = isLowPoint(lowPoint, number, ad);
                }
                //check down
                if (i < input.size() - 1) {
                    int ad = Integer.parseInt(input.get(i + 1).substring(j, j + 1));
                    lowPoint = isLowPoint(lowPoint, number, ad);
                }

                //check left
                if (j > 0) {
                    int ad = Integer.parseInt(input.get(i).substring(j - 1, j));
                    lowPoint = isLowPoint(lowPoint, number, ad);
                }

                if (lowPoint) {
                    sum = sum + number + 1;
                }
            }
        }
        System.out.println(sum);
    }

    private static boolean isLowPoint(boolean lowPoint, int number, int ad) {
        if (ad <= number) {
            lowPoint = false;
        }
        return lowPoint;
    }
}
