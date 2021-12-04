package se.trito;

import se.trito.domain.BingoBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static se.trito.utils.FileReaderUtil.toStringList;

public class Dec04 {
    public static void run() {
        List<String> input = toStringList("files/04.txt");
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        List<Integer> calls = getCalls(input.get(0));
        List<BingoBoard> boards = new ArrayList<>();
        for (int i = 2; i < input.size(); i += 6) {
            boards.add(createBoard(input, i));
        }

        int score = playBingo(input, 1, calls, boards);
        System.out.println("part 1: " + score);
    }

    private static void part2(List<String> input) {
        List<Integer> calls = getCalls(input.get(0));
        List<BingoBoard> boards = new ArrayList<>();
        for (int i = 2; i < input.size(); i += 6) {
            boards.add(createBoard(input, i));
        }

        int score = playBingo(input, boards.size(), calls, boards);
        System.out.println("part 2: " + score);
    }

    private static int playBingo(List<String> input, int numberOfBingosStop, List<Integer> calls, List<BingoBoard> boards) {
        int score = 0;
        int boardsWithBingo = 0;
        for (int call : calls) {
            for (BingoBoard board : boards) {
                if (!board.isDone && board.hasNumber(call) && board.hasBingo()) {
                    boardsWithBingo++;
                    if (boardsWithBingo == numberOfBingosStop) {
                        score = board.calculateScore() * call;
                        return score;
                    }
                }
            }
        }
        return score;
    }

    private static List<Integer> getCalls(String input) {
        String[] callsString = input.split(",");
        return Stream.of(callsString)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static BingoBoard createBoard(List<String> input, int start) {
        BingoBoard board = new BingoBoard();
        for (int i = start; i < start + 5; i++) {
            String[] numbers = input.get(i).split(" ");
            board.addRow(numbers);
        }
        return board;
    }
}
