package ss.nscube.sudoku.utils;

public class SudokuGenerator {
    private int[][] s;
    private int[][] filled;
    private int[] randomArray = SudokuUtil.getRandomized81();
    private int currentIndex = 0;

    public SudokuGenerator() {
        initiateSudoku();
        generateRandomSudoku();
        createSudokuWithUniqueSolution();
    }

    public void generateRandomSudoku() {
        fillDiagonal();
        solve();
        SudokuUtil.copySudoku(s, filled);
    }

    public void removeElement() {
        int random = randomArray[currentIndex++];
        int i = random / 9;
        int j = random % 9;
        if (s[i][j] != 0) {
            s[i][j] = 0;
        }
    }

    public boolean hasUniqueSolution() {
        return SudokuUtil.hasUniqueSolution(SudokuUtil.cloneSudoku(s));
    }


    void fillDiagonal() {
        int l;
        for(int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    do {
                        l = SudokuUtil.randomGenerator(1, 9);
                    } while (!isEligibleBox(l, j + i, k + i));
                    s[j + i][k + i] = l;
                }
            }
        }
    }

    boolean solve() {
        int x = -1, y = -1;
        for(int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j)
                if (s[i][j] == 0) {
                    x = i;
                    y = j;
                }
        if (x == -1) return true;
        for (int i = 1; i <= 9; ++i) {
            if (isEligible(i, x, y)) {
                s[x][y] = i;
                if (solve()) return true;
                s[x][y] = 0;
            }
        }
        return false;
    }

    boolean isEligibleBox(int n, int x, int y) {
        int xFrom = (x / 3) * 3;
        int yFrom = (y / 3) * 3;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                if (s[i+xFrom][j+yFrom] == n) return false;
            }
        return true;
    }

    boolean isEligible(int n, int x, int y) {
        for (int i = 0; i < 9; ++i) if (s[x][i] == n) return false;
        for (int i = 0; i < 9; ++i) if (s[i][y] == n) return false;
        return isEligibleBox(n, x, y);
    }

    public void initiateSudoku() {
        s = new int[9][9];
        filled = new int[9][9];
        for(int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j) {
                s[i][j] = 0;
                filled[i][j] = 0;
            }
    }

    public int[][] getSudoku() {
        return s;
    }

    public void createSudokuWithUniqueSolution() {
        while (currentIndex < 81) {
            removeElement();
            if (!hasUniqueSolution()) {
                int last = randomArray[currentIndex - 1];
                s[last / 9][last % 9] = filled[last / 9][last % 9];
            }
        }
    }

    public void printWithDot() {
        SudokuUtil.printWithDot(s);
    }

    public void print() {
        SudokuUtil.print(s);
    }

    public static void main(String[] args) {
        SudokuGenerator sg = new SudokuGenerator();
    }
}