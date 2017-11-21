package sudoku;

import java.io.File;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.*;
public class MainScene extends Scene {
    static Button button[];
    int x = 0;
    public MainScene(VBox vBox) { 
        super(vBox, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        vBox.setStyle(Sudoku.vBoxStyle);
        vBox.setAlignment(Pos.CENTER);
        double d = Screen.getPrimary().getVisualBounds().getWidth()/3;
        vBox.setSpacing(10);
        vBox.setMaxWidth(200);
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
                        if(x > 3)
                            x = 3;
                        button[x+1].setStyle(Sudoku.selectedButtonStyle);
                        button[x].setStyle(Sudoku.buttonStyle);
                        x++;
                        break;
                    case ENTER:
                        button[x].fire();
                        break;
                }
            }
        });
        button = new Button[5];
        button[0] = new Button("NewGame");
        button[0].setFont(Sudoku.font);
        button[0].setStyle(Sudoku.buttonStyle);
        button[0].setMinWidth(Button.USE_PREF_SIZE);
        button[0].setMaxWidth(d);
        button[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new NewGameScene(new VBox()));
            }
        });
        vBox.getChildren().add(button[0]);
        button[1] = new Button("Resume");
        button[1].setStyle(Sudoku.buttonStyle);
        button[1].setFont(Sudoku.font);
        button[1].setMinWidth(Button.USE_PREF_SIZE);
        button[1].setMaxWidth(d);
        button[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new SudokuScene(new VBox()));
                SudokuButtonVBox.timeline.play();
                Deserialize dr = new Deserialize(new File("resume"));
                ResumeSerialization rs = (ResumeSerialization)dr.getObject();
                BigGridPane.reset(new File(rs.getFileName()));
                System.out.println(rs.getFileName());
//                Deserialize df = new Deserialize(new File(rs.getFileName()));
//                Question q = (Question)df.getObject();
                int n[][] = rs.getN();
//                BigGridPane.n = n;
//                BigGridPane.reset();
                for(int i = 0; i < 9; ++i)
                    for(int j = 0; j < 9; ++j) {
                        BigGridPane.n[i][j] = n[i][j];
                Values values  = (Values) new Deserialize(new File("values")).getObject();
                char a[] = (char[]) values.getE();
                        if(n[i][j] != 0)
                            BigGridPane.button[i][j].setText(a[BigGridPane.n[i][j] - 1] + "");
                        else
                            BigGridPane.button[i][j].setText(" ");
                    }
                SudokuScene.sudokuButtonVBox.mins = rs.getMins();
                SudokuScene.sudokuButtonVBox.secs = rs.getSecs();
                
            }
        });
        vBox.getChildren().add(button[1]);
        button[2] = new Button("Settings");
        button[2].setFont(Sudoku.font);
        button[2].setStyle(Sudoku.buttonStyle);
        button[2].setMinWidth(Button.USE_PREF_SIZE);
        button[2].setMaxWidth(d);
        button[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new SettingsScene(new VBox()));
            }
        });
        vBox.getChildren().add(button[2]);
        button[3] = new Button("Create");
        button[3].setFont(Sudoku.font);
        button[3].setStyle(Sudoku.buttonStyle);
        button[3].setMinWidth(Button.USE_PREF_SIZE);
        button[3].setMaxWidth(d);
        button[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new SudokuSaverScene(new VBox()));
            }
        });
        vBox.getChildren().add(button[3]);
        button[4] = new Button("Exit");
        button[4].setFont(Sudoku.font);
        button[4].setStyle(Sudoku.buttonStyle);
        button[4].setMinWidth(Button.USE_PREF_SIZE);
        button[4].setMaxWidth(d);
        button[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.close();
            }
        });
        vBox.getChildren().add(button[4]);
    }
    public static void main(String[] args) {}
}
