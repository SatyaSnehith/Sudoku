package ss.nscube.sudoku;

import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ss.nscube.sudoku.controls.SFlowPane;
import ss.nscube.sudoku.controls.SVBox;
import ss.nscube.sudoku.utils.SudokuUtil;
import ss.nscube.sudoku.utils.UiUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SudokuScene extends Scene {
    private boolean showPopup = false;
    private BigGridPane bgp;

    public SudokuScene(SVBox vBox) {
        super(vBox, UiUtil.WINDOW_WIDTH, UiUtil.WINDOW_HEIGHT);
        vBox.requestFocus();
        vBox.setOnKeyPressed(e -> bgp.handleKey(e.getCode()));
        vBox.setOnMouseClicked(event -> vBox.requestFocus());
        vBox.requestFocus();
        vBox.setSpacing(100);
        try {
            InputStream inputStream = Objects.requireNonNull(SudokuApplication.class.getClassLoader().getResource("images/sudoku.png")).openConnection().getInputStream();
            vBox.getChildren().add(new ImageView(new Image(inputStream)));
        } catch (IOException ignored) {}
        SFlowPane flowPane = new SFlowPane(100, 100);
        bgp = new BigGridPane(this);
        SudokuButtonVBox sudokuButtonVBox = new SudokuButtonVBox(this);
        flowPane.getChildren().add(bgp);
        flowPane.getChildren().add(sudokuButtonVBox);
        vBox.getChildren().add(flowPane);
        vBox.setAlignment(Pos.CENTER);
        flowPane.setAlignment(Pos.CENTER);
        updateSudoku();
    }

    void updateSudoku() {
        bgp.update(SudokuUtil.generateSudoku());
    }

    static SudokuScene sudokuScene = null;
    public static SudokuScene getInstance() {
        if (sudokuScene == null) {
            sudokuScene = new SudokuScene(new SVBox());
        }
        return sudokuScene;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }
}