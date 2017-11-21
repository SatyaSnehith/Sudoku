package sudoku;

import java.io.File;
import javafx.animation.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.util.*;
public class SudokuButtonVBox extends VBox {
    public static boolean subsetActivated = false;
    public static int mins = 0, secs = 0;
    Button pauseButton, subsetButton, checkButton, backButton;
    public static Color labelColor = Color.WHITE;
    static boolean pause = true;
    public static Timeline timeline;
    void change(Label label) {
        if(secs % 60 == 0 && secs != 0) {
                ++mins;
                secs = 0;
        }
        label.setText(ifSingleDigit(mins) + mins + ":" + ifSingleDigit(secs) + secs++);
    }
    String ifSingleDigit(int number) {
        if(number / 10 == 0)
                return "0";
        return "";
    }
    public SudokuButtonVBox() {
        setSpacing(10);
        double d = Screen.getPrimary().getVisualBounds().getWidth()/3;
        Label label = new Label();
        label.setTextFill(labelColor);
        label.setFont(Sudoku.font);
        label.setStyle(Sudoku.labelStyle);
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
                            change(label);
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);
        getChildren().add(label);
        pauseButton = new Button("Pause");
        pauseButton.setFont(Sudoku.font);
        pauseButton.setStyle(Sudoku.buttonStyle);
        pauseButton.setMinWidth(Button.USE_PREF_SIZE);
        pauseButton.setMaxWidth(d);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(pause) {
                    timeline.stop();
                    SudokuListScene.sudokuScene.bgp.setVisible(false);
//                    System.out.println("x = " + SudokuListScene.sudokuScene.bgp.getBoundsInLocal().getWidth() + "y = " +  SudokuListScene.sudokuScene.bgp.getBoundsInLocal().getWidth());
                    pauseButton.setText("Play");
                    pause = false;
                } else {
                    timeline.play();
                    SudokuListScene.sudokuScene.bgp.setVisible(true);
                    pauseButton.setText("Pause");
                    pause = true;
                }
            }
        });
        getChildren().add(pauseButton);
        subsetButton = new Button("Subset");
        subsetButton.setFont(Sudoku.font);
        subsetButton.setStyle(Sudoku.buttonStyle);
        subsetButton.setMinWidth(Button.USE_PREF_SIZE);
        subsetButton.setMaxWidth(d);
        subsetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(subsetActivated) {
                    subsetButton.setStyle(Sudoku.buttonStyle);
                    for(int i = 0; i < 9; ++i) {
                        for(int j = 0; j < 9; ++j) {
                            if(BigGridPane.n[i][j] == 0)
                                BigGridPane.button[i][j].setText(" ");
                            BigGridPane.button[i][j].setFont(Sudoku.font);
                        }
                    }
                    subsetActivated = false;
                } else {
                    subsetButton.setStyle(Sudoku.selectedButtonStyle);
                    for(int i = 0; i < 9; ++i) {
                        for(int j = 0; j < 9; ++j) {
                            if(BigGridPane.n[i][j] == 0) {
                                BigGridPane.button[i][j].setFont(new Font(Sudoku.fontFamily ,12));
                            } else
                                BigGridPane.button[i][j].setFont(Sudoku.font);
                        }
                    }
                    subsetActivated = true;
                }
            }
        });
        getChildren().add(subsetButton);
        checkButton = new Button("Check");
        checkButton.setFont(Sudoku.font);
        checkButton.setStyle(Sudoku.buttonStyle);
        checkButton.setMinWidth(Button.USE_PREF_SIZE);
        checkButton.setMaxWidth(d);
        checkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(new Check(BigGridPane.n).getResult())
                    checkButton.setText("True");
                else
                    checkButton.setText("False");
            }
        });
        getChildren().add(checkButton);
        backButton = new Button("Back");
        backButton.setFont(Sudoku.font);
        backButton.setStyle(Sudoku.buttonStyle);
        backButton.setMinWidth(Button.USE_PREF_SIZE);
        backButton.setMaxWidth(d);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline.stop();
                new Serialize(new ResumeSerialization(SudokuListScene.fileName, BigGridPane.n, mins, secs), new File("resume"));
                mins = 0;
                secs = 0;
                Sudoku.mainStage.setScene(new SudokuListScene(new VBox(), SudokuListScene.difficulty));
            }
        });
        getChildren().add(backButton);
    }
}
