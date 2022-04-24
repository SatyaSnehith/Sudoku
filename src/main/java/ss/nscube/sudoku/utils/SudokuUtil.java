package ss.nscube.sudoku.utils;

import java.util.Random;
import java.util.Arrays;

public class SudokuUtil {
    public static int[][] generateSudoku() {
        return (new SudokuGenerator()).getSudoku();
    }

    public static int randomGenerator(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static Boolean hasUniqueSolution(int[][] s) {
        int x = -1, y = -1;
        for(int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j)
                if (s[i][j] == 0) {
                    x = i;
                    y = j;
                }
        if (x == -1) return true;
        boolean solved = false;
        int solvedWith = -1;
        for (int i = 1; i <= 9; ++i) {
            if (isEligible(i, s, x, y)) {
                s[x][y] = i;
                if (solve(cloneSudoku(s))) {
                    if (solved) return false;
                    solved = true;
                    solvedWith = i;
                }
                if (!hasUniqueSolution(cloneSudoku(s))) return false;
            }
        }
        return solved;
    }

    public static boolean isEligible(int n, int s[][], int x, int y) {
        for (int i = 0; i < 9; ++i) if (i != y && s[x][i] == n) return false;
        for (int i = 0; i < 9; ++i) if (i != x && s[i][y] == n) return false;
        int xFrom = (x / 3) * 3;
        int yFrom = (y / 3) * 3;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                if (x != i+xFrom && j+yFrom != y && s[i+xFrom][j+yFrom] == n) return false;
            }
        return true;
    }

    public static void copySudoku(int[][] from, int[][] to) {
        for(int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j) to[i][j] = from[i][j];
    }

    public static int[][] cloneSudoku(int[][] s) {
        int[][] temp = new int[9][9];
        for(int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j) temp[i][j] = s[i][j];
        return temp;
    }

    public static boolean solve(int[][] s) {
        int x = -1, y = -1;
        for(int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j)
                if (s[i][j] == 0) {
                    x = i;
                    y = j;
                }
        if (x == -1) return true;
        for (int i = 1; i <= 9; ++i) {
            if (isEligible(i, s, x, y)) {
                s[x][y] = i;
                if (solve(s)) return true;
                s[x][y] = 0;
            }
        }
        return true;
    }

    public static int[] getRandomized81() {
        int arr[] = new int[81];
        for (int i = 0; i < 81; ++i) arr[i] = i;
        Random r = new Random();
        for (int i = 80; i > 0; i--) {
            int j = r.nextInt(i);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    public static void print(int[][] s) {
        for(int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) System.out.print(s[i][j] + " ");
            System.out.println();
        }
    }
    
    public static void printWithDot(int s[][]) {
        for(int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j) System.out.print((s[i][j] == 0) ? "." : s[i][j]);
        System.out.println();
    }

    public static int[][] toSudoku(String s) {
        if (s.length() != 81) throw new IllegalArgumentException("sudoku string length should be 81");
        int[][] a = new int[9][9];
        for (int i = 0; i < 81; ++i) {
            char c = s.charAt(i);
            int x = i / 9;
            int y = i % 9;
            a[x][y] = (c == '.') ? 0 : (c - '0');
        }
        return a;
    }
}