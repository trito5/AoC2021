package se.trito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static se.trito.utils.FileReaderUtil.splitOnRegexToIntList;

public class Dec06 {
    public static void run() {
        List<Integer> input = splitOnRegexToIntList("files/06.txt", ",");
        part1(input);
        part2(input);
    }

    private static void part1(List<Integer> input) {
        Map<Integer, Long> fishMap = mapStartFishes(input);
        fishMap = mapNewFishGeneration(fishMap, 80);
        Long sum = countFishes(fishMap);
        System.out.println(sum);
    }

    private static void part2(List<Integer> input) {
        Map<Integer, Long> fishMap = mapStartFishes(input);
        fishMap = mapNewFishGeneration(fishMap, 256);
        Long sum = countFishes(fishMap);
        System.out.println(sum);
    }

    private static Long countFishes(Map<Integer, Long> fishMap) {
        Long sum = 0L;
        for (Map.Entry<Integer, Long> entry : fishMap.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    private static Map<Integer, Long> mapNewFishGeneration(Map<Integer, Long> fishMap, int days) {
        for (int i = 1; i <= days; i++) {
            Map<Integer, Long> tempMap = new HashMap<>();
            tempMap.put(0, fishMap.getOrDefault(1, 0L));
            tempMap.put(1, fishMap.getOrDefault(2, 0L));
            tempMap.put(2, fishMap.getOrDefault(3, 0L));
            tempMap.put(3, fishMap.getOrDefault(4, 0L));
            tempMap.put(4, fishMap.getOrDefault(5, 0L));
            tempMap.put(5, fishMap.getOrDefault(6, 0L));
            tempMap.put(6, fishMap.getOrDefault(7, 0L) + fishMap.getOrDefault(0, 0L));
            tempMap.put(7, fishMap.getOrDefault(8, 0L));
            tempMap.put(8, fishMap.getOrDefault(0, 0L));

            fishMap.clear();
            fishMap = tempMap;
        }
        return fishMap;
    }

    private static Map<Integer, Long> mapStartFishes(List<Integer> input) {
        Map<Integer, Long> fishMap = new HashMap<>();
        for (int i : input) {
            if (fishMap.containsKey(i)) {
                fishMap.put(i, fishMap.get(i) + 1);
            } else {
                fishMap.put(i, 1L);
            }
        }
        return fishMap;
    }
}
