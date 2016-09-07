package com.aniskywalker.skyblock.core.events;

import com.aniskywalker.skyblock.core.islands.Island;
import com.aniskywalker.skyblock.core.islands.IslandSetting;
import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.Cause;

public class IslandSettingsChangeEvent implements Event, Cancellable {

    private final Cause cause;
    private final Island island;
    private final ImmutableMap<String, IslandSetting<?>> changed;
    private boolean cancelled = false;

    public IslandSettingsChangeEvent(Cause cause, Island island, ImmutableMap<String, IslandSetting<?>> changed) {
        this.cause = cause;
        this.island = island;
        this.changed = changed;
    }

    @Override
    public Cause getCause() {
        return this.cause;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
