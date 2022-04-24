package ss.nscube.sudoku.controls;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import ss.nscube.sudoku.theme.Style;
import ss.nscube.sudoku.theme.StyleUpdater;
import ss.nscube.sudoku.theme.Theme;

public class SFlowPane extends FlowPane implements StyleUpdater {
    Style[] style = null;

    public SFlowPane() {
        super();
        updateStyle(Theme.TRANSPARENT_STYLE);
    }

    public SFlowPane(int vGap, int hGap) {
        super(vGap, hGap);
        updateStyle(Theme.TRANSPARENT_STYLE);

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