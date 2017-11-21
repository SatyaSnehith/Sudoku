package sudoku;

import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
/**
 *
 * @author satya
 */
public class SettingsScene extends Scene {
    Button button[];
    int x = 0;
    
    public SettingsScene(VBox vBox) {
        super(vBox, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        double d = (Screen.getPrimary().getVisualBounds().getWidth()/3);
        vBox.setSpacing(10);
        vBox.setStyle(Sudoku.vBoxStyle);
        vBox.setAlignment(Pos.CENTER);
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
                        if(x > 1)
                            x = 1;
                        button[x+1].setStyle(Sudoku.selectedButtonStyle);
                        button[x].setStyle(Sudoku.buttonStyle);
                        x++;
                        break;
                    case ESCAPE:
                        button[2].fire();
                        break;
                    case BACK_SPACE:
                        button[2].fire();
                        break;

                    case ENTER:
                        button[x].fire();
                        break;
                }
            }
        });
        button = new Button[3];
        button[0] = new Button("ChangeElements");
        button[0].setFont(Sudoku.font);
        button[0].setStyle(Sudoku.buttonStyle);
        button[0].setMinWidth(Button.USE_PREF_SIZE);
        button[0].setMaxWidth(d);
        button[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new AlphabeticalSudokuScene(new VBox()));
            }
        });
        vBox.getChildren().add(button[0]);
        button[1] = new Button("Themes");
        button[1].setFont(Sudoku.font);
        button[1].setStyle(Sudoku.buttonStyle);
        button[1].setMinWidth(Button.USE_PREF_SIZE);
        button[1].setMaxWidth(d);
        button[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new ThemesScene(new VBox()));
            }
        });
        vBox.getChildren().add(button[1]);
        button[2] = new Button("Back");
        button[2].setFont(Sudoku.font);
        button[2].setStyle(Sudoku.buttonStyle);
        button[2].setMinWidth(Button.USE_PREF_SIZE);
        button[2].setMaxWidth(d);
        button[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new MainScene(new VBox()));
            }
        });
        vBox.getChildren().add(button[2]);

    }
}
