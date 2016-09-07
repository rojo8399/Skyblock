package com.aniskywalker.skyblock.core.islands;

import com.aniskywalker.skyblock.core.members.IslandMember;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Island {

    {
        ObjectMapper objectMapper = new ObjectMapper();
    }

    private UUID id;
    private IslandMetadata metadata = new IslandMetadata();
    private Map<String, Object> stats = new HashMap<>();
    private List<IslandMember> members = new ArrayList<>();
    private Map<String, IslandSetting<?>> settings = new HashMap<>();
    private ArrayList<LogItem> log = new ArrayList<>();

    private static final LoadingCache<UUID, Island> cache = CacheBuilder.newBuilder().build(
            new CacheLoader<UUID, Island>() {
                @Override
                public Island load(UUID key) throws Exception {
                    return new Island(key);
                }
            });

    public static Island getById(UUID id) throws ExecutionException {
        return Island.cache.get(id);
    }

    private Island(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

}
