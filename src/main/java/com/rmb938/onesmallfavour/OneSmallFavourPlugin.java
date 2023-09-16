package com.rmb938.onesmallfavour;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
        name = "One Small Favour"
)
public class OneSmallFavourPlugin extends Plugin {

    @Inject
    private ClientToolbar clientToolbar;

    @Inject
    private Client client;

    @Inject
    private OneSmallFavourConfig config;

    @Override
    protected void startUp() throws Exception {


    }

    @Override
    protected void shutDown() throws Exception {
        log.info("Example stopped!");
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged) {
        if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
        }
    }

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded event) {
        if (event.getGroupId() == 717) {
            log.info("combat tasks open");
            Widget combatAchievementsDropDown = this.client.getWidget(717, 17);

            // resize dropdown to fit new text
            combatAchievementsDropDown.setOriginalWidth(combatAchievementsDropDown.getOriginalWidth() + 11);
            combatAchievementsDropDown.setOriginalHeight(combatAchievementsDropDown.getOriginalHeight() + 29);

            Widget overviewWidgetBackground = combatAchievementsDropDown.getChild(9);

            // resize dropdown children to fit new text
            for (Widget child : combatAchievementsDropDown.getChildren()) {
                if (child.getType() == WidgetType.TEXT || child.getType() == WidgetType.RECTANGLE) {
                    child.setOriginalWidth(80);
                }
            }

            overviewWidgetBackground.setOriginalWidth(80);

            log.info("creating child widget");
            Widget backgroundWidget = combatAchievementsDropDown.createChild(-1, WidgetType.RECTANGLE);
            backgroundWidget.setOriginalX(6);
            backgroundWidget.setOriginalY(98);
            backgroundWidget.setOriginalWidth(80);
            backgroundWidget.setOriginalHeight(23);
            backgroundWidget.setFilled(true);
            backgroundWidget.setOpacity(255);
            backgroundWidget.revalidate();

            Widget textWidget = combatAchievementsDropDown.createChild(-1, WidgetType.TEXT);
            textWidget.setText("OSF - Dailies");
            textWidget.setFontId(495);
            textWidget.setXTextAlignment(WidgetTextAlignment.CENTER);
            textWidget.setYTextAlignment(WidgetTextAlignment.CENTER);
            textWidget.setTextColor(0xff981f);
            textWidget.setTextShadowed(true);
            textWidget.setOriginalX(6);
            textWidget.setOriginalY(98);
            textWidget.setOriginalWidth(80);
            textWidget.setOriginalHeight(23);
            textWidget.revalidate();
            textWidget.setHasListener(true);
            textWidget.setAction(0, "View OSF - Dailies");
            textWidget.setOnMouseOverListener((JavaScriptCallback) callBack -> {
                textWidget.setTextColor(0xffffff);
            });
            textWidget.setOnMouseLeaveListener((JavaScriptCallback) callBack -> {
                textWidget.setTextColor(0xff981f);
            });
            textWidget.setOnOpListener((JavaScriptCallback) callBack -> {
                combatAchievementsDropDown.setHidden(true);

                Widget combatAchievementsLayer = this.client.getWidget(717, 0);
//                combatAchievementsLayer.createChild(-1, WidgetType.LAYER); - crashes with java.lang.StackOverflowError: null, not sure how to use LAYER

                Widget dailiesBackgroundSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesBackgroundSprite.setSpriteId(297);
                dailiesBackgroundSprite.setSpriteTiling(true);
                dailiesBackgroundSprite.setOriginalHeight(2);
                dailiesBackgroundSprite.setWidthMode(WidgetSizeMode.MINUS);
                dailiesBackgroundSprite.setHeightMode(WidgetSizeMode.MINUS);
                dailiesBackgroundSprite.setXPositionMode(WidgetPositionMode.ABSOLUTE_CENTER);
                dailiesBackgroundSprite.setYPositionMode(WidgetPositionMode.ABSOLUTE_CENTER);
                dailiesBackgroundSprite.revalidate();

                Widget dailiesTopLeftSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesTopLeftSprite.setSpriteId(310);
                dailiesTopLeftSprite.setSpriteTiling(true);
                dailiesTopLeftSprite.setOriginalWidth(25);
                dailiesTopLeftSprite.setOriginalHeight(30);
                dailiesTopLeftSprite.revalidate();

                Widget dailiesTopRightSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesTopRightSprite.setSpriteId(311);
                dailiesTopRightSprite.setSpriteTiling(true);
                dailiesTopRightSprite.setOriginalWidth(25);
                dailiesTopRightSprite.setOriginalHeight(30);
                dailiesTopRightSprite.setXPositionMode(WidgetPositionMode.ABSOLUTE_RIGHT);
                dailiesTopRightSprite.revalidate();

                Widget dailiesBottomLeftSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesBottomLeftSprite.setSpriteId(312);
                dailiesBottomLeftSprite.setSpriteTiling(true);
                dailiesBottomLeftSprite.setOriginalWidth(25);
                dailiesBottomLeftSprite.setOriginalHeight(30);
                dailiesBottomLeftSprite.setYPositionMode(WidgetPositionMode.ABSOLUTE_BOTTOM);
                dailiesBottomLeftSprite.revalidate();

                Widget dailiesBottomRightSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesBottomRightSprite.setSpriteId(313);
                dailiesBottomRightSprite.setSpriteTiling(true);
                dailiesBottomRightSprite.setOriginalWidth(25);
                dailiesBottomRightSprite.setOriginalHeight(30);
                dailiesBottomRightSprite.setXPositionMode(WidgetPositionMode.ABSOLUTE_RIGHT);
                dailiesBottomRightSprite.setYPositionMode(WidgetPositionMode.ABSOLUTE_BOTTOM);
                dailiesBottomRightSprite.revalidate();

                Widget dailiesLeftSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesLeftSprite.setSpriteId(172);
                dailiesLeftSprite.setSpriteTiling(true);
                dailiesLeftSprite.setOriginalX(-15);
                dailiesLeftSprite.setOriginalWidth(36);
                dailiesLeftSprite.setOriginalHeight(60);
                dailiesLeftSprite.setYPositionMode(WidgetPositionMode.ABSOLUTE_CENTER);
                dailiesLeftSprite.setHeightMode(WidgetSizeMode.MINUS);
                dailiesLeftSprite.revalidate();

                Widget dailiesRightSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesRightSprite.setSpriteId(315);
                dailiesRightSprite.setSpriteTiling(true);
                dailiesRightSprite.setOriginalX(-15);
                dailiesRightSprite.setOriginalWidth(36);
                dailiesRightSprite.setOriginalHeight(60);
                dailiesRightSprite.setXPositionMode(WidgetPositionMode.ABSOLUTE_RIGHT);
                dailiesRightSprite.setYPositionMode(WidgetPositionMode.ABSOLUTE_CENTER);
                dailiesRightSprite.setHeightMode(WidgetSizeMode.MINUS);
                dailiesRightSprite.revalidate();

                Widget dailiesTopSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesTopSprite.setSpriteId(314);
                dailiesTopSprite.setSpriteTiling(true);
                dailiesTopSprite.setOriginalY(-15);
                dailiesTopSprite.setOriginalWidth(50);
                dailiesTopSprite.setOriginalHeight(36);
                dailiesTopSprite.setXPositionMode(WidgetPositionMode.ABSOLUTE_CENTER);
                dailiesTopSprite.setWidthMode(WidgetSizeMode.MINUS);
                dailiesTopSprite.revalidate();

                Widget dailiesBottomSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesBottomSprite.setSpriteId(173);
                dailiesBottomSprite.setSpriteTiling(true);
                dailiesBottomSprite.setOriginalY(-15);
                dailiesBottomSprite.setOriginalWidth(50);
                dailiesBottomSprite.setOriginalHeight(36);
                dailiesBottomSprite.setXPositionMode(WidgetPositionMode.ABSOLUTE_CENTER);
                dailiesBottomSprite.setYPositionMode(WidgetPositionMode.ABSOLUTE_BOTTOM);
                dailiesBottomSprite.setWidthMode(WidgetSizeMode.MINUS);
                dailiesBottomSprite.revalidate();

                Widget dailiesTopBarCutSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesTopBarCutSprite.setSpriteId(2546);
                dailiesTopBarCutSprite.setSpriteTiling(true);
                dailiesTopBarCutSprite.setOriginalY(14);
                dailiesTopBarCutSprite.setOriginalWidth(10);
                dailiesTopBarCutSprite.setOriginalHeight(26);
                dailiesTopBarCutSprite.setXPositionMode(WidgetPositionMode.ABSOLUTE_CENTER);
                dailiesTopBarCutSprite.setWidthMode(WidgetSizeMode.MINUS);
                dailiesTopBarCutSprite.revalidate();

                Widget dailiesCloseSprite = combatAchievementsLayer.createChild(-1, WidgetType.GRAPHIC);
                dailiesCloseSprite.setSpriteId(535);
                dailiesCloseSprite.setSpriteTiling(true);
                dailiesCloseSprite.setOriginalX(3);
                dailiesCloseSprite.setOriginalY(6);
                dailiesCloseSprite.setOriginalWidth(26);
                dailiesCloseSprite.setOriginalHeight(23);
                dailiesCloseSprite.setXPositionMode(WidgetPositionMode.ABSOLUTE_RIGHT);
                dailiesCloseSprite.revalidate();
            });
        }
    }

    @Provides
    OneSmallFavourConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(OneSmallFavourConfig.class);
    }
}
