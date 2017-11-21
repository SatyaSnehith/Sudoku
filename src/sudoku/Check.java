package sudoku;

public class Check {
    private int[][] sun;
    public Check(int[][] sun) {
        this.sun = sun;
    }
    public boolean getResult() {
        return checkRowsAndColumns() && checkSquare();
    }
    public boolean checkRowsAndColumns() {
        boolean inRow, inColumn, tof = true;
        int rNum = 0, cNum = 0, column = 0;
        for(int i = 1; i < 10; ++i) {
            for(int j = 0; j <= 8; ++j) {
                inRow = false;
                inColumn = false;
                for(int k = 0; k <= 8; ++k) {
                    if(sun[j][k] == i)
                        inRow = true;
                    if(sun[k][j] == i)
                        inColumn = true;
                }
                if(!inRow) {
                    // System.out.println("row " + (j + 1) + " does not have " + i + " in it.");
                    tof = false;
                }
                if(!inColumn) {
                    // System.out.println("column " + (j + 1) + " does not have " + i + " in it.");
                    tof = false;
                }
            }
        }
        return tof;
    }
    public boolean checkSmallSquare(int x, int y) {
        boolean tof;
        for(int k = 1; k < 10; ++k){
            tof = false;
            for(int i = x; i < x + 3; ++i) {
                for(int j = y; j < y + 3; ++j) {
                    if(sun[i][j] == k) {
                        tof = true;
                    }
                }
            }
            if(!tof) {
                System.out.println("at " + k);
                return false;
            }
        }
        return true;
    }
    public boolean checkSquare() {
        for(int i = 0; i < 9; i+=3) {
            for(int j = 0; j < 9; j+=3) {
                if(!checkSmallSquare(i, j)) {
                    System.out.print(i + ", " + j);
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {}
}
