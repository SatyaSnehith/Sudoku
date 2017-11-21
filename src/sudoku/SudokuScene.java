package sudoku;

import java.io.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import javafx.scene.text.Font;
import javafx.stage.*;
public class SudokuScene extends Scene {
    public static File file;
    public static BigGridPane bgp;
    public static SudokuButtonVBox sudokuButtonVBox;
    public int x = 0, y = 0;
    VBox vBox;
    public SudokuScene(VBox vBox) {
        super(vBox, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        this.vBox = vBox;
        vBox.setStyle(Sudoku.vBoxStyle);
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
        this.setOnKeyPressed(new EventHandler<KeyEvent> () {
            @Override
            public void handle(KeyEvent e) {
                Values values  = (Values) new Deserialize(new File("values")).getObject();
                char a[] = (char[]) values.getE(); 
                KeyCode c = e.getCode();
                if(c.equals(KeyCode.RIGHT) && y != 8) {
                    BigGridPane.button[x][y].setStyle(Sudoku.buttonStyle);
                    BigGridPane.button[x][y+1].setStyle(Sudoku.selectedButtonStyle);
                    y++;
                } else if(c.equals(KeyCode.LEFT)&& y != 0) {
                    BigGridPane.button[x][y].setStyle(Sudoku.buttonStyle);
                    BigGridPane.button[x][y-1].setStyle(Sudoku.selectedButtonStyle);
                    y--;
                } else if(c.equals(KeyCode.UP) && x != 0) {
                    BigGridPane.button[x][y].setStyle(Sudoku.buttonStyle);
                    BigGridPane.button[x-1][y].setStyle(Sudoku.selectedButtonStyle);
                    x--;
                } else if(c.equals(KeyCode.DOWN) && x != 8) {
                    BigGridPane.button[x][y].setStyle(Sudoku.buttonStyle);
                    BigGridPane.button[x+1][y].setStyle(Sudoku.selectedButtonStyle);
                    x++;
                }
                for(int i = 1; i <= 9; ++i){
                    if((c.equals(digits[i-1]) || c.equals(numpad[i-1])) && SudokuButtonVBox.subsetActivated && !BigGridPane.button[x][y].isDisabled()) {
                        BigGridPane.n[x][y] = 0;
                        BigGridPane.button[x][y].setFont(new Font(Sudoku.fontFamily, 12));
                        BigGridPane.button[x][y].setText(getString(x, y, i));
                    } else if((c.equals(digits[i-1]) || c.equals(numpad[i-1])) && !BigGridPane.button[x][y].isDisabled()) {
                        BigGridPane.button[x][y].setText(a[i-1] + "");
                        BigGridPane.n[x][y] = i;
                    }
                }
            }
        });
        FlowPane flowPane = new FlowPane(100, 100);
        bgp = new BigGridPane();
        sudokuButtonVBox = new SudokuButtonVBox();
        flowPane.getChildren().add(bgp);
        flowPane.getChildren().add(sudokuButtonVBox);
        vBox.getChildren().add(flowPane);
        vBox.setAlignment(Pos.CENTER);
        flowPane.setAlignment(Pos.CENTER);
    }
    public static String getString(int x, int y, int i) {
        Values values  = (Values) new Deserialize(new File("values")).getObject();
        char b[] = (char[]) values.getE();
        if(BigGridPane.sun[x][y][i-1] == i)
            BigGridPane.sun[x][y][i-1] = 0;
        else
            BigGridPane.sun[x][y][i-1] = i;
        final int z = 0;
        String string = "";
        for(int a = 0; a < 9; ++a) {
            string += "" + (z == BigGridPane.sun[x][y][a] ? " " : (b[BigGridPane.sun[x][y][a] - 1] + ""));
            if(a == 2 || a == 5)
                string += "\n";
            if(!((a+1) % 3 == 0))
                string += " ";
        }
        return string;
    }
    public VBox getVBox(){
        return vBox;
    }
}