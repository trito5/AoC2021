package se.trito;

import se.trito.utils.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static se.trito.utils.FileReaderUtil.toStringList;

public class Dec09 {

    public static void run() {
        List<String> input = toStringList("files/09.txt");
        part1(input);
        part2(input);
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

    public static void part2(List<String> input) {
        List<Node> nodes = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                int number = Integer.parseInt(input.get(i).substring(j, j + 1));
                Node n = new Node(i, j, number);
                nodes.add(n);
            }
        }

        List<Integer> sums = new ArrayList<>();
        for (Node n : nodes) {
            queue.add(n);
            int sum = 0;
            while (!queue.isEmpty()) {
                Node check = queue.poll();
                if (check.c != 9 && check.c != -1) {
                    sum++;
                    check.c = -1;
                    //add left
                    int i = nodes.indexOf(new Node(check.x - 1, check.y));
                    if (i != -1) {
                        queue.add(nodes.get(i));
                    }
                    //add right
                    i = nodes.indexOf(new Node(check.x + 1, check.y));
                    if (i != -1) {
                        queue.add(nodes.get(i));
                    }
                    //add up
                    i = nodes.indexOf(new Node(check.x, check.y - 1));
                    if (i != -1) {
                        queue.add(nodes.get(i));
                    }
                    //add down
                    i = nodes.indexOf(new Node(check.x, check.y + 1));
                    if (i != -1) {
                        queue.add(nodes.get(i));
                    }
                }
            }
            sums.add(sum);
        }
        Collections.sort(sums, Collections.reverseOrder());
        System.out.println(sums.get(0) * sums.get(1) * sums.get(2));
    }

    private static boolean isLowPoint(boolean lowPoint, int number, int ad) {
        if (ad <= number) {
            lowPoint = false;
        }
        return lowPoint;
    }
}
