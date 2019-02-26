package com.zhel.todo;

public class ToDoDataModel {
    private String textItem;
    private boolean isStrikeout;

    public ToDoDataModel(String textItem, boolean isStrikeout) {
        this.textItem = textItem;
        this.isStrikeout = isStrikeout;
    }

    public String getTextItem() {
        return textItem;
    }

    public boolean isStrikeout() {
        return isStrikeout;
    }

    public void setStrikeout(boolean strikeout) {
        isStrikeout = strikeout;
    }
}
