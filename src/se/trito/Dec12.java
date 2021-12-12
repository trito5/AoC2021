package se.trito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static se.trito.utils.FileReaderUtil.splitOnRegexTo2dStringList;

public class Dec12 {

    public static void run() {
        List<List<String>> input = splitOnRegexTo2dStringList("files/12.txt", "-");
        part1(input);
        part2(input);
    }

    public static void part1(List<List<String>> input) {
        int sum = 0;
        Queue<List<String>> queue = new LinkedList<>();
        Map<String, List<String>> connections = new HashMap<>();
        for (List<String> list : input) {
            addConnections(connections, list.get(0), list.get(1));
            addConnections(connections, list.get(1), list.get(0));
        }
        List<String> temp = new ArrayList<>();
        temp.add("start");
        queue.add(temp);

        while (!queue.isEmpty()) {
            List<String> latestPath = queue.poll();
            List<String> possibilities = connections.get(latestPath.get(latestPath.size() - 1));
            for (String s : possibilities) {
                if (s.equals("end")) {
                    sum++;
                } else {
                    List<String> newPath = new ArrayList<>(latestPath);
                    if (s.toUpperCase().equals(s)) {
                        newPath.add(s);
                        queue.add(newPath);
                    } else {
                        if (!newPath.contains(s)) {
                            newPath.add(s);
                            queue.add(newPath);
                        }
                    }
                }
            }
        }
        System.out.println("Sum " + sum);
    }

    public static void part2(List<List<String>> input) {
        int sum = 0;
        Queue<List<String>> queue = new LinkedList<>();
        Map<String, List<String>> connections = new HashMap<>();
        for (List<String> list : input) {
            addConnections(connections, list.get(0), list.get(1));
            addConnections(connections, list.get(1), list.get(0));
        }
        List<String> temp = new ArrayList<>();
        temp.add("start");
        queue.add(temp);

        while (!queue.isEmpty()) {
            List<String> latestPath = queue.poll();
            List<String> possibilities = connections.get(latestPath.get(latestPath.size() - 1));
            for (String s : possibilities) {
                if (s.equals("end")) {
                    sum++;
                } else {
                    List<String> newPath = new ArrayList<>(latestPath);
                    boolean isUpperCase = s.toUpperCase().equals(s);
                    if (!"start".equals(s)) {
                        if (isUpperCase) {
                            newPath.add(s);
                            queue.add(newPath);
                        } else if (!newPath.contains(s) || hasOnlyVisitedSmallCavesOnce(newPath)) {
                            newPath.add(s);
                            queue.add(newPath);
                        }
                    }
                }
            }
        }
        System.out.println("Sum " + sum);
    }

    private static void addConnections(Map<String, List<String>> connections, String key, String value) {
        if (connections.containsKey(key)) {
            connections.get(key).add(value);
        } else {
            List<String> temp = new ArrayList<>();
            temp.add(value);
            connections.put(key, temp);
        }
    }

    private static boolean hasOnlyVisitedSmallCavesOnce(List<String> path) {
        int noOfSmallCaves = 0;
        Set<String> uniqueCaves = new HashSet<>();
        for (String s : path) {
            boolean isUpperCase = s.toUpperCase().equals(s);
            if (!isUpperCase) {
                noOfSmallCaves++;
                uniqueCaves.add(s);
            }
        }
        return uniqueCaves.size() == noOfSmallCaves;
    }

}
