/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.File;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;

/**
 *
 * @author satya
 */
public class SudokuListScene extends Scene {
    Button sudokuButtons[], backButton;
    public static SudokuScene sudokuScene;
    int i = 0, x = 0;
    public static int difficulty;
    public static String fileName;
    String fileNames[][] = new String[5][9];
    String beginnerNames[] = new String[10];
    String easyNames[] = new String[10];
    String mediumNames[] = new String[10];
    String hardNames[] = new String[10];
    String brutalNames[] = new String[10];
    String names[] = new String[5];
    
    SudokuListScene(VBox vBox, int difficulty) {
        super(vBox, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        names[0] = "Beginner";
        names[1] = "Easy";
        names[2] = "Medium";
        names[3] = "Hard";
        names[4] = "Brutal";
        vBox.setStyle(Sudoku.vBoxStyle);
        Label label = new Label(names[difficulty]);
        label.setStyle(Sudoku.labelStyle);
        label.setFont(Sudoku.font);
        vBox.getChildren().add(label);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(50);
        TilePane tilePane = new TilePane();
        vBox.getChildren().add(tilePane);
        SudokuListScene.difficulty = difficulty;
        for(int i = 1; i <= 10; ++i) {
            beginnerNames[i-1] = "beginner_" + i + ".sdk";
            easyNames[i-1] = "easy_" + i + ".sdk";
            mediumNames[i-1] = "medium_" + i + ".sdk";
            hardNames[i-1] = "hard_" + i + ".sdk";
            brutalNames[i-1] = "brutal_" + i + ".sdk";
        }
        fileNames[0] = beginnerNames;
        fileNames[1] = easyNames;
        fileNames[2] = mediumNames;
        fileNames[3] = hardNames;
        fileNames[4] = brutalNames;
        tilePane.setAlignment(Pos.TOP_CENTER);
        tilePane.setPrefColumns(5);
        tilePane.setPrefRows(2);
        tilePane.setStyle(Sudoku.vBoxStyle);
        tilePane.setHgap(200);
        tilePane.setVgap(200);
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case LEFT:
                        if (x < 1) {
                            x = 1;
                        }
                        sudokuButtons[x - 1].setStyle(Sudoku.selectedButtonStyle);
                        sudokuButtons[x].setStyle(Sudoku.buttonStyle);
                        backButton.setStyle(Sudoku.buttonStyle);
                        x--;
                        break;
                    case RIGHT:
                        if (x > 8) {
                            x = 8;
                        }
                        sudokuButtons[x + 1].setStyle(Sudoku.selectedButtonStyle);
                        sudokuButtons[x].setStyle(Sudoku.buttonStyle);
                        backButton.setStyle(Sudoku.buttonStyle);
                        x++;
                        break;
                    case DOWN:
                        backButton.setStyle(Sudoku.selectedButtonStyle);
                        sudokuButtons[x].setStyle(Sudoku.buttonStyle);
                        break;
                    case UP:
                        sudokuButtons[x].setStyle(Sudoku.selectedButtonStyle);
                        backButton.setStyle(Sudoku.buttonStyle);
                        break;
                    case ESCAPE:
                        backButton.fire();
                        break;
                    case BACK_SPACE:
                        backButton.fire();
                        break;
                    case ENTER:
                        sudokuButtons[x].fire();
                        break;
                }
            }
        });
        sudokuScene =  new SudokuScene(new VBox());
        sudokuButtons = new Button[10];
        for(int j = 0; j < 10; ++j) {
            final int x = j + 1;
            sudokuButtons[j] = new Button(x + "");
            sudokuButtons[j].setFont(Sudoku.font);
            sudokuButtons[j].setStyle(Sudoku.buttonStyle);
            sudokuButtons[j].setMinWidth(Button.USE_PREF_SIZE);
            sudokuButtons[j].setMaxWidth(Double.MAX_VALUE);
            sudokuButtons[j].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    fileName = "sdks" + File.separator + fileNames[difficulty][x - 1];
                    Sudoku.mainStage.setScene(sudokuScene);
                    BigGridPane.reset(new File(fileName));
                    SudokuButtonVBox.timeline.play();
                }
            });
            tilePane.getChildren().addAll(sudokuButtons[j]);
        }
        backButton = new Button("Back");
        backButton.setFont(Sudoku.font);
        backButton.setStyle(Sudoku.buttonStyle);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Sudoku.mainStage.setScene(new NewGameScene(new VBox()));
            }
        });
        vBox.getChildren().add(backButton);
//        ScrollPane sp = new ScrollPane();
//        sp.setContent(vBox);
//        VBox.setVgrow(sp, Priority.ALWAYS);
//        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
//        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
//        vBox.getChildren().add(sp);
    }
}