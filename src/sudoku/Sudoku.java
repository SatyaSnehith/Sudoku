package sudoku;

import java.io.*;
import javafx.scene.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.text.*;
public class Sudoku extends Application {
    public static Stage mainStage;
    public static int number;
    public static File file;
    public static char[] e = new char[9];
    public static String fontFamily = "DejaVu Sans Mono";
    public static int fontSize = 30;
    public static Font font = new Font(fontFamily, fontSize);
    public static String labelStyle = "-fx-text-fill: " + "white" + ";";
    public static String buttonStyle = "-fx-background-color:" + "white" + ";"
                       + "-fx-background-radius: " + 0 +";"
                       + "-fx-background-insets: 1,2,3;"
                        + "-fx-text-fill: " + "black" + ";"
                        + "-fx-spacing: 40;";
    public static String selectedButtonStyle = "-fx-background-color:" + "black" + ";"
                            + "-fx-background-radius:" + 0 +";"
                            + "-fx-background-insets: 1,2,3;"
                             + "-fx-text-fill:" + "white" + ";";;
    public static String vBoxStyle = "-fx-background-color:" + "black" + ";";
        Screen screen = Screen.getPrimary();
        Rectangle2D r = screen.getVisualBounds();

	public static void main(String[] args) {
            launch(args);
	}
	@Override
	public void start(Stage stage) {
		mainStage = stage;
                VBox vBox = new VBox();
                vBox.setSpacing(100);
		stage.setScene(new MainScene(new VBox()));
                stage.setMaximized(true);
		//stage.initStyle(StageStyle.UTILITY);
		//stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Sudoku");
		stage.show();
	}
}
