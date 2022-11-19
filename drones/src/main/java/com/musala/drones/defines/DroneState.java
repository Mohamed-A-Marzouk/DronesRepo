package com.musala.drones.defines;

import java.util.HashMap;

public enum DroneState {
    IDLE("IDLE", 1),
    LOADING("LOADING", 2),
    LOADED("LOADED", 3),
    DELIVERING("DELIVERING", 4),
    DELIVERED("DELIVERED", 5),
    RETURNING("RETURNING", 6);

    private final String name;
    private final int value;
    private final static HashMap<Integer, DroneState> valueMap;
    private final static HashMap<String, DroneState> nameMap;

    static {
        valueMap = new HashMap<>();
        nameMap = new HashMap<>();
        for (DroneState status : DroneState.values()) {
            valueMap.put(status.value, status);
            nameMap.put(status.name(), status);
        }
    }

    private DroneState(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static DroneState getById(int id) {
        return valueMap.get(id);
    }

}
