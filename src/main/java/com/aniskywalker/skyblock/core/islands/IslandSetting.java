package com.aniskywalker.skyblock.core.islands;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.google.common.base.Objects;

public class IslandSetting<T> {

    private T value;

    @JsonAnySetter
    public void set(Object value) {
        this.value = (T) value;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this.getClass()).add("value", value).toString();
    }

}
