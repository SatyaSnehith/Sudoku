package ss.nscube.sudoku;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ss.nscube.sudoku.controls.SFlowPane;
import ss.nscube.sudoku.controls.SVBox;
import ss.nscube.sudoku.theme.Theme;
import ss.nscube.sudoku.utils.SudokuUtil;
import ss.nscube.sudoku.utils.UiUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SudokuScene extends Scene {
    private boolean showPopup = false;
    private BigGridPane bgp;
    int mins = 0, secs = 0;
    Timeline timeline;

    Label timerLabel = new Label("00:00");

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

        timerLabel.setFont(Theme.bigFont);
        timerLabel.setStyle(Theme.getCSS(Theme.timerLabelStyle));
        timerLabel.setPadding(new Insets(0, 0, 5, 0));
        setupTimer();

        SVBox sudokuVBox = new SVBox();
        sudokuVBox.getChildren().add(timerLabel);
        sudokuVBox.getChildren().add(bgp);

        flowPane.getChildren().add(sudokuVBox);
        flowPane.getChildren().add(sudokuButtonVBox);

        vBox.getChildren().add(flowPane);
        vBox.setAlignment(Pos.CENTER);
        flowPane.setAlignment(Pos.CENTER);
        updateSudoku();
    }

    void onSudokuUpdated() {
        if (bgp.isSolved()) {
            timeline.pause();
        }
    }

    void resetTimer() {
        timeline.pause();
        mins = 0;
        secs = -1;
        timeline.play();
    }

    void setupTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> change()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.play();
    }

    void change() {
        secs++;
        if(secs == 60) {
            mins++;
            secs = 0;
        }
        timerLabel.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
                + (((secs/10) == 0) ? "0" : "") + secs);
    }

    void updateSudoku() {
        bgp.update(SudokuUtil.generateSudoku());
        resetTimer();
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