package sudoku;

import java.io.File;
import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;

/**
 *
 * @author satyasnehith
 */
public class AlphabeticalSudokuScene extends Scene {
    char a[] = new char[9];
    final ToggleGroup toggleGroup = new ToggleGroup();
    ToggleButton toggleButton[] = new ToggleButton[9];
    Label label[];
    Button applyButton, backButton;
    GridPane gridPane;
    int x = 0;
    public AlphabeticalSudokuScene(VBox vBox) {
        super(vBox, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle(Sudoku.vBoxStyle);
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        label = new Label[9];
        char b[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        a = b;
        for(int i = 0; i < 9; ++i) {
            toggleButton[i] = new ToggleButton(" ");
            toggleButton[i].setToggleGroup(toggleGroup);
            toggleButton[i].setFont(Sudoku.font);
            toggleButton[i].setStyle(Sudoku.buttonStyle);
            label[i] = new Label("Element " + (i+1) + " : ");
            label[i].setStyle(Sudoku.labelStyle);
            gridPane.add(label[i], 0, i);
            gridPane.add(toggleButton[i], 1, i);
        }
        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                ToggleButton ntb = (ToggleButton)new_toggle;
                ToggleButton otb = (ToggleButton)old_toggle;
                    try {
                        if (new_toggle.isSelected()) {
                            ntb.setStyle(Sudoku.selectedButtonStyle);
                            otb.setStyle(Sudoku.buttonStyle);
                        }
                    } catch(NullPointerException npe) {
                        System.out.println("No Old ToggleButton");
                    }

              if (toggleGroup.getSelectedToggle() != null) {
                setOnKeyTyped(new EventHandler<KeyEvent> () {
                    @Override
                    public void handle(KeyEvent e) {
                        ToggleButton tb = (ToggleButton)toggleGroup.getSelectedToggle();
                        try {
                            tb.setText(e.getCharacter());
                        } catch(Exception ex) {
                            tb.setText(" ");
                        }
                        for(int z = 0; z < 9; ++z) {
                            if(tb == toggleButton[z])
                                a[z] = e.getCharacter().charAt(0);
                        }
                        switch(e.getCode()){
                            case UP:
                                if(x < 1)
                                    x = 1;
                                toggleButton[x-1].setStyle(Sudoku.selectedButtonStyle);
                                toggleButton[x].setStyle(Sudoku.buttonStyle);
                                x--;
                                toggleButton[x].setSelected(true);
                                break;
                            case DOWN:
                                if(x > 7)
                                    x = 7;
                                toggleButton[x+1].setStyle(Sudoku.selectedButtonStyle);
                                toggleButton[x].setStyle(Sudoku.buttonStyle);
                                x++;
                                toggleButton[x].setSelected(true);
                                break;
                        }
                    }
                });
              }
            }
        });
        vBox.getChildren().add(gridPane);
        TilePane tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setHgap(200);
        applyButton = new Button("Apply");
        applyButton.setStyle(Sudoku.buttonStyle);
        applyButton.setFont(Sudoku.font);
        applyButton.setOnAction(new EventHandler<ActionEvent> () {
            @Override
            public void handle(ActionEvent e) {
                for(int i = 0; i < 9; ++i) {
                    if(a[i] == ' ') {
                        a[i] = b[i];
                    }
                }
                Values values = new Values();
                values.setE(a);
                new Serialize(values, new File("values"));
                Sudoku.mainStage.setScene(new SettingsScene(new VBox()));
            }
        });
        tilePane.getChildren().add(applyButton);
        backButton = new Button("Back");
        backButton.setStyle(Sudoku.buttonStyle);
        backButton.setFont(Sudoku.font);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Sudoku.mainStage.setScene(new SettingsScene(new VBox()));
            }
        });
        tilePane.getChildren().add(backButton);
        vBox.getChildren().add(tilePane);
    }
    public static void main(String[] args) {}

}
