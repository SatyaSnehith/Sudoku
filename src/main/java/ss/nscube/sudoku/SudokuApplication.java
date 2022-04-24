package ss.nscube.sudoku;

import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.layout.*;

public class SudokuApplication extends javafx.application.Application {
    public static Stage mainStage;

	public static void main(String[] args) {
        launch(args);
	}

    public static void setScene(Scene scene) {
        if (mainStage != null) mainStage.setScene(scene);
    }

    public static void close() {
        if (mainStage != null) mainStage.close();
    }

	@Override
	public void start(Stage stage) {
		mainStage = stage;
        VBox vBox = new VBox();
        vBox.setSpacing(100);
		stage.setScene(SudokuScene.getInstance());
        stage.setMaximized(true);
        stage.setTitle("Sudoku");
		stage.show();
	}
}
