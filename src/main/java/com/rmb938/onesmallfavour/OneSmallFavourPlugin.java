package com.rmb938.onesmallfavour;

import com.google.inject.Provides;
import com.rmb938.onesmallfavour.ui.UIInterface;
import com.rmb938.onesmallfavour.ui.UIWindow;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.JavaScriptCallback;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetTextAlignment;
import net.runelite.api.widgets.WidgetType;
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
                // script to close dropdown, stop dropdown hover, and player interface change sound
                // 46989327 - drop down button id
                // 46989329 - drop down id
                // 46989328 - rectangle around whole interface preventing clicking other buttons when dropdown is open (NoClickThrough true)
                client.runScript(4771, 46989327, 46989329, 46989328);

                // TODO: There has to be other scripts ran to once this is done https://github.com/runelite/runelite/discussions/17038
                //  we can figure out what scripts are running to close, then open a new interface
                //  this will help us select the right parent widget to use in the future

                Widget combatAchievementsLayer = this.client.getWidget(717, 1);
                for (Widget child : combatAchievementsLayer.getDynamicChildren()) {
                    if (child != null) {
                        child.setHidden(true);
                    }
                }

                for (Widget child : combatAchievementsLayer.getStaticChildren()) {
                    if (child != null) {
                        child.setHidden(true);
                    }
                }
//                combatAchievementsLayer.createChild(-1, WidgetType.LAYER); - crashes with java.lang.StackOverflowError: null, not sure how to use LAYER

                UIInterface dailiesInterface = new UIInterface();
                dailiesInterface.add(new UIWindow());
                dailiesInterface.build(client);
                dailiesInterface.render(combatAchievementsLayer);
            });
        }
    }

    @Provides
    OneSmallFavourConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(OneSmallFavourConfig.class);
    }
}
