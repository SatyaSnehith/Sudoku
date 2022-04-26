package ss.nscube.sudoku.utils;

public class Sudoku {
    private int[][] sudoku;
    private int[][] answer;

    public Sudoku(int[][] sudoku, int[][] answer) {
        this.sudoku = sudoku;
        this.answer = answer;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public int[][] getAnswer() {
        return answer;
    }

    public void setAnswer(int[][] answer) {
        this.answer = answer;
    }
}
