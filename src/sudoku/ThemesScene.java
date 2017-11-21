/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 *
 * @author satyasnehith
 */
public class ThemesScene extends Scene {
    Button button[];
    int x = 0;
    public ThemesScene(VBox vBox) {
        super(vBox, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        vBox.setStyle(Sudoku.vBoxStyle);
        vBox.setAlignment(Pos.CENTER);
        double d = (Screen.getPrimary().getVisualBounds().getWidth()/3);
        vBox.setSpacing(10);
        this.setOnKeyPressed(new EventHandler<KeyEvent> () {
            @Override
            public void handle(KeyEvent e) {
                switch(e.getCode()){
                    case UP:
                        if(x < 1)
                            x = 1;
                        button[x-1].setStyle(Sudoku.selectedButtonStyle);
                        button[x].setStyle(Sudoku.buttonStyle);
                        x--;
                        break;
                    case DOWN:
                        if(x > 2)
                            x = 2;
                        button[x+1].setStyle(Sudoku.selectedButtonStyle);
                        button[x].setStyle(Sudoku.buttonStyle);
                        x++;
                        break;
                    case ESCAPE:
                        button[3].fire();
                        break;
                    case BACK_SPACE:
                        button[3].fire();
                        break;

                    case ENTER:
                        button[x].fire();
                        break;
                }
            }
        });
        button = new Button[4];
        button[0] = new Button("WhiteAndBlack Theme");
        button[0].setFont(Sudoku.font);
        button[0].setStyle(Sudoku.buttonStyle);
        button[0].setMinWidth(Button.USE_PREF_SIZE);
        button[0].setMaxWidth(d);
        button[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String white = "white";
                String black = "black";
                Sudoku.labelStyle = "-fx-text-fill: " + black + ";";
                Sudoku.buttonStyle = "-fx-background-color:" + black+ ";"
                   + "-fx-background-radius: " + 0 +";"
                   + "-fx-background-insets: 1,2,3;"
                    + "-fx-text-fill: " + white + ";"
                    + "-fx-spacing: 40;";
                Sudoku.selectedButtonStyle = "-fx-background-color:" + white + ";"
                        + "-fx-background-radius:" + 0 +";"
                        + "-fx-background-insets: 1,2,3;"
                         + "-fx-text-fill:" + black + ";";;
                Sudoku.vBoxStyle = "-fx-background-color:" + white + ";";
            }
        });
        vBox.getChildren().add(button[0]);
        button[1] = new Button("BlackAndWhite Theme");
        button[1].setFont(Sudoku.font);
        button[1].setStyle(Sudoku.buttonStyle);
        button[1].setMinWidth(Button.USE_PREF_SIZE);
        button[1].setMaxWidth(d);
        button[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String white = "white";
                String black = "black";
                Sudoku.labelStyle = "-fx-text-fill: " + white + ";";
                Sudoku.buttonStyle = "-fx-background-color:" + white+ ";"
                   + "-fx-background-radius: " + 0 +";"
                   + "-fx-background-insets: 1,2,3;"
                    + "-fx-text-fill: " + black + ";"
                    + "-fx-spacing: 40;";
                Sudoku.selectedButtonStyle = "-fx-background-color:" + black + ";"
                        + "-fx-background-radius:" + 0 +";"
                        + "-fx-background-insets: 1,2,3;"
                         + "-fx-text-fill:" + white + ";";;
                Sudoku.vBoxStyle = "-fx-background-color:" + black + ";";
            }
        });
        vBox.getChildren().add(button[1]);
        button[2] = new Button("Custom Theme");
        button[2].setFont(Sudoku.font);
        button[2].setStyle(Sudoku.buttonStyle);
        button[2].setMinWidth(Button.USE_PREF_SIZE);
        button[2].setMaxWidth(d);
        button[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new CustomThemeScene(new VBox()));
            }
        });
        vBox.getChildren().add(button[2]);
        button[3] = new Button("Back");
        button[3].setFont(Sudoku.font);
        button[3].setStyle(Sudoku.buttonStyle);
        button[3].setMinWidth(Button.USE_PREF_SIZE);
        button[3].setMaxWidth(d);
        button[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new SettingsScene(new VBox()));
            }
        });
        vBox.getChildren().add(button[3]);
    }
}
