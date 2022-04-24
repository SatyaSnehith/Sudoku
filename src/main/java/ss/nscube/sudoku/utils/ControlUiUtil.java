package ss.nscube.sudoku.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import ss.nscube.sudoku.controls.SButton;
import ss.nscube.sudoku.controls.SEButton;

public class ControlUiUtil {
    public static SButton getButton(String text, EventHandler<ActionEvent> value) {
        SButton button = getButton(text);
        button.setOnAction(value);
        return button;
    }
    public static SButton getButton(String text) {
        SButton button = new SButton(text);
        button.setMinWidth(Button.USE_PREF_SIZE);
        button.setMaxWidth(200);
        return button;
    }

    public static SEButton getSudokuButton(String text) {
        SEButton button = new SEButton(text);
        button.setMinWidth(Button.USE_PREF_SIZE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(Button.USE_PREF_SIZE);
        button.setMaxHeight(Double.MAX_VALUE);
        return button;
    }
}
