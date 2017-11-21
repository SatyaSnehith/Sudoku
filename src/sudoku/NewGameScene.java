package sudoku;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.*;

public class NewGameScene extends Scene {
    TilePane tilePane;
    Button button[], backButton;
    int x = 0;
    public NewGameScene(VBox vBox) {
        super(vBox, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setHgap(30);
        tilePane.setMinWidth(TilePane.USE_PREF_SIZE);
        this.setOnKeyPressed(new EventHandler<KeyEvent> () {
            @Override
            public void handle(KeyEvent e) {
                switch(e.getCode()){
                    case LEFT:
                        if(x < 1)
                            x = 1;
                        button[x-1].setStyle(Sudoku.selectedButtonStyle);
                        button[x].setStyle(Sudoku.buttonStyle);
                        backButton.setStyle(Sudoku.buttonStyle);
                        x--;
                        break;
                    case RIGHT:
                        if(x > 3)
                            x = 3;
                        button[x+1].setStyle(Sudoku.selectedButtonStyle);
                        button[x].setStyle(Sudoku.buttonStyle);
                        backButton.setStyle(Sudoku.buttonStyle);
                        x++;
                        break;
                    case DOWN:
                        backButton.setStyle(Sudoku.selectedButtonStyle);
                        button[x].setStyle(Sudoku.buttonStyle);
                        break;
                    case UP:
                        button[x].setStyle(Sudoku.selectedButtonStyle);
                        backButton.setStyle(Sudoku.buttonStyle);
                        break;
                    case ESCAPE:
                        backButton.fire();
                        break;
                    case BACK_SPACE:
                        backButton.fire();
                        break;
                    case ENTER:
                        button[x].fire();
                        break;
                }
            }
        });
        button = new Button[6];
        button[0] = new Button("Beginner");
        button[0].setStyle(Sudoku.buttonStyle);
        button[0].setFont(Sudoku.font);
        button[0].setMinWidth(Button.USE_PREF_SIZE);
        button[0].setMaxWidth(Double.MAX_VALUE);
        button[0].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Sudoku.mainStage.setScene(new SudokuListScene(new VBox(), 0));
                }
        });
        tilePane.getChildren().add(button[0]);
        button[1] = new Button("Easy");
        button[1].setStyle(Sudoku.buttonStyle);
        button[1].setFont(Sudoku.font);
        button[1].setMinWidth(Button.USE_PREF_SIZE);
        button[1].setMaxWidth(Double.MAX_VALUE);
        button[1].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Sudoku.mainStage.setScene(new SudokuListScene(new VBox(), 1));
                }
            });
        tilePane.getChildren().add(button[1]);
        button[2] = new Button("Medium");
        button[2].setStyle(Sudoku.buttonStyle);
        button[2].setFont(Sudoku.font);
        button[2].setMinWidth(Button.USE_PREF_SIZE);
        button[2].setMaxWidth(Double.MAX_VALUE);
        button[2].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Sudoku.mainStage.setScene(new SudokuListScene(new VBox(), 2));
                }
            });
        tilePane.getChildren().add(button[2]);
        button[3] = new Button("Hard");
        button[3].setStyle(Sudoku.buttonStyle);
        button[3].setFont(Sudoku.font);
        button[3].setMinWidth(Button.USE_PREF_SIZE);
        button[3].setMaxWidth(Double.MAX_VALUE);
        button[3].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Sudoku.mainStage.setScene(new SudokuListScene(new VBox(), 3));
                }
            });
        tilePane.getChildren().add(button[3]);
        button[4] = new Button("Brutal");
        button[4].setStyle(Sudoku.buttonStyle);
        button[4].setFont(Sudoku.font);
        button[4].setMinWidth(Button.USE_PREF_SIZE);
        button[4].setMaxWidth(Double.MAX_VALUE);
        button[4].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Sudoku.mainStage.setScene(new SudokuListScene(new VBox(), 4));
                }
            });
        tilePane.getChildren().add(button[4]);
        vBox.getChildren().add(tilePane);
        backButton = new Button("Back");
        backButton.setStyle(Sudoku.buttonStyle);
        backButton.setFont(Sudoku.font);
        backButton.setMinWidth(Button.USE_PREF_SIZE);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new MainScene(new VBox()));
            }
        });
        vBox.setStyle(Sudoku.vBoxStyle);
        vBox.setSpacing(200);
        vBox.getChildren().add(backButton);
        vBox.setAlignment(Pos.CENTER);
    }
    public static void main(String[] args) {}
}
