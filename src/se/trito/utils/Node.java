package se.trito.utils;

import java.util.Objects;

public class Node {
    public int x;
    public int y;
    public int c;


    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public Node() {
    }

    public int getManhattanDistance(Node externNode) {
        return Math.abs(externNode.x - this.x) + Math.abs(externNode.y - this.y);
    }

    public double getHypothenus(Node externNode) {
        int adjacent = Math.abs(externNode.x - this.x);
        int opposite = Math.abs(externNode.y - this.y);
        return Math.sqrt((adjacent*adjacent)+(opposite*opposite));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, c);
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", c=" + c +
                '}';
    }
}
