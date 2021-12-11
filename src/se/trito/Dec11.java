package se.trito;

import se.trito.utils.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static se.trito.utils.FileReaderUtil.toStringList;

public class Dec11 {

    public static void run() {
        List<String> input = toStringList("files/11.txt");
        part1(input);
        part2(input);
    }

    public static void part1(List<String> input) {
        List<Node> nodes = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        addNodes(input, nodes);
        int sum = 0;
        for (int turn = 1; turn <= 100; turn++) {

            sum = addEnergy(nodes, queue, sum);
            for (Node nodeCheck : nodes) {
                if (nodeCheck.c == -1) {
                    nodeCheck.c = 0;
                }
            }
        }
        System.out.println(sum);
    }

    public static void part2(List<String> input) {
        List<Node> nodes = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        addNodes(input, nodes);
        int sum = 0;
        int syncedTurn = 0;
        for (int turn = 1; turn <= 1000; turn++) {
            sum = addEnergy(nodes, queue, sum);
            int counter = 0;
            for (Node nodeCheck : nodes) {
                if (nodeCheck.c == -1) {
                    nodeCheck.c = 0;
                    counter++;
                }
            }
            if (counter == nodes.size()) {
                syncedTurn = turn;
                break;
            }
        }
        System.out.println(syncedTurn);
    }

    private static int addEnergy(List<Node> nodes, Queue<Node> queue, int sum) {
        for (Node n : nodes) {
            queue.add(n);
            while (!queue.isEmpty()) {
                Node pop = queue.poll();
                if (pop.c != 10 && pop.c != -1) {
                    pop.c++;
                }
                //if it flashes
                if (pop.c == 10) {
                    sum++;
                    pop.c = -1;
                    int i = nodes.indexOf(new Node(pop.x - 1, pop.y));
                    if (i != -1) {
                        if (nodes.get(i).c != -1)
                            queue.add(nodes.get(i));
                    }
                    i = nodes.indexOf(new Node(pop.x - 1, pop.y - 1));
                    if (i != -1) {
                        if (nodes.get(i).c != -1)
                            queue.add(nodes.get(i));
                    }
                    i = nodes.indexOf(new Node(pop.x, pop.y - 1));
                    if (i != -1) {
                        if (nodes.get(i).c != -1)
                            queue.add(nodes.get(i));
                    }
                    i = nodes.indexOf(new Node(pop.x + 1, pop.y - 1));
                    if (i != -1) {
                        if (nodes.get(i).c != -1)
                            queue.add(nodes.get(i));
                    }
                    i = nodes.indexOf(new Node(pop.x + 1, pop.y));
                    if (i != -1) {
                        if (nodes.get(i).c != -1)
                            queue.add(nodes.get(i));
                    }
                    i = nodes.indexOf(new Node(pop.x + 1, pop.y + 1));
                    if (i != -1) {
                        if (nodes.get(i).c != -1)
                            queue.add(nodes.get(i));
                    }
                    i = nodes.indexOf(new Node(pop.x, pop.y + 1));
                    if (i != -1) {
                        if (nodes.get(i).c != -1)
                            queue.add(nodes.get(i));
                    }
                    i = nodes.indexOf(new Node(pop.x - 1, pop.y + 1));
                    if (i != -1) {
                        if (nodes.get(i).c != -1)
                            queue.add(nodes.get(i));
                    }
                }
            }
        }
        return sum;
    }

    private static void addNodes(List<String> input, List<Node> nodes) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                Node n = new Node(j, i, Integer.parseInt(String.valueOf(input.get(i).charAt(j))));
                nodes.add(n);
            }
        }
    }
}
