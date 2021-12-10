package se.trito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static se.trito.utils.FileReaderUtil.toStringList;

public class Dec10 {

    public static void run() {
        List<String> input = toStringList("files/10.txt");
        part1(input);
        part2(input);
    }

    public static void part1(List<String> input) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> errors = new HashMap<>();

        for (String s : input) {
            for (char c : s.toCharArray()) {
                boolean skipLine = isBadLine(stack, errors, c);
                if (skipLine) {
                    stack.clear();
                    break;
                }
            }
        }
        int sum = (errors.get(')') * 3) + (errors.get(']') * 57) + (errors.get('}') * 1197) + (errors.get('>') * 25137);
        System.out.println(sum);
    }

    public static void part2(List<String> input) {
        List<Long> sums = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> errors = new HashMap<>();
        for (String s : input) {
            boolean skipLine = false;
            for (char c : s.toCharArray()) {
                skipLine = isBadLine(stack, errors, c);
                if (skipLine) {
                    stack.clear();
                    break;
                }
            }

            if (!skipLine) {
                long sum = 0L;
                while (stack.size() > 0) {
                    char pop = stack.pop();
                    sum = sum * 5;
                    switch (pop) {
                        case '(' -> sum += 1;
                        case '[' -> sum += 2;
                        case '{' -> sum += 3;
                        case '<' -> sum += 4;
                    }
                }
                sums.add(sum);
            }
        }
        Collections.sort(sums);
        System.out.println(sums.get(sums.size() / 2));
    }

    private static boolean isBadLine(Stack<Character> stack, Map<Character, Integer> errors, char c) {
        switch (c) {
            case '(' -> stack.add(c);
            case '[' -> stack.add(c);
            case '{' -> stack.add(c);
            case '<' -> stack.add(c);
            case ')' -> {
                char pop = stack.pop();
                if (pop != '(') {
                    errors.put(')', errors.getOrDefault(')', 0) + 1);
                    return true;
                }
            }
            case ']' -> {
                char pop = stack.pop();
                if (pop != '[') {
                    errors.put(']', errors.getOrDefault(']', 0) + 1);
                    return true;
                }
            }
            case '}' -> {
                char pop = stack.pop();
                if (pop != '{') {
                    errors.put('}', errors.getOrDefault('}', 0) + 1);
                    return true;
                }
            }
            case '>' -> {
                char pop = stack.pop();
                if (pop != '<') {
                    errors.put('>', errors.getOrDefault('>', 0) + 1);
                    return true;
                }
            }
        }
        return false;
    }
}
