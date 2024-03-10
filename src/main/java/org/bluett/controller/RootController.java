package org.bluett.controller;

import java.util.HashMap;
import java.util.Map;

public abstract class RootController<V> {

    private V data;

    public void putExtraData(V value) {
        this.data = value;
    }

    public V getExtraData() {
        return data;
    }
}
