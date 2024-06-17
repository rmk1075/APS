package programmers.행렬과_연산;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
}


class Solution {
    private static final String SHIFT_ROW = "ShiftRow";
    private static final String ROTATE = "Rotate";
    private Deque<Integer> left;
    private Deque<Integer> right;
    private Deque<Deque<Integer>> mid;
    public int[][] solution(int[][] rc, String[] operations) {
        init(rc);
        for (String op : operations) {
            if (op.equals(SHIFT_ROW)) {
                shiftRow();
            } else if(op.equals(ROTATE)) {
                rotate();
            }
        }
        set(rc);
        return rc;
    }

    private void init(int[][] rc) {
        int r = rc.length;
        left = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            left.offer(rc[i][0]);
        }

        int c = rc[0].length;
        mid = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            Deque<Integer> temp = new ArrayDeque<>();
            for (int j = 1; j < c - 1; j++) {
                temp.offer(rc[i][j]);
            }
            mid.offer(temp);
        }

        right = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            right.offer(rc[i][c - 1]);
        }
    }

    private void shiftRow() {
        left.addFirst(left.pollLast());
        mid.addFirst(mid.pollLast());
        right.addFirst(right.pollLast());
    }

    private void rotate() {
        int temp = left.pollFirst();
        Deque<Integer> first = mid.getFirst();
        first.addFirst(temp);
        right.addFirst(first.pollLast());
        Deque<Integer> last = mid.getLast();
        last.addLast(right.pollLast());
        left.addLast(last.pollFirst());
    }

    private void set(int[][] rc) {
        int r = rc.length;
        for (int i = 0; i < r; i++) {
            rc[i][0] = left.pollFirst();
        }

        int c = rc[0].length;
        for (int i = 0; i < r; i++) {
            Deque<Integer> row = mid.pollFirst();
            for (int j = 1; j < c - 1; j++) {
                rc[i][j] = row.pollFirst();
            }
        }

        for (int i = 0; i < r; i++) {
            rc[i][c - 1] = right.pollFirst();
        }
    }
}
