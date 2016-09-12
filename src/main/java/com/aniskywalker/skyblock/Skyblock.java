package com.aniskywalker.skyblock;

import com.aniskywalker.skyblock.core.database.C;

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

    private static Skyblock instance;

    public Skyblock() {
        instance = this;
    }

    public static Skyblock getInstance() {
        return instance;
    }

    private C db;

    public C getDatabase() {
        return this.db;
    }

    @Listener
    public void onInit(GameInitializationEvent event) {

    }

}
