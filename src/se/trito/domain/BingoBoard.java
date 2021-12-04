package se.trito.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BingoBoard {
    List<List<Integer>> rows = new ArrayList<>();
    public boolean isDone = false;

    public void addRow(String[] input) {
        List<Integer> row =  Stream.of(input)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        rows.add(row);
    }

    public boolean hasNumber(int number) {
        for (List<Integer> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                if (number == row.get(i)) {
                    row.set(i, -1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasBingo() {
        for (List<Integer> row : rows) {
            if (isRowBingo(row)) {
                isDone = true;
                return true;
            }
        }
        for (int i = 0; i < rows.get(0).size(); i++) {
           if (isColumnBingo(i)) {
               isDone = true;
               return true;
           }
        }
        return false;
    }

    private boolean isColumnBingo(int index) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).get(index) >= 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isRowBingo(List<Integer> row) {
        for (int i : row) {
            if (i >= 0) {
                return false;
            }
        }
        return true;
    }

    public int calculateScore() {
        int sum = 0;
        for (List<Integer> row : rows) {
            sum += row.stream()
                    .filter(i -> i > 0)
                    .mapToInt(Integer::intValue)
                    .sum();
       }
        return sum;

    }

    @Override
    public String toString() {
        return "BingoBoard{" +
                "rows=" + rows +
                '}';
    }
}
