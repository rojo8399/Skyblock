package com.aniskywalker.skyblock.core.util;

/**
 * A simple callback interface to facilitate asynchronous data loading. One and only one method will ever be called
 * on any given Callback.
 * @param <T>
 */
public interface Callback<T> {

    /**
     * Called when a result is ready to be accepted.
     * @param result The result of the operation
     */
    void accept(T result);

    /**
     * Called if a request fails.
     * @param cause The cause of failure
     */
    void fail(Throwable cause);
}
