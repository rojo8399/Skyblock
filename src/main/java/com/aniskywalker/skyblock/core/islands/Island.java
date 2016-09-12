package com.aniskywalker.skyblock.core.islands;

import com.aniskywalker.skyblock.core.exceptions.OrphanedException;
import com.aniskywalker.skyblock.core.members.IslandMember;
import com.aniskywalker.skyblock.core.util.Callback;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.schematic.Schematic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * This object represents an Island stored in the database.
 */
public class Island {

    private final UUID id;
    private IslandMetadata metadata = new IslandMetadata();
    private List<IslandMember> members = new ArrayList<>();
    private Map<String, ?> settings = new HashMap<>();
    private Map<String, Map<String, ?>> data = new HashMap<>();

    private boolean orphaned = false;


    private static final LoadingCache<UUID, Island> cache = CacheBuilder.newBuilder().build(
            new com.google.common.cache.CacheLoader<UUID, Island>() {
                @Override
                public Island load(UUID key) {
                    return new Island(key);
                }
            });

    {
        World w = Sponge.getServer().getWorld("").get();
        Schematic.builder().build();
    }

    /**
     * Gets an Island by ID.
     *
     * <p>Note: This does not communicate with the database. There are no guarantees this island actually exists in
     * the database.</p>
     *
     * @param id The ID of the Island
     * @return The Island
     */
    public static Island get(UUID id) throws ExecutionException {
        // Island itself doesn't do anything that could throw an exception...
        return cache.getUnchecked(id);
    }

    /**
     * DO NOT CONSTRUCT AN ISLAND. Instead, fetch an Island by ID from the cache.
     *
     * @param id The UUID of the Island.
     */
    private Island(UUID id) {
        this.id = id;
    }

    //    /**
    //     * DO NOT USE THIS METHOD. Instead, fetch an Island from the cache.
    //     *
    //     * @param id The UUID of the Island.
    //     *
    //     * @return A new builder instance.
    //     * @see Island#getById(UUID)
    //     */
    //    private static IslandBuilder builder(UUID id) {
    //        return new IslandBuilder(id);
    //    }

    /**
     * Gets whether or not this Island has been orphaned. If this returns true, any method that can throw an
     * {@link OrphanedException} should be expected to do so, and this object should be discarded.
     *
     * @return Whether or not this Island has been orphaned
     */
    public boolean isOrphaned() {
        return this.orphaned;
    }

    /**
     * Gets the {@link UUID} that identifies this Island.
     *
     * <p>This does not throw an {@link OrphanedException} if the Island has been orphaned because the ID cannot
     * change. However, this does not promise the existence of an Island in the
     * database.</p>
     *
     * @return The UUID
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Gets the {@link IslandMetadata} associated with this Island.
     *
     * @param callback The callback to accept results.
     * @throws OrphanedException Thrown if this Island has been orphaned.
     */
    public void getMetadata(Callback<IslandMetadata> callback) throws OrphanedException {
    }

    public ImmutableMap<String, ?> getDataForId(String id) {
        if (data.containsKey(id)) {
            return ImmutableMap.copyOf(data.get(id));
        }
        return ImmutableMap.<String, Object>builder().build();
    }

    public ImmutableList<IslandMember> getMembers() {
        return ImmutableList.copyOf(members);
    }

    public ImmutableMap<String, ?> getSettings() {
        return ImmutableMap.copyOf(settings);
    }

    public void destroy() {
        CompletableFuture<Island> completableFuture = new CompletableFuture<>();
        // Invalidate this Island from the cache.
        cache.invalidate(this.id);
        // Mark this Island as orphaned.
        this.orphaned = true;
        // Remove all data from this Island (to free up memory in the event there is still a reference to this Island).
        this.metadata = null;
        this.members = null;
        this.settings = null;
        this.data = null;
    }

    //    /**
    //     * DO NOT USE THIS CLASS. It is for internal use only! Instead, fetch an Island from the cache.
    //     *
    //     * @see Island#getById(UUID)
    //     */
    //    public static class IslandBuilder {
    //
    //        private final UUID id;
    //        private IslandMetadata metadata;
    //        private List<IslandMember> members;
    //        private Map<String, ?> settings;
    //        private Map<String, Map<String, ?>> data;
    //
    //
    //        IslandBuilder(UUID id) {
    //            this.id = id;
    //        }
    //
    //        public Island.IslandBuilder metadata(IslandMetadata metadata) {
    //            this.metadata = metadata;
    //            return this;
    //        }
    //
    //        public Island.IslandBuilder members(List<IslandMember> members) {
    //            this.members = members;
    //            return this;
    //        }
    //
    //        public Island.IslandBuilder settings(Map<String, ?> settings) {
    //            this.settings = settings;
    //            return this;
    //        }
    //
    //        public Island.IslandBuilder data(Map<String, Map<String, ?>> data) {
    //            this.data = data;
    //            return this;
    //        }
    //
    //        public Island build() {
    //            return new Island(id, metadata, members, settings, data);
    //        }
    //
    //        public String toString() {
    //            return "com.aniskywalker.skyblock.core.islands.Island.IslandBuilder(id=" + this.id + ", metadata="
    //                    + this.metadata + ", data=" + this.data + ", members=" + this.members + ", settings="
    //                    + this.settings + ")";
    //        }
    //    }
}
