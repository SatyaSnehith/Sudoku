package sudoku;

import java.io.File;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.input.*;
public class NumberStage extends Stage {
    public static boolean game;
    public static int number;
    VBox vBox;
    Scene scene;
    GridPane gridPane;
    public static Button b[], clearButton;
    public static int x, y;
    public NumberStage(int i, int j, double x, double y) {
        setX(x);
        setY(y);
        Values values  = (Values) new Deserialize(new File("values")).getObject();
        char a[] = (char[]) values.getE();
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle(Sudoku.vBoxStyle);
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        b = new Button[9];
        int q = 0, w = -1;
        for(int c = 0; c < 9; ++c) {
            int d = c;
            b[c] = new Button(a[c] + "");
            b[c].setFont(Sudoku.font);
            b[c].setStyle(Sudoku.buttonStyle);
            b[c].setOnAction(new EventHandler<ActionEvent> () {
                @Override
                public void handle(ActionEvent event) {
                    number = d+1;
                    if(SudokuButtonVBox.subsetActivated) {
                        BigGridPane.button[i][j].setText(SudokuScene.getString(i, j, number));
                        BigGridPane.n[i][j] = 0;
                    }
                    else if(game) {
                        BigGridPane.button[i][j].setText(a[number - 1] + "");
                        BigGridPane.n[i][j] = number;
                    } else {
                        SudokuSaverBigGridPane.button[i][j].setText(a[number - 1] + "");
                        SudokuSaverBigGridPane.n[i][j] = number;
                    }
                    close();
                }
            });
            if(q % 3 == 0) {
                q = 0;
                w+=1;
            }            
            gridPane.add(b[c], q++, w);
        }
        clearButton = new Button("Clear");
        clearButton.setFont(new Font(Sudoku.fontFamily, 40));
        clearButton.setMinWidth(180);
        clearButton.setMaxHeight(b[0].getHeight());
        clearButton.setStyle(Sudoku.buttonStyle);
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                number = 0;
                if(game) {
                    BigGridPane.button[i][j].setText(" ");
                    BigGridPane.n[i][j] = number;
                    for (int k = 0; k < 9; ++k) {
                        BigGridPane.sun[i][j][k] = number;
                    }
                } else {
                    SudokuSaverBigGridPane.button[i][j].setText(" ");
                    SudokuSaverBigGridPane.n[i][j] = number;
                }
                close();
            }
        });
        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(clearButton);
        scene = new Scene(vBox);
        setScene(scene);
        initStyle(StageStyle.UNDECORATED);
        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent event) {
               try {
                   number = Integer.parseInt(event.getCharacter());
                   BigGridPane.button[i][j].setText(Sudoku.e[number - 1] + "");
                   BigGridPane.n[i][j] = number;
                   SudokuSaverBigGridPane.button[i][j].setText(Sudoku.e[number - 1] + "");
                   SudokuSaverBigGridPane.n[i][j] = number;
                   close();
               } catch(Exception e) {
                   if(event.getCharacter().equals("c")) {
                       number = 0;
                       BigGridPane.button[i][j].setText(" ");
                       BigGridPane.n[i][j] = number;
                       SudokuSaverBigGridPane.button[i][j].setText(Sudoku.e[number - 1] + "");
                       SudokuSaverBigGridPane.n[i][j] = number;
                       close();
                   } else
                       System.out.print("enter only a number");
               }
           }
        });
    }
    public static void main(String[] args) {}
}