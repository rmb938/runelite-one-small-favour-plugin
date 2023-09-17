package com.rmb938.onesmallfavour.ui;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ScriptEvent;
import net.runelite.api.widgets.*;
import org.intellij.lang.annotations.MagicConstant;

@Slf4j
public abstract class UIComponent {

    private int widgetType;

    private String name;

    // size
    @Getter
    private int width;
    @Getter
    private int height;
    private int widthMode;
    private int heightMode;

    // location
    @Getter
    private int x;
    @Getter
    private int y;
    private int xMode;
    private int yMode;

    private boolean hidden;

    public UIComponent(@MagicConstant(valuesFromClass = WidgetType.class) int widgetType) {
        this.widgetType = widgetType;
        this.name = "";
    }

    protected void onActionSelected(ScriptEvent e) {

    }

    protected void onMouseHover(ScriptEvent e) {

    }

    protected void onMouseLeave(ScriptEvent e) {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void build(Client client) {

    }

    public void setSizeMode(@MagicConstant(valuesFromClass = WidgetSizeMode.class) int widthMode, @MagicConstant(valuesFromClass = WidgetSizeMode.class) int heightMode) {
        this.widthMode = widthMode;
        this.heightMode = heightMode;
    }

    public void setPositionMode(@MagicConstant(valuesFromClass = WidgetPositionMode.class) int xMode, @MagicConstant(valuesFromClass = WidgetPositionMode.class) int yMode) {
        this.xMode = xMode;
        this.yMode = yMode;
    }

    public void render(Widget parentWidget) {
        Widget childWidget = parentWidget.createChild(-1, this.widgetType);

        childWidget.setHidden(this.hidden);

        childWidget.setOnOpListener((JavaScriptCallback) this::onActionSelected);
        childWidget.setOnMouseOverListener((JavaScriptCallback) this::onMouseHover);
        childWidget.setOnMouseLeaveListener((JavaScriptCallback) this::onMouseLeave);
        childWidget.setHasListener(true);

        childWidget.setName(String.format("<col=ff9040>%s</col>", this.name));

        childWidget.setWidthMode(this.widthMode);
        childWidget.setHeightMode(this.heightMode);
        childWidget.setXPositionMode(this.xMode);
        childWidget.setYPositionMode(this.yMode);

        childWidget.setOriginalWidth(width);
        childWidget.setOriginalHeight(height);

        childWidget.setOriginalX(x);
        childWidget.setOriginalY(y);
        childWidget.revalidate();

        this.postRender(parentWidget, childWidget);
    }

    public abstract void postRender(Widget parentWidget, Widget childWidget);
}
