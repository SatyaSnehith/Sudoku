package sudoku;

import java.io.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.stage.FileChooser;
// import javafx.*;
public class SudokuSaverButtonVBox extends VBox {
    Button saveButton, openButton, clearButton, solveButton, backButton;
    public SudokuSaverButtonVBox() {
        //toggleGroup = new ToggleGroup();
        setSpacing(10);
        TextField textField = new TextField();
        textField.setPromptText("Enter Name of the Sudoku File:");
        getChildren().add(textField);
        saveButton = new Button("Save");
        saveButton.setFont(Sudoku.font);
        saveButton.setStyle(Sudoku.buttonStyle);
        saveButton.setMinWidth(Button.USE_PREF_SIZE);
        saveButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Serialize(new Question(SudokuSaverBigGridPane.n), new File(textField.getText()));

            }
        });
        getChildren().add(saveButton);
        openButton = new Button("Open");
        openButton.setFont(Sudoku.font);
        openButton.setStyle(Sudoku.buttonStyle);
        openButton.setMinWidth(Button.USE_PREF_SIZE);
        openButton.setMaxWidth(Double.MAX_VALUE);
        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Sudoku File");
                File file = fileChooser.showOpenDialog(Sudoku.mainStage);
                Deserialize d = new Deserialize(file);
                Question q = (Question) d.getObject();
                SudokuSaverBigGridPane.n = q.numbers;
                for(int i = 0; i < 9; ++i) {
                    for(int j = 0; j < 9; ++j) {
                        if(q.numbers[i][j] != 0)
                            SudokuSaverBigGridPane.button[i][j].setText(q.numbers[i][j] + "");
                        else
                            SudokuSaverBigGridPane.button[i][j].setText("  ");
                    }
                }

            }
        });
        getChildren().add(openButton);
        clearButton = new Button("Clear");
        clearButton.setFont(Sudoku.font);
        clearButton.setStyle(Sudoku.buttonStyle);
        clearButton.setMinWidth(Button.USE_PREF_SIZE);
        clearButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //toggleGroup.getSelectedToggle().setSelected(false);
                for(int i = 0; i < 9; ++i) {
                    for(int j = 0; j < 9; ++j) {
                        SudokuSaverBigGridPane.n[i][j] = 0;
                        SudokuSaverBigGridPane.button[i][j].setText("  ");
                    }
                }
            }
        });
        getChildren().add(clearButton);
        solveButton = new Button("Solve");
        solveButton.setFont(Sudoku.font);
        solveButton.setStyle(Sudoku.buttonStyle);
        solveButton.setMinWidth(Button.USE_PREF_SIZE);
        solveButton.setMaxWidth(Double.MAX_VALUE);
        solveButton.setOnAction(event -> {
        });
        getChildren().add(solveButton);
        backButton = new Button("Back");
        backButton.setFont(Sudoku.font);
        backButton.setStyle(Sudoku.buttonStyle);
        backButton.setMinWidth(Button.USE_PREF_SIZE);
        backButton.setMaxWidth(Double.MAX_VALUE);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sudoku.mainStage.setScene(new MainScene(new VBox()));
            }
        });
        getChildren().add(backButton);
    }
}
