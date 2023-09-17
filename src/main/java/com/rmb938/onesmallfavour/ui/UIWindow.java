package com.rmb938.onesmallfavour.ui;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.SpriteID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetPositionMode;
import net.runelite.api.widgets.WidgetSizeMode;
import net.runelite.api.widgets.WidgetType;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UIWindow extends UIComponent {

    private final List<UIComponent> components;

    public UIWindow() {
        super(WidgetType.GRAPHIC);

        this.components = new ArrayList<>();
    }

    public void add(UIComponent component) {
        this.components.add(component);
    }

    public void remove(UIComponent component) {
        this.components.remove(component);
    }

    public void build(Client client) {
        List<UIComponent> internalComponents = new ArrayList<>();

        UIGraphic backgroundGraphic = new UIGraphic(297);
        backgroundGraphic.setSpriteTiling(true);
        backgroundGraphic.setSize(0, 2);
        backgroundGraphic.setSizeMode(WidgetSizeMode.MINUS, WidgetSizeMode.MINUS);
        backgroundGraphic.setPositionMode(WidgetPositionMode.ABSOLUTE_CENTER, WidgetPositionMode.ABSOLUTE_CENTER);
        internalComponents.add(backgroundGraphic);

        UIGraphic topLeftGraphic = new UIGraphic(310);
        topLeftGraphic.setSpriteTiling(true);
        topLeftGraphic.setSize(25, 30);
        internalComponents.add(topLeftGraphic);

        UIGraphic topRightGraphic = new UIGraphic(311);
        topRightGraphic.setSpriteTiling(true);
        topRightGraphic.setSize(25, 30);
        topRightGraphic.setPositionMode(WidgetPositionMode.ABSOLUTE_RIGHT, 0);
        internalComponents.add(topRightGraphic);

        UIGraphic bottomLeftGraphic = new UIGraphic(312);
        bottomLeftGraphic.setSpriteTiling(true);
        bottomLeftGraphic.setSize(25, 30);
        bottomLeftGraphic.setPositionMode(0, WidgetPositionMode.ABSOLUTE_BOTTOM);
        internalComponents.add(bottomLeftGraphic);

        UIGraphic bottomRightGraphic = new UIGraphic(313);
        bottomRightGraphic.setSpriteTiling(true);
        bottomRightGraphic.setSize(25, 30);
        bottomRightGraphic.setPositionMode(WidgetPositionMode.ABSOLUTE_RIGHT, WidgetPositionMode.ABSOLUTE_BOTTOM);
        internalComponents.add(bottomRightGraphic);

        UIGraphic leftGraphic = new UIGraphic(172);
        leftGraphic.setSpriteTiling(true);
        leftGraphic.setPosition(-15, 0);
        leftGraphic.setSize(36, 60);
        leftGraphic.setPositionMode(0, WidgetPositionMode.ABSOLUTE_CENTER);
        leftGraphic.setSizeMode(0, WidgetSizeMode.MINUS);
        internalComponents.add(leftGraphic);

        UIGraphic rightGraphic = new UIGraphic(315);
        rightGraphic.setSpriteTiling(true);
        rightGraphic.setPosition(-15, 0);
        rightGraphic.setSize(36, 60);
        rightGraphic.setPositionMode(WidgetPositionMode.ABSOLUTE_RIGHT, WidgetPositionMode.ABSOLUTE_CENTER);
        rightGraphic.setSizeMode(0, WidgetSizeMode.MINUS);
        internalComponents.add(rightGraphic);

        UIGraphic topGraphic = new UIGraphic(314);
        topGraphic.setSpriteTiling(true);
        topGraphic.setPosition(0, -15);
        topGraphic.setSize(50, 36);
        topGraphic.setPositionMode(WidgetPositionMode.ABSOLUTE_CENTER, 0);
        topGraphic.setSizeMode(WidgetSizeMode.MINUS, 0);
        internalComponents.add(topGraphic);

        UIGraphic bottomGraphic = new UIGraphic(173);
        bottomGraphic.setSpriteTiling(true);
        bottomGraphic.setPosition(0, -15);
        bottomGraphic.setSize(50, 36);
        bottomGraphic.setPositionMode(WidgetPositionMode.ABSOLUTE_CENTER, WidgetPositionMode.ABSOLUTE_BOTTOM);
        bottomGraphic.setSizeMode(WidgetSizeMode.MINUS, 0);
        internalComponents.add(bottomGraphic);

        UIGraphic topBarCutGraphic = new UIGraphic(2546);
        topBarCutGraphic.setSpriteTiling(true);
        topBarCutGraphic.setPosition(0, 14);
        topBarCutGraphic.setSize(10, 26);
        topBarCutGraphic.setPositionMode(WidgetPositionMode.ABSOLUTE_CENTER, 0);
        topBarCutGraphic.setSizeMode(WidgetSizeMode.MINUS, 0);
        internalComponents.add(topBarCutGraphic);

        UIButton closeButton = new UIButton(SpriteID.WINDOW_CLOSE_BUTTON);
        closeButton.setHoverSpriteId(SpriteID.WINDOW_CLOSE_BUTTON_HOVERED);
        closeButton.setPosition(3, 6);
        closeButton.setSize(26, 23);
        closeButton.setPositionMode(WidgetPositionMode.ABSOLUTE_RIGHT, 0);
        closeButton.addAction(0, "Close", callback -> {
            client.runScript(29);
        });
        internalComponents.add(closeButton);

        this.components.addAll(0, internalComponents);

        for (UIComponent component : this.components) {
            component.build(client);
        }
    }

    public void postRender(Widget parentWidget, Widget childWidget) {
        for (UIComponent component : this.components) {
            component.render(parentWidget);
        }
    }
}
