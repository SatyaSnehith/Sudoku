package ss.nscube.sudoku;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import ss.nscube.sudoku.controls.SEButton;
import ss.nscube.sudoku.controls.STilePane;
import ss.nscube.sudoku.controls.SVBox;
import ss.nscube.sudoku.utils.ControlUiUtil;

public class NumberStage extends Stage {
    public static int number;
    SVBox vBox;
    Scene scene;
    STilePane gridPane;
    public static SEButton[] b;
    public static SEButton clearButton;
    private BigGridPane bgp;
    public NumberStage(BigGridPane bgp) {
        this.bgp = bgp;
        vBox = new SVBox();
        vBox.setAlignment(Pos.CENTER);
        gridPane = new STilePane();
        gridPane.setPrefColumns(3);
        gridPane.setPrefRows(3);
        gridPane.setAlignment(Pos.CENTER);
        b = new SEButton[9];
        for(int c = 0; c < 9; ++c) {
            b[c] = ControlUiUtil.getSudokuButton( c+1 + "");
            gridPane.getChildren().add(b[c]);
        }
        vBox.getChildren().add(gridPane);
        clearButton = ControlUiUtil.getSudokuButton( "Clear");
        vBox.getChildren().add(clearButton);
        scene = new Scene(vBox);
        setScene(scene);
        initStyle(StageStyle.UNDECORATED);
        focusedProperty().addListener((ov, onHidden, onShown) -> {
            if (onHidden) close();
        });
    }

    public void show(int i, int j, double x, double y) {
        setX(x);
        setY(y);
        int q = 0, w = -1;
        for(int c = 0; c < 9; ++c) {
            int d = c;
            b[c].setOnAction(event -> {
                number = d+1;
                bgp.setValue(i, j, number);
                close();
            });
        }
        clearButton.setOnAction( event -> {
            bgp.setValue(i, j, 0);
            close();
        });

        scene.setOnKeyTyped(event -> {
            try {
                number = Integer.parseInt(event.getCharacter());
                bgp.setValue(i, j, number);
                close();
            } catch(Exception e) {
                if(event.getCharacter().equals("c")) {
                    bgp.setValue(i, j, 0);
                    close();
                } else
                    System.out.print("enter only a number");
            }
        });
        show();
    }

    public void refresh() {
        vBox.refresh();
    }

    @Override
    public void close() {
        super.close();
        bgp.requestFocus();
    }
}