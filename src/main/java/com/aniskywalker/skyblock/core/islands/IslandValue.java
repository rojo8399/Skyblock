package com.aniskywalker.skyblock.core.islands;

public class IslandValue<T> {

    private T value;

    public IslandValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

}
