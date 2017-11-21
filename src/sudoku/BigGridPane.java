package sudoku;

import java.io.*;
import javafx.event.*;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class BigGridPane extends TilePane { 
    public static int[][] n;
    public static int[][][] sun;
    public static Button button[][];
    TilePane gridPane[][];
    public BigGridPane() {
        NumberStage.game = true;
        this.setPrefColumns(3);
        this.setPrefRows(3);
        sun = new int[9][9][9];
        for(int a[][]: sun)
            for(int b[]: a)
                for(int c: b) {
                    c = 0;
                }
        Sudoku.e[0] = '1';Sudoku.e[1] = '2';Sudoku.e[2] = '3';
        Sudoku.e[3] = '4';Sudoku.e[4] = '5';Sudoku.e[5] = '6';
        Sudoku.e[6] = '7';Sudoku.e[7] = '8';Sudoku.e[8] = '9' ;
        gridPane = new TilePane[3][3];
        button = new Button[9][9];
        setVgap(3);
        setHgap(3);
        doIt(); 
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                final int x = i, y = j;
                button[i][j] = new Button(" ");
                button[i][j].setFont(Sudoku.font);
                button[i][j].setStyle(Sudoku.buttonStyle);
                button[i][j].setMinWidth(Button.USE_PREF_SIZE);
                button[i][j].setMaxWidth(Double.MAX_VALUE);
                button[i][j].setMinHeight(Button.USE_PREF_SIZE);
                button[i][j].setMaxHeight(Double.MAX_VALUE);
                if(n[i][j] != 0) {
                    Values values  = (Values) new Deserialize(new File("values")).getObject();
                    char a[] = (char[]) values.getE();
                    button[i][j].setDisable(true);
                    button[i][j].setText(a[n[i][j]-1] + "");
                }
                button[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    new NumberStage(x, y, mouseEvent.getScreenX(), mouseEvent.getScreenY()).show();
                    }
                });
//                button[i][j].setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        //System.out.println("i = " + x + " j = " + y + " x = " + button[x][y].getLayoutX() + "y = " + button[x][y].getLayoutY());
//                    }
//                });
            }
        }
        try {
            button[0][0].setText("");
            ImageView i = new ImageView(new Image(new FileInputStream("happy.svg")));
            i.setFitHeight(20);
            i.setFitWidth(20);
            Group g = new Group(i);
            button[0][0].setStyle("-fx-background-image: url('grinning.png')");
            //button[0][0].setGraphic(g);
        } catch(Exception e) {
            System.out.print("Cannot find image");
        }
        button[0][0].setStyle(Sudoku.selectedButtonStyle);

        int x = 0, y =0;
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                gridPane[i][j] = new TilePane();
                gridPane[i][j].setPrefColumns(3);
                gridPane[i][j].setPrefRows(3);
                x = i * 3;
                y = j * 3;
                for(int k = x; k < x + 3; ++k) {
                    for(int l = y; l < y + 3; ++l) {
                        gridPane[i][j].getChildren().add(button[k][l]);
                    }
                }
                getChildren().add(gridPane[i][j]);
            }
        }

    }
    void doIt() {
//        File file = new File ("Sudoku.sudoku");
//        Deserialize d = new Deserialize(file);
//        Question q = (Question)d.getObject();
//        n = q.numbers;
     int[][] sdk = new int[9][9];
//     for(int i = 0; i < 9; ++i) {
//         for(int j = 0; j < 9; ++j) {
//             sdk[i][j] = 0;
//         }
//     }
     sdk[0][4] = 2;sdk[0][5] = 3;sdk[0][7] = 5;
     sdk[1][6] = 1;sdk[1][7] = 7;sdk[1][8] = 9;
     sdk[2][1] = 1;sdk[2][4] = 5;
     sdk[3][0] = 5;sdk[3][6] = 8;
     sdk[4][1] = 2;sdk[4][3] = 8;sdk[4][5] = 9;sdk[4][7] = 1;
     sdk[5][2] = 4;sdk[5][8] = 3;
     sdk[6][4] = 1;sdk[6][7] = 6;
     sdk[7][0] = 8;sdk[7][1] = 7;sdk[7][2] = 3;
     sdk[8][1] = 5;sdk[8][3] = 7;sdk[8][4] = 4;
     n = sdk;
    }
    public static boolean isCompleted() {
        for(int i = 0; i < 9; ++i)
            for(int j = 0; j < 9; ++j)
                if(n[i][j] == 0)
                    return false;
        return true;
    }
    public static void reset() {
        Values values  = (Values) new Deserialize(new File("values")).getObject();
        char a[] = (char[]) values.getE();
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                button[i][j].setFont(Sudoku.font);
                if(n[i][j] != 0) {
                    button[i][j].setText(a[n[i][j] - 1] + "");
                    button[i][j].setDisable(true);
                } else
                    button[i][j].setText(" ");
            }
        }
    }
    public static void reset(File file) {
        Values values  = (Values) new Deserialize(new File("values")).getObject();
        char a[] = (char[]) values.getE();
        Deserialize d = new Deserialize(file);
        Question q = (Question)d.getObject();
        n = q.numbers;
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                if(n[i][j] != 0) {
                    button[i][j].setText(a[n[i][j] - 1]  + "");
                    System.out.println(a[n[i][j] - 1] + "");
                    button[i][j].setDisable(true);
                }
                else {
                    button[i][j].setText(" ");
                    button[i][j].setDisable(false);
                }
            }
        }
    }
    public static void main(String[] args) {}
}
