package com.aniskywalker.skyblock.core.islands;


public class IslandMetadata {

    private long createdAt;

    public IslandMetadata() {
        this(System.currentTimeMillis());
    }

    public IslandMetadata(long createdAt) {
        this.createdAt = createdAt;
    }

}
