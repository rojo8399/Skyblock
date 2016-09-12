package com.aniskywalker.skyblock.core.database;

import com.rethinkdb.net.Connection;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import ninja.leaping.configurate.ConfigurationNode;

public class C {

    private final ObjectPool<Connection> pool;

    public C(Connection.Builder builder) {
        this(builder, new GenericObjectPoolConfig());
    }

    public C(Connection.Builder builder, GenericObjectPoolConfig config) {
        this.pool = new GenericObjectPool<Connection>(new ConnectionFactory(builder), config);
    }

    public C(ConfigurationNode configurationNode) {
        this(configurationNode, new GenericObjectPoolConfig());
    }

    public C(ConfigurationNode c, GenericObjectPoolConfig config) {
        this.pool = new GenericObjectPool<Connection>(new ConnectionFactory(c), config);
    }

    public Connection getConnection() throws Exception {
        return pool.borrowObject();
    }

}
