package com.aniskywalker.skyblock.core.exceptions;

import com.aniskywalker.skyblock.core.islands.Island;

/**
 * Thrown when a data-backed or cached object has been orphaned from the system, but a functional method has been
 * called.
 *
 * <p>For example, if an {@link Island} was retrieved from the cache but was destroyed, any method called on it that
 * would depend on it accessing a database will throw this exception.
 * </p>
 */
public class OrphanedException extends Exception {

}
