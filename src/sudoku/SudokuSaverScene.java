package sudoku;

import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import javafx.stage.*;

public class SudokuSaverScene extends Scene {
    public static int x = 0, y = 0;
    public SudokuSaverScene(VBox vBox) {
        super(vBox, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        vBox.setStyle(Sudoku.vBoxStyle);
        vBox.setAlignment(Pos.CENTER);
        KeyCode digits[] = new KeyCode[9];
        digits[0] = KeyCode.DIGIT1;
        digits[1] = KeyCode.DIGIT2;
        digits[2] = KeyCode.DIGIT3;
        digits[3] = KeyCode.DIGIT4;
        digits[4] = KeyCode.DIGIT5;
        digits[5] = KeyCode.DIGIT6;
        digits[6] = KeyCode.DIGIT7;
        digits[7] = KeyCode.DIGIT8;
        digits[8] = KeyCode.DIGIT9;
        KeyCode numpad[] = new KeyCode[9];
        numpad[0] = KeyCode.NUMPAD1;
        numpad[1] = KeyCode.NUMPAD2;
        numpad[2] = KeyCode.NUMPAD3;
        numpad[3] = KeyCode.NUMPAD4;
        numpad[4] = KeyCode.NUMPAD5;
        numpad[5] = KeyCode.NUMPAD6;
        numpad[6] = KeyCode.NUMPAD7;
        numpad[7] = KeyCode.NUMPAD8;
        numpad[8] = KeyCode.NUMPAD9;
        FlowPane flowPane = new FlowPane(100, 100);
        SudokuSaverBigGridPane bgp = new SudokuSaverBigGridPane();
        flowPane.getChildren().add(bgp);
        flowPane.getChildren().add(new SudokuSaverButtonVBox());
        vBox.getChildren().add(flowPane);
        flowPane.setAlignment(Pos.CENTER);
        Values values  = (Values) new Deserialize(new File("values")).getObject();
        char a[] = (char[]) values.getE();
        this.setOnKeyPressed(new EventHandler<KeyEvent> () {
            @Override
            public void handle(KeyEvent e) {
                KeyCode c = e.getCode();
                if(c.equals(KeyCode.RIGHT) && y != 8) {
                    SudokuSaverBigGridPane.button[x][y].setStyle(Sudoku.buttonStyle);
                    SudokuSaverBigGridPane.button[x][y+1].setStyle(Sudoku.selectedButtonStyle);
                    y++;
                } else if(c.equals(KeyCode.LEFT)&& y != 0) {
                    SudokuSaverBigGridPane.button[x][y].setStyle(Sudoku.buttonStyle);
                    SudokuSaverBigGridPane.button[x][y-1].setStyle(Sudoku.selectedButtonStyle);
                    y--;
                } else if(c.equals(KeyCode.UP) && x != 0) {
                    SudokuSaverBigGridPane.button[x][y].setStyle(Sudoku.buttonStyle);
                    SudokuSaverBigGridPane.button[x-1][y].setStyle(Sudoku.selectedButtonStyle);
                    x--;
                } else if(c.equals(KeyCode.DOWN) && x != 8) {
                    SudokuSaverBigGridPane.button[x][y].setStyle(Sudoku.buttonStyle);
                    SudokuSaverBigGridPane.button[x+1][y].setStyle(Sudoku.selectedButtonStyle);
                    x++;
                } else if(c.equals(KeyCode.C)) {
                    SudokuSaverBigGridPane.button[x][y].setText("  ");
                    SudokuSaverBigGridPane.n[x][y] = 0;
                }
                for(int i = 1; i <= 9; ++i){
                    if((c.equals(digits[i-1]) || c.equals(numpad[i-1]))) {
                        SudokuSaverBigGridPane.button[x][y].setText(a[i-1] + "");
                        SudokuSaverBigGridPane.n[x][y] = i;
                    }
                }
            }
        });

    }
}
