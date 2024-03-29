package ss.nscube.sudoku;

import javafx.scene.input.KeyCode;
import ss.nscube.sudoku.controls.SEButton;
import ss.nscube.sudoku.controls.STilePane;
import ss.nscube.sudoku.utils.ControlUiUtil;
import ss.nscube.sudoku.utils.Sudoku;
import ss.nscube.sudoku.utils.SudokuUtil;

public class BigGridPane extends STilePane {
    public int[][] sudoku;
    public int[][] unfilled;
    public int[][] answer;
    public SEButton[][] buttons;
    STilePane[][] gridPane;
    KeyCode[] numPad = new KeyCode[10];
    KeyCode[] digits = new KeyCode[10];
    private int selectedI = 0, selectedJ = 0;
    NumberStage numberStage = new NumberStage(this);

    SudokuScene scene;

    public BigGridPane(SudokuScene scene) {
        this.scene = scene;
        init();
        this.setPrefColumns(3);
        this.setPrefRows(3);
        setVgap(1);
        setHgap(2);
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                SEButton button = ControlUiUtil.getSudokuButton(" ");
                buttons[i][j] = button;
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnMouseClicked(mouseEvent -> {
                    select(finalI, finalJ);
                    if (unfilled[finalI][finalJ] == 0 && scene.isShowPopup()) {
                        numberStage.show(finalI, finalJ, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                    } else {
                        BigGridPane.this.getParent().requestFocus();
                    }
                });
            }
        }
        int x, y;
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                gridPane[i][j] = new STilePane();
                gridPane[i][j].setPrefColumns(3);
                gridPane[i][j].setPrefRows(3);
                x = i * 3;
                y = j * 3;
                for(int k = x; k < x + 3; ++k) {
                    for(int l = y; l < y + 3; ++l) {
                        gridPane[i][j].getChildren().add(buttons[k][l]);
                    }
                }
                getChildren().add(gridPane[i][j]);
            }
        }
        numberStage.setOnCloseRequest(event -> {this.requestFocus();});
    }

    public void update(Sudoku sdk) {
        int[][] s = sdk.getSudoku();
        answer = sdk.getAnswer();
        for(int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                int num = s[i][j];
                sudoku[i][j] = num;
                unfilled[i][j]  = num;
                if(s[i][j] != 0) {
                    buttons[i][j].disable();
                    buttons[i][j].setText(String.valueOf(num));
                } else {
                    buttons[i][j].blank();
                    buttons[i][j].setText(" ");
                }
            }
        }
        select(selectedI, selectedJ);
    }

    void init() {
        sudoku = new int[9][9];
        unfilled = new int[9][9];
        gridPane = new STilePane[3][3];
        buttons = new SEButton[9][9];
        numPad[0] = KeyCode.NUMPAD0;
        numPad[1] = KeyCode.NUMPAD1;
        numPad[2] = KeyCode.NUMPAD2;
        numPad[3] = KeyCode.NUMPAD3;
        numPad[4] = KeyCode.NUMPAD4;
        numPad[5] = KeyCode.NUMPAD5;
        numPad[6] = KeyCode.NUMPAD6;
        numPad[7] = KeyCode.NUMPAD7;
        numPad[8] = KeyCode.NUMPAD8;
        numPad[9] = KeyCode.NUMPAD9;
        digits[0] = KeyCode.DIGIT0;
        digits[1] = KeyCode.DIGIT1;
        digits[2] = KeyCode.DIGIT2;
        digits[3] = KeyCode.DIGIT3;
        digits[4] = KeyCode.DIGIT4;
        digits[5] = KeyCode.DIGIT5;
        digits[6] = KeyCode.DIGIT6;
        digits[7] = KeyCode.DIGIT7;
        digits[8] = KeyCode.DIGIT8;
        digits[9] = KeyCode.DIGIT9;
    }

    public void handleKey(KeyCode c) {
        if(c.equals(KeyCode.RIGHT) && selectedJ != 8) {
            select(selectedI, selectedJ+1);
        } else if(c.equals(KeyCode.LEFT)&& selectedJ != 0) {
            select(selectedI, selectedJ-1);
        } else if(c.equals(KeyCode.UP) && selectedI != 0) {
            select(selectedI-1, selectedJ);
        } else if(c.equals(KeyCode.DOWN) && selectedI != 8) {
            select(selectedI+1, selectedJ);
        }
        for(int i = 0; i < 10; ++i){
            if((c.equals(digits[i]) || c.equals(numPad[i])) && !buttons[selectedI][selectedJ].isDisabled()) {
                setValue(selectedI, selectedJ, i);
            }
        }
        if(c.equals(KeyCode.ESCAPE) || c.equals(KeyCode.BACK_SPACE)) {
            setValue(selectedI, selectedJ, 0);
        }
    }

    public void setValue(int i, int j, int num) {
        if (unfilled[i][j] == 0) {
            if (num == 0) {
                buttons[i][j].setText(" ");
                sudoku[i][j] = num;
            } else {
                buttons[i][j].setText(String.valueOf(num));
//                boolean correct = SudokuUtil.isEligible(num, sudoku, i, j);
                sudoku[i][j] = num;
                if (isSolved()) {
                    System.out.println("SOLVED");
                }
            }
            highlight(i, j);
        }
    }

    public boolean isSolved() {
        if (isCompleted()) return SudokuUtil.solve(SudokuUtil.cloneSudoku(sudoku));
        return false;
    }

    public boolean isCompleted() {
        for(int i = 0; i < 9; ++i) for (int j = 0; j < 9; ++j) if (sudoku[i][j] == 0) return false;
        return true;
    }

    public void select(int i, int j) {
        buttons[selectedI][selectedJ].normal();
        selectedI = i;
        selectedJ = j;
        highlight(i, j);
    }

    void highlight(int x, int y) {
        int num = sudoku[x][y];
        if (num == 0) buttons[x][y].wrong(false);
        for(int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if(sudoku[i][j] != 0) {
                    buttons[i][j].wrong(sudoku[i][j] != answer[i][j]);
                    if (!(i == x && j == y) && sudoku[i][j] == num) buttons[i][j].hint();
                    else buttons[i][j].normal();
                } else {
                    buttons[i][j].normal();
                }
            }
        }
        System.out.println(num);
        scene.onSudokuUpdated();
        for (int i = 0; i < 9; ++i) if (i != y) buttons[x][i].hint();
        for (int i = 0; i < 9; ++i) if (i != x) buttons[i][y].hint();
        int xFrom = (x / 3) * 3;
        int yFrom = (y / 3) * 3;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                if (x != i+xFrom || j+yFrom != y) buttons[i+xFrom][j+yFrom].hint(); else buttons[i+xFrom][j+yFrom].normal();
            }
        buttons[x][y].select();
    }

    @Override
    public void refresh() {
        super.refresh();
        if (numberStage != null) numberStage.refresh();
    }
}
