package com.aniskywalker.skyblock;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = PluginInfo.ID,
        version = PluginInfo.VERSION,
        name = PluginInfo.NAME,
        description = PluginInfo.DESCRIPTION,
        url = PluginInfo.URL,
        authors = {"Meyer Zinn (AniSkywalker)"})
public class Skyblock {

    @Listener
    public void onInit(GameInitializationEvent event) {

    }

}
