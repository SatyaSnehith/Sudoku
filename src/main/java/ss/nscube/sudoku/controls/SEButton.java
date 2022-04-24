package ss.nscube.sudoku.controls;

import javafx.scene.control.Button;
import ss.nscube.sudoku.theme.Style;
import ss.nscube.sudoku.theme.StyleUpdater;
import ss.nscube.sudoku.theme.Theme;

public class SEButton extends Button implements StyleUpdater {
    Style[] style = null;
    boolean isEditable = true;
//    boolean isCorrect = true;
    String currentStyle;

    public SEButton(String text) {
        super(text);
        setPrefSize(70, 70);
        setFont(Theme.bigFont);
        updateStyle(Theme.E_BUTTON_STYLE);
    }

    public void select() {
        updateStyle(Theme.SELECT_E_BUTTON_STYLE);
    }

    public void disable() {
        isEditable = false;
        updateStyle(Theme.DISABLE_E_BUTTON_STYLE);
    }

    public void blank() {
        isEditable = true;
        updateStyle(Theme.E_BUTTON_STYLE);
    }

//    public void isCorrect(boolean correct) {
//        isCorrect = correct;
//        if (isCorrect) {
//            updateStyle(currentStyle);
//        } else {
//            updateStyle(Theme.WRONG_E_BUTTON_STYLE);
//        }
//    }

    public void hint() {
        updateStyle(Theme.HINT_E_BUTTON_STYLE);
    }

    public void normal() {
        if (isEditable) blank();
        else disable();
    }

    @Override
    public void updateStyle(String styleName) {
//        currentStyle = isCorrect ? styleName : Theme.WRONG_E_BUTTON_STYLE;
        currentStyle = styleName;
        style = Theme.getStyle(currentStyle);
        refresh();
    }

    @Override
    public void refresh() {
        setStyle(Theme.getCSS(style));
    }
}
