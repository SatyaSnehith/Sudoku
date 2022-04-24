package ss.nscube.sudoku.theme;

public class Style {
    private String backgroundColor;
    private String textColor;
    private Integer borderRadius;
    private String borderColor;
    private Integer borderWidth;

    public Style(String backgroundColor, String textColor, Integer borderRadius, String borderColor, Integer borderWidth) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.borderRadius = borderRadius;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }

    public String getCSS() {
        String style = "";
        if (backgroundColor != null) style += "-fx-background-color:" + backgroundColor + ";";
        if (borderRadius != null) style += "-fx-background-radius:" + borderRadius + ";";
        if (textColor != null) style += "-fx-text-fill:" + textColor + ";";
        if (borderColor != null) style += "-fx-border-color:" + borderColor + ";";
        if (borderWidth != null) style += "-fx-border-width:" + borderWidth + ";";
        return style;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public Integer getBorderRadius() {
        return borderRadius;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public Integer getBorderWidth() {
        return borderWidth;
    }
}
