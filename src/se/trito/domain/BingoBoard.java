package se.trito.domain;

import java.util.ArrayList;
import java.util.List;

public class BingoBoard {
    List<List<Integer>> rows = new ArrayList<>();
    public boolean isDone = false;

    public void addRow(String[] input) {
        List<Integer> row = new ArrayList<>();
        for (String s : input) {
            if (!s.isEmpty()) {
                row.add(Integer.parseInt(s));
            }
        }
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
            for (int i : row) {
                if (i > 0) {
                    sum+= i;
                }
            }
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
