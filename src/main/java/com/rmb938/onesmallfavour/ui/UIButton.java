package com.rmb938.onesmallfavour.ui;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ScriptEvent;
import net.runelite.api.widgets.JavaScriptCallback;
import net.runelite.api.widgets.Widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class UIButton extends UIGraphic {

    private final List<UIComponent> components;

    @Setter
    private int hoverSpriteId;

    private Map<Integer, Map.Entry<String, JavaScriptCallback>> actions;

    public UIButton(int spriteId) {
        super(spriteId);
        this.components = new ArrayList<>();
        this.actions = new HashMap<>();
    }

    public void postRender(Widget parentWidget, Widget childWidget) {
        super.postRender(parentWidget, childWidget);
        for (UIComponent component : this.components) {
            component.render(parentWidget);
        }

        for (Map.Entry<Integer, Map.Entry<String, JavaScriptCallback>> entry : this.actions.entrySet()) {
            childWidget.setAction(entry.getKey(), entry.getValue().getKey());
        }
    }

    public void addAction(int index, String action, JavaScriptCallback callback) {
        actions.put(index, Map.entry(action, callback));
    }

    protected void onActionSelected(ScriptEvent e) {
        for (Map.Entry<Integer, Map.Entry<String, JavaScriptCallback>> entry : this.actions.entrySet()) {
            if ((e.getOp() - 1) == entry.getKey()) {
                entry.getValue().getValue().run(e);
            }
        }
    }

    protected void onMouseHover(ScriptEvent e) {
        if (this.hoverSpriteId != 0) {
            e.getSource().setSpriteId(this.hoverSpriteId);
        }
    }

    protected void onMouseLeave(ScriptEvent e) {
        e.getSource().setSpriteId(this.getSpriteId());
    }


}
