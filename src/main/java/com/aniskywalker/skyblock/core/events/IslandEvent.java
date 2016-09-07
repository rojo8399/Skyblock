package com.aniskywalker.skyblock.core.events;

import com.aniskywalker.skyblock.core.islands.Island;
import org.spongepowered.api.event.Event;

public interface IslandEvent extends Event {

    Island getIsland();

}
