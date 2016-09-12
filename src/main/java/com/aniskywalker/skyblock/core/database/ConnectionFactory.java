package com.aniskywalker.skyblock.core.database;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import ninja.leaping.configurate.ConfigurationNode;

public class ConnectionFactory implements PooledObjectFactory<Connection> {

    private final Connection.Builder builder;

    public ConnectionFactory(Connection.Builder configuration) {
        this.builder = configuration;
    }

    public ConnectionFactory(ConfigurationNode configuration) {
        this.builder = RethinkDB.r.connection();
        if (!configuration.getNode("hostname").isVirtual()) {
            this.builder.hostname(configuration.getNode("hostname").getString());
        }
        if (!configuration.getNode("port").isVirtual()) {
            this.builder.port(configuration.getNode("port").getInt());
        }
        if (!configuration.getNode("authKey").isVirtual()) {
            this.builder.authKey(configuration.getNode("authKey").getString());
        }
        if (!configuration.getNode("user").isVirtual()) {
            this.builder.user(
                    configuration.getNode("user").getString(),
                    configuration.getNode("password").getString(""));
        }
        if (!configuration.getNode("timeout").isVirtual()) {
            this.builder.timeout(configuration.getNode("timeout").getLong());
        }
        if (!configuration.getNode("db").isVirtual()) {
            this.builder.db(configuration.getNode("db").getString());
        }
    }

    @Override
    public PooledObject<Connection> makeObject() throws Exception {
        return new DefaultPooledObject<>(builder.connect());
    }

    @Override
    public void destroyObject(PooledObject<Connection> p) throws Exception {
        p.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<Connection> p) {
        return p.getObject().isOpen();
    }

    @Override
    public void activateObject(PooledObject<Connection> p) throws Exception {
        p.getObject().reconnect();
    }

    @Override
    public void passivateObject(PooledObject<Connection> p) throws Exception {
        p.getObject().close();
    }
}
