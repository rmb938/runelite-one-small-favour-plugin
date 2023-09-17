package com.rmb938.onesmallfavour.ui;

import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;

import java.util.ArrayList;
import java.util.List;

public class UIInterface {
    private final List<UIComponent> components;

    public UIInterface() {
        this.components = new ArrayList<>();
    }

    public void add(UIComponent component) {
        this.components.add(component);
    }

    public void remove(UIComponent component) {
        this.components.remove(component);
    }

    public void build(Client client) {
        for (UIComponent component : this.components) {
            component.build(client);
        }
    }

    public void render(Widget parentWidget) {
        parentWidget.deleteAllChildren();
        for (UIComponent component : this.components) {
            component.render(parentWidget);
        }
    }
}
