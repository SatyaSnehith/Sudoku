package sudoku;

import javafx.event.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class SudokuSaverBigGridPane extends GridPane {
    public static int[][] n;
    Font font = new Font(30);
    public static Button button[][];
    GridPane gridPane[][];
    Screen screen = Screen.getPrimary();
    Rectangle2D r = screen.getVisualBounds();

    public SudokuSaverBigGridPane() {
        NumberStage.game = false;
        n = new int[9][9];
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                n[i][j] = 0;
            }
        }
        gridPane = new GridPane[3][3];
        button = new Button[9][9];
        setVgap(3);
        setHgap(3);
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                final int x = i, y = j;
                button[i][j] = new Button("  ");
                button[i][j].setFont(font);
                button[i][j].setStyle(Sudoku.buttonStyle);
                button[i][j].setMinWidth(Button.USE_PREF_SIZE);
                button[i][j].setMaxWidth(Double.MAX_VALUE);
                button[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    new NumberStage(x, y, mouseEvent.getScreenX(), mouseEvent.getScreenY()).show();
                    }
                });
            }
        }
        int x = 0, y =0;
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                gridPane[i][j] = new GridPane();
                x = i * 3;
                y = j * 3;
                for(int k = x; k < x + 3; ++k) {
                    for(int l = y; l < y + 3; ++l) {
                        gridPane[i][j].add(button[k][l], l % 3, k % 3);
                    }
                }
                add(gridPane[i][j], j, i);
            }
        }

    }
}
