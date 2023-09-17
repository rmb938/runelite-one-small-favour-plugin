package com.rmb938.onesmallfavour.ui;

import lombok.Getter;
import lombok.Setter;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetType;

public class UIGraphic extends UIComponent {

    @Getter
    private final int spriteId;

    @Setter
    private boolean spriteTiling;

    public UIGraphic(int spriteId) {
        super(WidgetType.GRAPHIC);
        this.spriteId = spriteId;
    }

    public void postRender(Widget parentWidget, Widget childWidget) {
        childWidget.setSpriteId(this.spriteId);
        childWidget.setSpriteTiling(this.spriteTiling);
    }
}