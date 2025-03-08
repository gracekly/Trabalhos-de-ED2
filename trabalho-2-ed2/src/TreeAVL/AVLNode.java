package TreeAVL;

import java.util.ArrayList;
import java.util.List;

public class AVLNode<T extends Comparable<T>> {
    T key;
    int height;
    AVLNode<T> left, right;
    List<Integer> lines;

    public AVLNode(T key, int line) {
        this.key = key;
        this.height = 1;
        this.lines = new ArrayList<>();
        this.lines.add(line);
    }

    public void addLine(int line) {
        this.lines.add(line);

    }
}
