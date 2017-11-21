/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;

/**
 *
 * @author satyasnehith
 */
public class CustomThemeScene extends Scene {
    String colors[];
    Label label[];
    TextField textField[];
    GridPane gridPane;
    Button applyButton, backButton;
    ChoiceBox choiceBox[];
    Rectangle rectangle[];
    public CustomThemeScene(VBox vBox) {
        super(vBox, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        String color = "AliceBlue AntiqueWhite Aqua Aquamarine Azure Beige Bisque Black BlanchedAlmond Blue BlueViolet Brown BurlyWood CadetBlue Chartreuse Chocolate Coral CornflowerBlue Cornsilk Crimson Cyan DarkBlue DarkCyan DarkGoldenRod DarkGray DarkGrey DarkGreen DarkKhaki DarkMagenta DarkOliveGreen Darkorange DarkOrchid DarkRed DarkSalmon DarkSeaGreen DarkSlateBlue DarkSlateGray DarkSlateGrey DarkTurquoise DarkViolet DeepPink DeepSkyBlue DimGray DimGrey DodgerBlue FireBrick FloralWhite ForestGreen Fuchsia Gainsboro GhostWhite Gold GoldenRod Gray Grey Green GreenYellow HoneyDew HotPink IndianRed Indigo Ivory Khaki Lavender LavenderBlush LawnGreen LemonChiffon LightBlue LightCoral LightCyan LightGoldenRodYellow LightGray LightGrey LightGreen LightPink LightSalmon LightSeaGreen LightSkyBlue LightSlateGray LightSlateGrey LightSteelBlue LightYellow Lime LimeGreen Linen Magenta Maroon MediumAquaMarine MediumBlue MediumOrchid MediumPurple MediumSeaGreen MediumSlateBlue MediumSpringGreen MediumTurquoise MediumVioletRed MidnightBlue MintCream MistyRose Moccasin NavajoWhite Navy OldLace Olive OliveDrab Orange OrangeRed Orchid PaleGoldenRod PaleGreen PaleTurquoise PaleVioletRed PapayaWhip PeachPuff Peru Pink Plum PowderBlue Purple Red RosyBrown RoyalBlue SaddleBrown Salmon SandyBrown SeaGreen SeaShell Sienna Silver SkyBlue SlateBlue SlateGray SlateGrey Snow SpringGreen SteelBlue Tan Teal Thistle Tomato Turquoise Violet Wheat White WhiteSmoke Yellow YellowGreen";       vBox.setStyle(Sudoku.vBoxStyle);
        colors = color.split(" ");
        ArrayList<String> list = new ArrayList<String>();
        for(String s: colors) {
            list.add(s);
        }
        textField = new TextField[2];
        textField[0] = new TextField();
        textField[0].setPromptText("Enter a Number.");
        textField[1] = new TextField();
        textField[1].setPromptText("Enter a Number.");
        rectangle = new Rectangle[4];
        choiceBox = new ChoiceBox[4];
        for(int i = 0; i < 4; ++i) {
            int k = i;
            rectangle[i] = new Rectangle(20, 20);
            choiceBox[i] = new ChoiceBox();
            choiceBox[i].getSelectionModel().select(0);
            choiceBox[i].setStyle(Sudoku.buttonStyle);
            choiceBox[i].setItems(FXCollections.observableArrayList(list));
            choiceBox[i].getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    rectangle[k].setFill(Color.valueOf(colors[(int)number2]));
                }
              });
        //    choiceBox[i].setTooltip(new Tooltip(label[i].getText()));
        }
        label = new Label[6];            
        label[0] = new Label("Text in Button:");
        label[0].setStyle(Sudoku.labelStyle);
        label[0].setFont(Sudoku.font);
        label[1] = new Label("Background:");
        label[1].setStyle(Sudoku.labelStyle);
        label[1].setFont(Sudoku.font);
        label[2] = new Label("Background in Button:");
        label[2].setStyle(Sudoku.labelStyle);
        label[2].setFont(Sudoku.font);
        label[3] = new Label("Text:");
        label[3].setStyle(Sudoku.labelStyle);
        label[3].setFont(Sudoku.font);
        label[4] = new Label("Button Radius:");
        label[4].setStyle(Sudoku.labelStyle);
        label[4].setFont(Sudoku.font);
        label[5] = new Label("Selected Button Radius:");
        label[5].setStyle(Sudoku.labelStyle);
        label[5].setFont(Sudoku.font);
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(label[0], 0, 0);
        gridPane.add(choiceBox[0], 1, 0);
        gridPane.add(rectangle[0], 2, 0);
        gridPane.add(label[1], 0, 1);
        gridPane.add(choiceBox[1], 1, 1 );
        gridPane.add(rectangle[1], 2, 1);
        gridPane.add(label[2], 0, 2);
        gridPane.add(choiceBox[2], 1, 2);
        gridPane.add(rectangle[2], 2, 2);
        gridPane.add(label[3], 0, 3);
        gridPane.add(choiceBox[3], 1, 3);
        gridPane.add(rectangle[3], 2, 3);
        gridPane.add(label[4], 0, 4);
        gridPane.add(textField[0], 1, 4);
        gridPane.add(label[5], 0, 5);
        gridPane.add(textField[1], 1, 5);
        vBox.getChildren().add(gridPane);
        vBox.setAlignment(Pos.CENTER);
        applyButton = new Button("Apply");
        applyButton.setStyle(Sudoku.buttonStyle);
        applyButton.setFont(Sudoku.font);
        applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int sbr = 0, br = 0;
                try {
                    br = Integer.parseInt(textField[0].getText());
                    sbr = Integer.parseInt(textField[1].getText());
                } catch(Exception ex) {
                    textField[0].setText("");
                    textField[1].setText("");
                }
                Sudoku.labelStyle = "-fx-text-fill: " + colors[choiceBox[3].getSelectionModel().getSelectedIndex()] + ";";
                Sudoku.buttonStyle = "-fx-background-color:" + colors[choiceBox[2].getSelectionModel().getSelectedIndex()]+ ";"
                   + "-fx-background-radius: " + br +";"
                   + "-fx-background-insets: 1,2,3;"
                    + "-fx-text-fill: " + colors[choiceBox[0].getSelectionModel().getSelectedIndex()] + ";"
                    + "-fx-spacing: 40;";
                Sudoku.selectedButtonStyle = "-fx-background-color:" + colors[choiceBox[0].getSelectionModel().getSelectedIndex()] + ";"
                        + "-fx-background-radius:" + sbr +";"
                        + "-fx-background-insets: 1,2,3;"
                         + "-fx-text-fill:" + colors[choiceBox[2].getSelectionModel().getSelectedIndex()] + ";";;
                Sudoku.vBoxStyle = "-fx-background-color:" + colors[choiceBox[1].getSelectionModel().getSelectedIndex()] + ";";
            }
        });
        backButton = new Button("Back");
        backButton.setStyle(Sudoku.buttonStyle);
        backButton.setFont(Sudoku.font);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Sudoku.mainStage.setScene(new ThemesScene(new VBox()));
            }
        });
        gridPane.add(applyButton, 0, 6);
        gridPane.add(backButton, 1, 6);
        gridPane.setAlignment(Pos.CENTER);
    }
    public static void main(String[] args) {}
}
