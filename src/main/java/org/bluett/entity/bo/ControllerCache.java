package org.bluett.entity.bo;

import org.bluett.entity.enums.NodePathEnum;

import java.util.HashMap;
import java.util.Map;

public class ControllerCache {
    private ControllerCache() {
    }

    private final static Map<NodePathEnum, Object> dataMap = new HashMap<>();

    public static void putData(NodePathEnum nodePathEnum, Object data) {
        dataMap.put(nodePathEnum, data);
    }

    public static Object getData(NodePathEnum nodePathEnum) {
        Object object = dataMap.get(nodePathEnum);
        dataMap.remove(nodePathEnum);
        return object;
    }
}
