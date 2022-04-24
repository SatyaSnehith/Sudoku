package ss.nscube.sudoku.theme;

import javafx.scene.text.Font;
import ss.nscube.sudoku.SudokuApplication;
import ss.nscube.sudoku.utils.MatColors;

import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

import static ss.nscube.sudoku.utils.MatColors.*;

public class Theme {
    public static int currentTheme = 1;
    public static int themeCount = 2;
    public static HashMap<String, Style[]> styleMap = new HashMap<>();

    public static final String BUTTON_STYLE = "buttonStyle";
    public static Style[] buttonStyle = new Style[themeCount];

    public static final String UNSELECT_BUTTON_STYLE = "unselectButtonStyle";
    public static Style[] unselectButtonStyle = new Style[themeCount];

    public static final String E_BUTTON_STYLE = "eButtonStyle";
    public static Style[] eButtonStyle = new Style[themeCount];

    public static final String WRONG_E_BUTTON_STYLE = "wrongEButtonStyle";
    public static Style[] wrongEButtonStyle = new Style[themeCount];

    public static final String SELECT_E_BUTTON_STYLE = "SelectEButtonStyle";
    public static Style[] selectEButtonStyle = new Style[themeCount];

    public static final String HINT_E_BUTTON_STYLE = "hintEButtonStyle";
    public static Style[] hintEButtonStyle = new Style[themeCount];

    public static final String DISABLE_E_BUTTON_STYLE = "disableEButtonStyle";
    public static Style[] disableEButtonStyle = new Style[themeCount];

    public static final String LABEL_STYLE = "labelStyle";
    public static Style[] labelStyle = new Style[themeCount];

    public static final String BOX_STYLE = "boxStyle";
    public static Style[] boxStyle = new Style[themeCount];

    public static final String TRANSPARENT_STYLE = "transparentStyle";
    public static Style[] transparentStyle = new Style[themeCount];

    static final String WHITE = "#FFFFFF";
    static final String BLACK = "#000000";
    static final String DARK = "#101010";
    static final String LIGHT = WHITE;
    static final String TRANSPARENT = "transparent";
    static final String UNSELECTED_LIGHT = "#BBBBBB";
    static final String UNSELECTED_DARK = "#666666";
    static final String GREY_66 = "#666666";
    static final String GREY_40 = "#404040";
    static final String GREY_DD = "#DDDDDD";
    static final String GREY_AA = "#DDDDDD";
    static final String GREY_88 = "#888888";
    static final String DARK_GREEN = "#1C2216";
    static final String LIGHT_GREEN = "#CEE4B4";
    static final String DARK_RED = "#531515";
    static final String LIGHT_RED = "#DFA1A1";

    public static String fontFamily = "DejaVu Sans Mono";
    public static Font smallFont, bigFont;
//    public static String buttonStyle = "-fx-background-color:" + white + ";"
//            + "-fx-background-radius: " + 5 +";"
//            + "-fx-background-insets: 1,2,3;"
//            + "-fx-text-fill: " + black + ";"
//            + "-fx-spacing: 40;";
//    public static String selectedButtonStyle = "-fx-background-color:" + black + ";"
//            + "-fx-background-radius:" + 5 +";"
//            + "-fx-background-insets: 1,2,3;"
//            + "-fx-text-fill:" + white + ";";;
//    public static String vBoxStyle = "-fx-background-color:" + "#101010" + ";";

    static {
        try {
            URL url = Objects.requireNonNull(SudokuApplication.class.getClassLoader().getResource("fonts/roboto_mono_regular.ttf"));
            smallFont = Font.loadFont(url.openConnection().getInputStream(), 24);
            bigFont = Font.loadFont(url.openConnection().getInputStream(), 30);
            System.out.println("font loaded");
        } catch (Exception e) {
            System.out.println("font not loaded");
            smallFont = new Font(fontFamily, 24);
            bigFont = new Font(fontFamily, 30);
        }
        styleMap.put(BUTTON_STYLE, buttonStyle);
        styleMap.put(UNSELECT_BUTTON_STYLE, unselectButtonStyle);
        styleMap.put(E_BUTTON_STYLE, eButtonStyle);
        styleMap.put(WRONG_E_BUTTON_STYLE, wrongEButtonStyle);
        styleMap.put(SELECT_E_BUTTON_STYLE, selectEButtonStyle);
        styleMap.put(HINT_E_BUTTON_STYLE, hintEButtonStyle);
        styleMap.put(DISABLE_E_BUTTON_STYLE, disableEButtonStyle);
        styleMap.put(LABEL_STYLE, labelStyle);
        styleMap.put(BOX_STYLE, boxStyle);
        styleMap.put(TRANSPARENT_STYLE, transparentStyle);

        //light theme
        buttonStyle[0] = new Style(LIGHT_GREEN, DARK, null, null, null);
        //dark theme
        buttonStyle[1] = new Style(DARK_GREEN, LIGHT, null, null, null);

        //light theme
        transparentStyle[0] = new Style(TRANSPARENT, null, null, null, null);
        //dark theme
        transparentStyle[1] = new Style(TRANSPARENT, null, null, null, null);

        //light theme
        unselectButtonStyle[0] = new Style(UNSELECTED_LIGHT, DARK, 10, null, null);
        //dark theme
        unselectButtonStyle[1] = new Style(UNSELECTED_DARK, LIGHT, 10, null, null);

        //light theme
        eButtonStyle[0] = new Style(GREY_200, GREY_900, null, GREY_88, 1);
        //dark theme
        eButtonStyle[1] = new Style(GREY_900, GREY_200, null, GREY_88, 1);

        //light theme
        wrongEButtonStyle[0] = new Style(LIGHT_RED, GREY_700, null, RED_100, 1);
        //dark theme
        wrongEButtonStyle[1] = new Style(DARK_RED, GREY_400, null, RED_900, 1);

        //light theme
        disableEButtonStyle[0] = new Style(GREY_200, GREY_600, null, GREY_88, 1);
        //dark theme
        disableEButtonStyle[1] = new Style(GREY_900, GREY_400, null, GREY_88, 1);

        //light theme
        selectEButtonStyle[0] = new Style(LIGHT_GREEN, BLACK, null, GREY_900, 2);
        //dark theme
        selectEButtonStyle[1] = new Style(DARK_GREEN, WHITE, null, GREY_400, 2);

        //light theme
        hintEButtonStyle[0] = new Style(LIGHT_GREEN, GREY_900, null, GREY_88, 1);
        //dark theme
        hintEButtonStyle[1] = new Style(DARK_GREEN, GREY_200, null, GREY_88, 1);


        //light theme
        labelStyle[0] = new Style(null, DARK, null, null, null);
        //dark theme
        labelStyle[1] = new Style(null, LIGHT, null, null, null);

        //light theme
        boxStyle[0] = new Style(LIGHT, null, null, null, null);
        //dark theme
        boxStyle[1] = new Style(DARK, null, null, null, null);
    }

    public static Style[] getStyle(String name) {
        if (!styleMap.containsKey(name)) throw new IllegalArgumentException("no style with name " + name);
        return styleMap.get(name);
    }

    public static String getCSS(Style[] style) {
        return style[Theme.currentTheme].getCSS();
    }
}
