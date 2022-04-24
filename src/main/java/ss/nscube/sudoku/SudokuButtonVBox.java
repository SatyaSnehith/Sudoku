package ss.nscube.sudoku;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import ss.nscube.sudoku.controls.SButton;
import ss.nscube.sudoku.controls.SVBox;
import ss.nscube.sudoku.theme.StyleUpdater;
import ss.nscube.sudoku.theme.Theme;
import ss.nscube.sudoku.utils.ControlUiUtil;

public class SudokuButtonVBox extends SVBox {
    private SButton themeButton, showPopupButton;
    public SudokuButtonVBox(SudokuScene scene) {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        getChildren().add(ControlUiUtil.getButton( "Generate", event -> {
            scene.updateSudoku();
            scene.getRoot().requestFocus();
        }));
        themeButton = ControlUiUtil.getButton("Light Theme", event -> {
            Parent parent = scene.getRoot();
            if (Theme.currentTheme == 0) {
                Theme.currentTheme = 1;
                themeButton.setText("Light Theme");
            } else {
                Theme.currentTheme = 0;
                themeButton.setText("Dark Theme");
            }
            if (parent instanceof StyleUpdater) ((StyleUpdater) parent).refresh();
            scene.getRoot().requestFocus();
        });
        getChildren().add(themeButton);
        showPopupButton = ControlUiUtil.getButton("Show Popup", event -> {
            if (scene.isShowPopup()) {
                scene.setShowPopup(false);
                showPopupButton.setText("Show Popup");
            } else {
                scene.setShowPopup(true);
                showPopupButton.setText("Hide Popup");
            }
            scene.getRoot().requestFocus();
        });
        getChildren().add(showPopupButton);
        getChildren().add(ControlUiUtil.getButton("Exit", event -> SudokuApplication.close()));
    }
}
