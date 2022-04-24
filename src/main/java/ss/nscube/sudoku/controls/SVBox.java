package ss.nscube.sudoku.controls;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import ss.nscube.sudoku.theme.Style;
import ss.nscube.sudoku.theme.StyleUpdater;
import ss.nscube.sudoku.theme.Theme;

public class SVBox extends VBox implements StyleUpdater {
    Style[] style = null;

    public SVBox() {
        super();
        updateStyle(Theme.BOX_STYLE);
    }

    @Override
    public void updateStyle(String styleName) {
        style = Theme.getStyle(styleName);
        refresh();
    }

    @Override
    public void refresh() {
        setStyle(Theme.getCSS(style));
        ObservableList<Node> list = getChildrenUnmodifiable();
        for (Node i : list) {
            if (i instanceof StyleUpdater) ((StyleUpdater)i).refresh();
        }
    }
}