package com.aniskywalker.skyblock.core.islands;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import org.junit.Test;


public class IslandTest {

    public static final RethinkDB r = RethinkDB.r;

    @Test
    public void testJacksonDeserialization() {
        Island island = new Island();
    }

}
