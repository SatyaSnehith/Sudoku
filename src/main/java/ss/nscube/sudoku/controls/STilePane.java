package ss.nscube.sudoku.controls;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import ss.nscube.sudoku.theme.Style;
import ss.nscube.sudoku.theme.StyleUpdater;
import ss.nscube.sudoku.theme.Theme;

public class STilePane extends TilePane implements StyleUpdater {
    Style[] style = null;

    public STilePane() {
        super();
        updateStyle(Theme.E_BUTTON_STYLE);
    }

    @Override
    public void updateStyle(String styleName) {
        style = Theme.getStyle(styleName);
        refresh();
    }

    @Override
    public void refresh() {
        setStyle(Theme.getCSS(style));
        ObservableList<Node> list = getChildren();
        for (Node i : list) if (i instanceof StyleUpdater) ((StyleUpdater)i).refresh();
    }
}