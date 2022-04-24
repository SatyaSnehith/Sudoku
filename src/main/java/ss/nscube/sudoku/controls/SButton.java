package ss.nscube.sudoku.controls;

import javafx.scene.control.Button;
import ss.nscube.sudoku.theme.Style;
import ss.nscube.sudoku.theme.StyleUpdater;
import ss.nscube.sudoku.theme.Theme;

public class SButton extends Button implements StyleUpdater {
    Style[] style = null;
    public SButton(String text) {
        super(text);
        setFont(Theme.smallFont);
        updateStyle(Theme.BUTTON_STYLE);
        setPrefWidth(300);
        setPrefHeight(70);
    }

    public void select(boolean selection) {
        updateStyle(selection ? Theme.BUTTON_STYLE : Theme.UNSELECT_BUTTON_STYLE);
    }

    @Override
    public void updateStyle(String styleName) {
        style = Theme.getStyle(styleName);
        refresh();
    }

    @Override
    public void refresh() {
        setStyle(Theme.getCSS(style));
    }
}