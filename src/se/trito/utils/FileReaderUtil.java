package se.trito.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReaderUtil {

    public static String readToString(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> toStringList(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Integer> toIntList(String fileName) {
        List<Integer> inputs = new ArrayList<>();
        try {
            inputs = Files.readAllLines(Path.of(fileName))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public static List<Long> toLongList(String fileName) {
        List<Long> inputs = new ArrayList<>();
        try {
            inputs = Files.readAllLines(Path.of(fileName))
                    .stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public static List<String> splitOnRegexToStringList(String filename, String regex) {
        try {
            return Arrays.asList(Files.readString(Path.of(filename)).split(regex));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Integer> splitOnRegexToIntList(String filename, String regex) {
        List<Integer> inputs = new ArrayList<>();
        try {
            inputs = Arrays.stream(Files.readString(Path.of(filename)).split(regex))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public static List<List<String>> splitOnRegexTo2dStringList(String fileName, String regex) {
        List<List<String>> inputs = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            inputs.add(Arrays.asList(line.split(regex)));
        }
        return inputs;
    }

    public static List<List<Integer>> splitOnRegexTo2dIntList(String fileName, String regex) {
        List<List<Integer>> inputs = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            inputs.add(Arrays.stream(line.split(regex))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        }
        return inputs;
    }

    //Tar regex, exempelvis "[ :!]" som delimiter
    public static List<String> splitOnSpecialCharsToStringList(String fileName, String delimiters) {
        List<String> output = new ArrayList<>();
        try {
            Scanner scanner = null;
            scanner = new Scanner(new File(fileName));
            scanner.useDelimiter(delimiters);
            while (scanner.hasNext()) {
                String next = scanner.next();
                if (!next.isEmpty()) {
                    output.add(next);
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }

    //Tar regex, exempelvis "[ :!]" som delimiter
    public static List<Integer> splitOnSpecialCharsToIntList(String fileName, String delimiters) {
        List<Integer> output = new ArrayList<>();
        try {
            Scanner scanner = null;
            scanner = new Scanner(new File(fileName));
            scanner.useDelimiter(delimiters);
            while (scanner.hasNext()) {
                String next = scanner.next();
                if (!next.isEmpty()) {
                    output.add(Integer.parseInt(next));
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }
}